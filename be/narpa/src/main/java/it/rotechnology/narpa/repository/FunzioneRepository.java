package it.rotechnology.narpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.rotechnology.narpa.model.Funzione;

public interface FunzioneRepository extends JpaRepository<Funzione, String> {
	// Optional<Funzione> findByNome(String nome);
}
