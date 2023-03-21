package it.rotechnology.narpa.model;

import java.io.Serializable;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="UTENTE")
@Getter
@Setter
public class Utente  implements Serializable {
	private static final long serialVersionUID = -6114287442328258598L;
	public static final int USERNAME_LENGTH = 25;
	public static final int PASWORD_LENGTH = 100;
	public static final int RAW_PASWORD_LENGTH = (PASWORD_LENGTH-8)/4;
	public static final int COGNOME_LENGTH = 50;
	public static final int NOME_LENGTH = 50;
	public static final int AZIENDA_LENGTH = 70;
	public static final int EMAIL_LENGTH = 80;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	@Getter @Setter
	private Long id;
	
	@Column(length = USERNAME_LENGTH)
	private String username;
	
	@Column(length=PASWORD_LENGTH)
	private String password;
	
	@Column(length=COGNOME_LENGTH)
	private String cognome;
	
	@Column(length=NOME_LENGTH)
	private String nome;
	
	@Column(length=EMAIL_LENGTH)
	private String email;
	
	@ManyToOne
	@JoinColumn(name="PROFILO_ID")
	private Profilo profilo;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "AUTORIZZAZIONE_UTENTE",
		joinColumns = @JoinColumn(name="UTENTE_ID"),
		inverseJoinColumns = @JoinColumn(name="FUNZIONE_CODICE"))
	private Set<Funzione> funzioni;
}
