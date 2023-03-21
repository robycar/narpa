package it.rotechnology.narpa.dto;

import it.rotechnology.narpa.model.Profilo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfiloDTO {

	private Long id;
	private String nome;
	private String descrizione;

	public ProfiloDTO(Profilo profilo) {
		super();
		this.id = profilo.getId();
		this.nome = profilo.getNome();
		this.descrizione = profilo.getDescrizione();
	}

	public ProfiloDTO(Long id) {
		super();
		this.id = id;
	}
}
