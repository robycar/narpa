package it.rotechnology.narpa.dto;

import java.util.Set;

import it.rotechnology.narpa.model.Funzione;
import it.rotechnology.narpa.model.Profilo;
import it.rotechnology.narpa.model.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UtenteDTO {

	private Long id;
	private String username;
	private String password;
	private String cognome;
	private String nome;
	private String email;
	private Profilo profilo;
	private Set<Funzione> funzioni;

	public UtenteDTO(Utente utente) {
		super();
		this.id = utente.getId();
		this.username = utente.getUsername();
		this.password = utente.getPassword();
		this.cognome = utente.getCognome();
		this.nome = utente.getNome();
		this.email = utente.getEmail();
		this.profilo = utente.getProfilo();
		this.funzioni = utente.getFunzioni();
	}

	public UtenteDTO(Long id) {
		super();
		this.id = id;
	}
}
