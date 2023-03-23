package it.rotechnology.narpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.rotechnology.narpa.dto.UtenteDTO;
import it.rotechnology.narpa.service.LoginService;
import jakarta.validation.Valid;

@RestController

public class LoginController {
    
    @Autowired
    private LoginService loginService;

    /*@Autowired
    private UtenteService utenteService;
    
    @Autowired
    private RuoloService ruoloService;*/

    @PostMapping("api/login")
    public ResponseEntity<?> retrieveUserData(@Valid @RequestParam(name = "username") String username, @RequestParam(name = "password") String password){
        
        if(loginService.existsByUsername(username)){

            UtenteDTO utenteSessione = loginService.findByUsername(username);        

            if(password.equals(utenteSessione.getPassword())){
                //TODO: Mostrare elenco dei profili e delle funzioni dell'utente

                return ResponseEntity.status(HttpStatus.OK).body("Benvenuto!");
            }
    
            return ResponseEntity.status(HttpStatus.OK).body("La password inserita non è corretta.");
        }
        
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("L'username non esiste.");
        }

}
