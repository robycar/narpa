package it.rotechnology.narpa.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rotechnology.narpa.dto.RuoloDTO;
import it.rotechnology.narpa.exception.AppError;
import it.rotechnology.narpa.model.Funzione;
import it.rotechnology.narpa.model.Ruolo;
import it.rotechnology.narpa.repository.FunzioneRepository;
import it.rotechnology.narpa.repository.RuoloRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class RuoloService extends AbstractService{

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

	public List<Ruolo> readRuoli(List<Long> ids) {

		Set<Long> ruoli = new HashSet<>(ids);
		List<Ruolo> result = ruoloRepository.findAllById(ruoli);

		for (Ruolo ruolo : result) {
			ruoli.remove(ruolo.getId());
		}
		if (ruoli.isEmpty()) {
			return result;
		} else {
			throw makeError(log,AppError.ROLE_NOT_EXISTS,ruoli);
		}
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