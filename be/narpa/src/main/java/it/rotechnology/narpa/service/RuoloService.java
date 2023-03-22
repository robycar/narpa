package it.rotechnology.narpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rotechnology.narpa.dto.RuoloDTO;
import it.rotechnology.narpa.model.Funzione;
import it.rotechnology.narpa.model.Ruolo;
import it.rotechnology.narpa.repository.FunzioneRepository;
import it.rotechnology.narpa.repository.RuoloRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RuoloService {

	@Autowired
	private RuoloRepository ruoloRepository;

	@Autowired
	private FunzioneRepository funzioneRepository;

	public RuoloDTO findRuoloById(Long id) {
		Ruolo ruolo = ruoloRepository.findById(id).orElseThrow();

		return new RuoloDTO(ruolo, true);
	}

	public List<RuoloDTO> findAll() {
		Iterable<Ruolo> ruoli = ruoloRepository.findAll();

		List<RuoloDTO> result = new ArrayList<>();

		for (Ruolo ruolo : ruoli) {
			result.add(new RuoloDTO(ruolo));
		}

		return result;
	}

	public RuoloDTO createRuolo(RuoloDTO dto) {
		Ruolo ruolo = new Ruolo();

		ruolo.setNome(dto.getNome());
		ruolo.setDescrizione(dto.getDescrizione());
		List<Funzione> listaFunzioni = new ArrayList<>();
		for (String f : dto.getFunzioni()) {
			listaFunzioni.add(this.funzioneRepository.findById(f).get());
		}

		ruolo.setFunzioni(listaFunzioni);

		return new RuoloDTO(this.ruoloRepository.save(ruolo));
	}

	public void deleteRuolo(Long id) {
		this.ruoloRepository.deleteById(id);
	}

	public Ruolo readRuolo(Long id) {
		Optional<Ruolo> result = ruoloRepository.findById(id);

		return result.orElseThrow(() -> new EntityNotFoundException("Ruolo non presente nel database" + id));
	}

	public RuoloDTO updateRuolo(RuoloDTO dto) {
		Ruolo ruolo = readRuolo(dto.getId());

		if (dto.getNome() != null) {
			ruolo.setNome(dto.getNome());
		}
		if (dto.getDescrizione() != null) {
			ruolo.setDescrizione(dto.getDescrizione());
		}

		return new RuoloDTO(this.ruoloRepository.save(ruolo));
	}
}