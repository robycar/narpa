package it.rotechnology.narpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rotechnology.narpa.dto.ProfiloDTO;
import it.rotechnology.narpa.exception.AppError;
import it.rotechnology.narpa.model.Profilo;
import it.rotechnology.narpa.repository.ProfiloRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProfiloService extends AbstractService {
	
	@Autowired
	private ProfiloRepository profiloRepository;

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
		profilo.setId(profiloDTO.getId());
		profilo.setNome(profiloDTO.getNome());
		profilo.setDescrizione(profiloDTO.getDescrizione());
		return new ProfiloDTO(profiloRepository.save(profilo));

	}

	public Profilo read(Long id) {
		Optional<Profilo> profilo = this.profiloRepository.findById(id);
		return profilo.orElseThrow(() -> makeError(log, AppError.PROFILE_NOT_EXISTS, id));
	}

	public ProfiloDTO getProfiloById(Long id) {
		return new ProfiloDTO(read(id));
	}

	public ProfiloDTO creaProfilo(ProfiloDTO profiloDTO) {
		Profilo profilo = new Profilo();
		profilo.setId(profiloDTO.getId());
		profilo.setNome(profiloDTO.getNome());
		profilo.setDescrizione(profiloDTO.getDescrizione());
		return new ProfiloDTO(this.profiloRepository.save(profilo));
	}

	public void rimuoviProfilo(Long id) {

		this.profiloRepository.deleteById(id);
	}

}
