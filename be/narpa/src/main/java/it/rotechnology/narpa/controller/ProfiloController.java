package it.rotechnology.narpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.rotechnology.narpa.dto.ProfiloDTO;
import it.rotechnology.narpa.service.ProfiloService;

@RestController
@RequestMapping(path = "/api/auth/profilo")
public class ProfiloController {
	@Autowired
	ProfiloService profiloService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<ProfiloDTO> findProfiloById(@PathVariable("id") long id) {
		ProfiloDTO profilo = profiloService.getProfiloById(id);
		return ResponseEntity.ok(profilo);
	}

	@PutMapping("")
	public ResponseEntity<ProfiloDTO> aggiungiProfilo(@RequestBody ProfiloDTO profiloDTO) {
		ProfiloDTO profilo = profiloService.creaProfilo(profiloDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(profilo);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ProfiloDTO> rimuoviProfilo(@PathVariable("id") Long id) {
		profiloService.rimuoviProfilo(id);
		return ResponseEntity.ok(null);

	}
}
