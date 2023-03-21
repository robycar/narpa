package it.rotechnology.narpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rotechnology.narpa.dto.ProfiloDTO;
import it.rotechnology.narpa.model.Profilo;
import it.rotechnology.narpa.repository.ProfiloRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProfiloService {
	@Autowired
	private ProfiloRepository profiloRepository;

	public void salvaProfilo(Profilo p) {
		profiloRepository.save(p);
	}

//	public List<Profilo> getAll() {
//		List<Profilo> result = List < Profilo > profiloRepository.findAll();
//		return result;
//	}

	public Profilo read(Long id) {
		Optional<Profilo> profilo = this.profiloRepository.findById(id);
		return profilo.get();
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
//	
//	public ProfiloDTO rimuoviProfilo(ProfiloDTO profiloDTO) {
//		
//	}
	
}
