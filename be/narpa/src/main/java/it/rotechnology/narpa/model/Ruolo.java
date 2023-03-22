package it.rotechnology.narpa.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="RUOLO")
@Getter
@Setter
public class Ruolo implements Serializable {
	private static final long serialVersionUID = -5771684324058623918L;
	public static final int NOME_LENGTH = 50;
	public static final int DESCRIZIONE_LENGTH = 128;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOME", length = NOME_LENGTH)
	private String nome;
	
	@Column(length=DESCRIZIONE_LENGTH)
	private String descrizione;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "AUTORIZZAZIONE_RUOLO", 
		joinColumns = @JoinColumn(name = "RUOLO_ID"), 
		inverseJoinColumns = @JoinColumn(name="FUNZIONE_CODICE"))
	private List<Funzione> funzioni;
	
}
