package it.rotechnology.narpa.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name="PROFILO")
@Getter
@Setter
public class Profilo implements Serializable {
	
	private static final long serialVersionUID = 6341110375345452644L;
	
	private static final int NOME_LENGTH = 40;
	private static final int DESCRIZIONE_LENGTH = 128;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(length = NOME_LENGTH)
	private String nome;
	
	@Column(length= DESCRIZIONE_LENGTH)
	private String descrizione;
	
	@ManyToMany
	@JoinTable(name = "RUOLO_PROFILO", joinColumns = @JoinColumn(name = "PROFILO_ID"), inverseJoinColumns = @JoinColumn(name = "RUOLO_ID"))
	private List<Ruolo> ruoli;

}
