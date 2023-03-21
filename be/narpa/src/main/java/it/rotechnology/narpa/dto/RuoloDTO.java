package it.rotechnology.narpa.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.rotechnology.narpa.model.Funzione;
import it.rotechnology.narpa.model.Ruolo;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RuoloDTO implements Serializable{ //e poi le funzioni
	
	private Long id;
	private String nome;
	private String descrizione;
	private Set<FunzioneDTO> funzioni;
	
	public RuoloDTO(Long id) {
		this.id = id;
	}
	
	public RuoloDTO(Ruolo r) {
		this.id= r.getId();
		this.nome= r.getNome();
		this.descrizione= r.getDescrizione();
		this.funzioni= new HashSet<>();
		if(r.getFunzioni()!= null) {
			for(Funzione funzione: r.getFunzioni())
			{
				FunzioneDTO funzioneDTO= new FunzioneDTO(funzione);
				this.funzioni.add(funzioneDTO);
			}
		}
	}
	
}
