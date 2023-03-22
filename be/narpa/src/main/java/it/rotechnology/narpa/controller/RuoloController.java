package it.rotechnology.narpa.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.rotechnology.narpa.dto.RuoloDTO;
import it.rotechnology.narpa.model.Ruolo;
import it.rotechnology.narpa.service.RuoloService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/ruolo")
public class RuoloController {
	
	@Autowired
	private RuoloService ruoloService;
	
	@GetMapping("/find/{id}")
	public ResponseEntity<RuoloDTO> findRuoloById(@PathVariable("id") Long id) {
		try {
			RuoloDTO dto= ruoloService.findRuoloById(id);
			return ResponseEntity.ok(dto);
		} catch(NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/findAll")
	public List<RuoloDTO> findAll() {
		
		return ruoloService.findAll();
	}
	
	@PutMapping("create")
	public ResponseEntity<RuoloDTO> createRuolo(@Valid @RequestBody RuoloDTO dto) {
		dto= ruoloService.createRuolo(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@PutMapping("update")
	public ResponseEntity<RuoloDTO> updateRuolo(@Valid @RequestBody RuoloDTO dto) {
		dto= ruoloService.updateRuolo(dto);
		
		return ResponseEntity.ok(dto);
	}
	
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteRuolo(@PathVariable("id") Long id) {
		ruoloService.deleteRuolo(id);
		
		return "Ruolo cancellato";
	}

}
