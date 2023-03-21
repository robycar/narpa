package it.rotechnology.narpa.dto;

import java.io.Serializable;

import it.rotechnology.narpa.model.Funzione;

public class FunzioneDTO implements Serializable{

	private String codice;
	private String descrizione;
	
	public FunzioneDTO(Funzione f) {
		this.codice= f.getCodice();
		this.descrizione= f.getDescrizione();
	}
	
}
