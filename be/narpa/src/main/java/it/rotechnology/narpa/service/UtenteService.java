package it.rotechnology.narpa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import it.rotechnology.narpa.dto.UtenteDTO;
import it.rotechnology.narpa.model.Utente;
import it.rotechnology.narpa.repository.UtenteRepository;

public class UtenteService extends AbstractService {

	@Autowired
	UtenteRepository utenteRepository;

	public void salvaUtente(Utente u) {
		utenteRepository.save(u);
	}

	public UtenteDTO getUtenteById(Long id) {
		return new UtenteDTO(read(id));
	}

	public List<UtenteDTO> getAll() {

		List<Utente> lista = utenteRepository.findAll();
		return lista.stream().map(UtenteDTO::new).collect(Collectors.toList());
//		Equivale a scrivere:
//		List<UtenteDTO> result = new ArrayList<>();
//		for (Utente utente: lista) {
//			result.add(new UtenteDTO(utente)); 
//		}
//		return result;
	}

	public Utente read(Long id) {
		Optional<Utente> utente = this.utenteRepository.findById(id);
		return utente.orElseThrow(); // TODO: aggiungere l'implementazione AbstractService
	}

	public UtenteDTO creaUtente(UtenteDTO utenteDTO) {
		Utente utente = new Utente();
		utente.setId(utenteDTO.getId());
		utente.setNome(utenteDTO.getNome());
		utente.setCognome(utenteDTO.getCognome());
		utente.setUsername(utenteDTO.getUsername());
		utente.setEmail(utenteDTO.getEmail());
		utente.setPassword(utenteDTO.getPassword());
		utente.setFunzioni(utenteDTO.getFunzioni());
		return new UtenteDTO(this.utenteRepository.save(utente));

	}

	public void rimuoviUente(Long id) {
		this.utenteRepository.deleteById(id);
	}

	public UtenteDTO modificaUtente(UtenteDTO utenteDTO) {
		Utente utente = read(utenteDTO.getId());
		utente.setId(utenteDTO.getId());
		utente.setNome(utenteDTO.getNome());
		utente.setCognome(utenteDTO.getCognome());
		utente.setUsername(utenteDTO.getUsername());
		utente.setEmail(utenteDTO.getEmail());
		utente.setPassword(utenteDTO.getPassword());
		utente.setFunzioni(utenteDTO.getFunzioni());
		return new UtenteDTO(this.utenteRepository.save(utente));
	}
}
