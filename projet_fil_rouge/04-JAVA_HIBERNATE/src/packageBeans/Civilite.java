package packageBeans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Civilite database table.
 * 
 */
@Entity
@Table(name="Civilite")
@NamedQuery(name="Civilite.findAll", query="SELECT c FROM Civilite c")
public class Civilite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_naiss", nullable=false)
	private Date dateNaiss;

	@Column(nullable=false, length=45)
	private String nom;

	@Column(nullable=false, length=45)
	private String prenom;

	@Column(nullable=false, length=1)
	private String sexe;

	//bi-directional one-to-one association to Eleve
	@OneToOne(mappedBy="civilite")
	private Eleve eleve;

	//bi-directional one-to-one association to Enseignant
	@OneToOne(mappedBy="civilite")
	private Enseignant enseignant;

	public Civilite() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateNaiss() {
		return this.dateNaiss;
	}

	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Eleve getEleve() {
		return this.eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Enseignant getEnseignant() {
		return this.enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

}