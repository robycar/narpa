package it.rotechnology.narpa.dto;

import java.util.List;
import java.util.stream.Collectors;

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
	private List <RuoloDTO> ruoli;

	public ProfiloDTO(Profilo profilo, boolean includeDetails) {
		super();
		this.id = profilo.getId();
		this.nome = profilo.getNome();
		this.descrizione = profilo.getDescrizione();
		if(includeDetails && profilo.getRuoli()!=null) {
			this.ruoli = profilo.getRuoli().stream().map(RuoloDTO::new).collect(Collectors.toList());
		}
	}
	
	public ProfiloDTO(Profilo profilo) {
		this(profilo,false);
	}

	public ProfiloDTO(Long id) {
		super();
		this.id = id;
	}
}
