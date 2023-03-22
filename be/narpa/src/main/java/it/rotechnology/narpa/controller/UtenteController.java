package it.rotechnology.narpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rotechnology.narpa.dto.UtenteDTO;
import it.rotechnology.narpa.service.UtenteService;

@RestController
@RequestMapping(path = "/api/auth/utente")
public class UtenteController {

	@Autowired
	UtenteService utenteService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<UtenteDTO> findUtenteById(@PathVariable("id") long id) {
		UtenteDTO utente = utenteService.getUtenteById(id);
		return ResponseEntity.ok(utente);
	}

	@GetMapping("")
	public ResponseEntity<List<UtenteDTO>> listaProfili() {
		List<UtenteDTO> lista = utenteService.getAll();
		return ResponseEntity.ok(lista);
	}

	@PutMapping
	public ResponseEntity<UtenteDTO> aggiungiUtente(@RequestBody UtenteDTO utenteDTO) {
		UtenteDTO utente = utenteService.creaUtente(utenteDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(utente);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<UtenteDTO> rimuoviUtente(@PathVariable("id") Long id) {
		utenteService.rimuoviUente(id);
		return ResponseEntity.ok(null);
	}

	@PostMapping
	public ResponseEntity<UtenteDTO> modificaUtente(@RequestBody UtenteDTO utenteDTO) {
		UtenteDTO utente = utenteService.modificaUtente(utenteDTO);
		return ResponseEntity.ok(utente);
	}

}
