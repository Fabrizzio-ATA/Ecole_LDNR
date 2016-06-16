package packageBeans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Enseignant database table.
 * 
 */
@Entity
@Table(name="Enseignant")
@NamedQuery(name="Enseignant.findAll", query="SELECT e FROM Enseignant e")
public class Enseignant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional one-to-one association to Utilisateur
	@OneToOne
	@JoinColumn(name="Utilisateur_id", nullable=false)
	private Utilisateur utilisateur;

	//bi-directional one-to-one association to Classe
	@OneToOne(mappedBy="enseignant")
	private Classe classe;

	//bi-directional one-to-one association to Civilite
	@OneToOne
	@JoinColumn(name="Civilite_id", nullable=false)
	private Civilite civilite;

	//bi-directional one-to-one association to Adresse
	@OneToOne
	@JoinColumn(name="Adresse_id", nullable=false)
	private Adresse adresse;

	public Enseignant() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Classe getClasse() {
		return this.classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Civilite getCivilite() {
		return this.civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

}