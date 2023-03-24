package it.rotechnology.narpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rotechnology.narpa.dto.ProfiloDTO;
import it.rotechnology.narpa.dto.RuoloDTO;
import it.rotechnology.narpa.exception.AppError;
import it.rotechnology.narpa.model.Profilo;
import it.rotechnology.narpa.model.Ruolo;
import it.rotechnology.narpa.repository.ProfiloRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProfiloService extends AbstractService {
	
	@Autowired
	private ProfiloRepository profiloRepository;
	
	@Autowired
	private RuoloService ruoloService;

	public void salvaProfilo(Profilo p) {
		profiloRepository.save(p);
	}

	public List<ProfiloDTO> getAll() {
	
		List<Profilo> lista = profiloRepository.findAll();
		return lista.stream().map(ProfiloDTO::new).collect(Collectors.toList()); 
//		Equivale a scrivere:
//		List<ProfiloDTO> result = new ArrayList<>();
//		for (Profilo profilo : lista) {
//			result.add(new ProfiloDTO(profilo)); 
//		}
//		return result;
	}

	public ProfiloDTO modificaProfilo(ProfiloDTO profiloDTO) {
		Profilo profilo = read(profiloDTO.getId());
		if (!profilo.getNome().equalsIgnoreCase(profiloDTO.getNome())) {

			Optional<Profilo> altroProfilo = profiloRepository.findByNome(profiloDTO.getNome());
			if (altroProfilo.isPresent()) {
				throw makeError(log, AppError.PROFILE_ALREADY_EXISTS, profiloDTO.getNome(), altroProfilo.get().getId());
			}
			profilo.setNome(profiloDTO.getNome());
		}
		profilo.setDescrizione(profiloDTO.getDescrizione());
		
		List<Ruolo>ruoli = ruoloService.readRuoli(profiloDTO.getRuoli().stream().map(RuoloDTO::getId).collect(Collectors.toList()));
		profilo.setRuoli(ruoli);
		return new ProfiloDTO(profiloRepository.saveAndFlush(profilo),true);
	}

	public Profilo read(Long id) {
		Optional<Profilo> profilo = this.profiloRepository.findById(id);
		return profilo.orElseThrow(() -> makeError(log, AppError.PROFILE_NOT_EXISTS, id));
	}

	public ProfiloDTO getProfiloById(Long id) {
		return new ProfiloDTO(read(id),true);
	}

	public ProfiloDTO creaProfilo(ProfiloDTO profiloDTO) {
		
		Optional<Profilo> profiloTramiteNome = profiloRepository.findByNome(profiloDTO.getNome());
		if(profiloTramiteNome.isPresent()) {
			throw  makeError(log, AppError.PROFILE_ALREADY_EXISTS, profiloDTO.getNome(),profiloTramiteNome.get().getId() );				
		}
		Profilo profilo = new Profilo();
		profilo.setId(profiloDTO.getId());
		profilo.setNome(profiloDTO.getNome());
		profilo.setDescrizione(profiloDTO.getDescrizione());
		List<Ruolo> ruoli = new ArrayList<>();
		for (RuoloDTO ruoloDTO : profiloDTO.getRuoli()) {
			Ruolo ruolo = ruoloService.readRuolo(ruoloDTO.getId());
			ruoli.add(ruolo);
		}
		profilo.setRuoli(ruoli);
		return new ProfiloDTO(this.profiloRepository.save(profilo),true);
	}

	public void rimuoviProfilo(Long id) {

		this.profiloRepository.deleteById(id);
	}	
}