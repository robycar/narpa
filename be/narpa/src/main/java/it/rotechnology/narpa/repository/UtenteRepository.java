package it.rotechnology.narpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.rotechnology.narpa.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long>{

    Optional<Utente> findByUsername(String username);

    Boolean existsByUsername(String username);


}
