package it.rotechnology.narpa.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.rotechnology.narpa.model.Funzione;
import it.rotechnology.narpa.model.Ruolo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RuoloDTO implements Serializable { // e poi le funzioni

	private static final long serialVersionUID = 13456423873L;

	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private String descrizione;
	// private List<FunzioneDTO> funzioni;
	private List<String> funzioni; // Al chiamante serve solo il codice

	public RuoloDTO(Long id) {
		this.id = id;
	}

	public RuoloDTO(Ruolo r, boolean includeDetails) { // Per i dettagli deve essere sempre true come valore

		this.id = r.getId();
		this.nome = r.getNome();
		this.descrizione = r.getDescrizione();
		if (includeDetails) {
			this.funzioni = new ArrayList<>();
			if (r.getFunzioni() != null) {
				for (Funzione funzione : r.getFunzioni()) {
					// FunzioneDTO funzioneDTO = new FunzioneDTO(funzione);
					this.funzioni.add(funzione.getCodice());
				}
			}
		}
	}

	public RuoloDTO(Ruolo ruolo) { // Questo costruttore non mi prende i dettagli
		this(ruolo, false);
	}

}
