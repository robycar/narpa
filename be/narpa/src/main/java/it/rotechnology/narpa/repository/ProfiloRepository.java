package it.rotechnology.narpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.rotechnology.narpa.model.Profilo;

public interface ProfiloRepository extends JpaRepository<Profilo, Long>{
	
	Optional<Profilo>  findByNome(String nomeProfilo);

}
