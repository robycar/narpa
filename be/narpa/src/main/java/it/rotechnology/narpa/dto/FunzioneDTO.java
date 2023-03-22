package it.rotechnology.narpa.dto;

import java.io.Serializable;

import it.rotechnology.narpa.model.Funzione;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FunzioneDTO implements Serializable{

	private static final long serialVersionUID = 16798347592L;
	
	private String codice;
	private String descrizione;
	
	public FunzioneDTO(Funzione f) {
		this.codice= f.getCodice();
		this.descrizione= f.getDescrizione();
	}
	
}
