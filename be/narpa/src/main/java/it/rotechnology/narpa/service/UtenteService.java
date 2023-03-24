package it.rotechnology.narpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.rotechnology.narpa.dto.UtenteDTO;
import it.rotechnology.narpa.exception.AppError;
import it.rotechnology.narpa.model.Funzione;
import it.rotechnology.narpa.model.Utente;
import it.rotechnology.narpa.repository.FunzioneRepository;
import it.rotechnology.narpa.repository.UtenteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UtenteService extends AbstractService {

	@Autowired
	FunzioneRepository funzioneRepository;

	@Autowired
	UtenteRepository utenteRepository;

	//Save
	public void salvaUtente(Utente u) {
		utenteRepository.save(u);
	}

	//Read
	public Utente read(Long id) {
		Optional<Utente> utente = this.utenteRepository.findById(id);
		return utente.orElseThrow(() ->  makeError(log, AppError.USER_NOT_EXISTS, id)); // TODO: aggiungere l'implementazione AbstractService
	}

	//Create
	public UtenteDTO creaUtente(UtenteDTO utenteDTO) {
		Optional<Utente> utenteUsername = utenteRepository.findByUsername(utenteDTO.getUsername());
		if(utenteUsername.isPresent()) {
			throw  makeError(log, AppError.PROFILE_ALREADY_EXISTS, utenteDTO.getUsername(), utenteUsername.get().getId() );				
		}
		
		Utente utente = new Utente();
		utente.setId(utenteDTO.getId());
		utente.setNome(utenteDTO.getNome());
		utente.setCognome(utenteDTO.getCognome());
		utente.setUsername(utenteDTO.getUsername());
		utente.setEmail(utenteDTO.getEmail());
		utente.setPassword(utenteDTO.getPassword());

		List<Funzione> funzioni = new ArrayList<>();
		for (Funzione f : utenteDTO.getFunzioni() ) {
			funzioni.add(this.funzioneRepository.findById(f.getCodice()).get());
		}
		utente.setFunzioni(utenteDTO.getFunzioni());
		utente.setProfilo(utenteDTO.getProfilo());

		return new UtenteDTO(this.utenteRepository.save(utente));
	}

	//Get utente by Id
	public UtenteDTO getUtenteById(Long id){
		return new UtenteDTO(read(id));
	}

	//Get all utenti
	public List<UtenteDTO> getAllUtenti() {
		List<Utente> lista = utenteRepository.findAll();
		return lista.stream().map(UtenteDTO::new).collect(Collectors.toList());
	}

	//Delete utente by id
	public void rimuoviUente(Long id) {
		this.utenteRepository.deleteById(id);
	}


	//Update utente
	public UtenteDTO modificaInfoUtente(UtenteDTO utenteDTO) {
		Utente utente = read(utenteDTO.getId());

		if(!utente.getUsername().equalsIgnoreCase(utenteDTO.getUsername())){
			Optional<Utente> checkUtente = utenteRepository.findByUsername(utenteDTO.getUsername());
				if(checkUtente.isPresent()){
					makeError(log, AppError.USER_ALREADY_EXISTS, utenteDTO.getUsername(), checkUtente.get().getUsername());
				}
			utente.setUsername(utenteDTO.getUsername());	
		}

		utente.setNome(utenteDTO.getNome());
		utente.setCognome(utenteDTO.getCognome());
		utente.setEmail(utenteDTO.getEmail());
		utente.setPassword(utenteDTO.getPassword());
		utente.setProfilo(utenteDTO.getProfilo());

		//TODO: Testing con modifica dei profili e delle funzioni, dopo aver popolato le tabelle

		return new UtenteDTO(utenteRepository.saveAndFlush(utente));
	}
}
