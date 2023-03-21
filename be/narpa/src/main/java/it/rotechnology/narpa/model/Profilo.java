package it.rotechnology.narpa.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="PROFILO")
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

}
