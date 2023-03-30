package it.rotechnology.narpa.dto;

import java.util.Set;
import java.util.stream.Collectors;

import it.rotechnology.narpa.model.Funzione;
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
	private ProfiloDTO profilo;
	private Set<String> funzioni;

	public UtenteDTO(Utente utente, boolean includeDetails) {
		super();
		this.id = utente.getId();
		this.username = utente.getUsername();
		this.password = utente.getPassword();
		this.cognome = utente.getCognome();
		this.nome = utente.getNome();
		this.email = utente.getEmail();
		if(utente.getProfilo()!=null){
			this.profilo = new ProfiloDTO(utente.getProfilo());
		}
		if(includeDetails){
			this.funzioni = utente.getFunzioni().stream().map(Funzione::getCodice).collect(Collectors.toSet());
		}
	}
	
	public UtenteDTO(Utente utente){
		this(utente, false);
	}

	public UtenteDTO(Long id) {
		super();
		this.id = id;
	}
}
