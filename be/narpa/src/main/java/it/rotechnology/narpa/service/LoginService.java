package it.rotechnology.narpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rotechnology.narpa.dto.UtenteDTO;
import it.rotechnology.narpa.model.Utente;
import it.rotechnology.narpa.repository.UtenteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoginService {
    
    @Autowired
    private UtenteRepository utenteRepository;


    public UtenteDTO findByUsername(String username){
        Utente utente = utenteRepository.findByUsername(username).get();
        UtenteDTO utenteDto = new UtenteDTO();
        utenteDto.setId(utente.getId());
        utenteDto.setUsername(utente.getUsername());
        utenteDto.setPassword(utente.getPassword());
        return utenteDto;
    }


    public Boolean existsByUsername(String username){
        if(!utenteRepository.existsByUsername(username)){
            return false;
        }

        return true;

    }

}
