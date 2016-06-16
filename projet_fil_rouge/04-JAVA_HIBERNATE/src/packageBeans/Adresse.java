package packageBeans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Adresse database table.
 * 
 */
@Entity
@Table(name="Adresse")
@NamedQuery(name="Adresse.findAll", query="SELECT a FROM Adresse a")
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=8)
	private String cp;

	@Column(length=16)
	private String telephone;

	@Column(nullable=false, length=45)
	private String ville;

	@Column(nullable=false, length=45)
	private String voie;

	//bi-directional many-to-one association to Eleve_has_Adresse
	@OneToMany(mappedBy="adresse")
	private List<Eleve_has_Adresse> eleveHasAdresses;

	//bi-directional one-to-one association to Enseignant
	@OneToOne(mappedBy="adresse")
	private Enseignant enseignant;

	public Adresse() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getVoie() {
		return this.voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public List<Eleve_has_Adresse> getEleveHasAdresses() {
		return this.eleveHasAdresses;
	}

	public void setEleveHasAdresses(List<Eleve_has_Adresse> eleveHasAdresses) {
		this.eleveHasAdresses = eleveHasAdresses;
	}

	public Eleve_has_Adresse addEleveHasAdress(Eleve_has_Adresse eleveHasAdress) {
		getEleveHasAdresses().add(eleveHasAdress);
		eleveHasAdress.setAdresse(this);

		return eleveHasAdress;
	}

	public Eleve_has_Adresse removeEleveHasAdress(Eleve_has_Adresse eleveHasAdress) {
		getEleveHasAdresses().remove(eleveHasAdress);
		eleveHasAdress.setAdresse(null);

		return eleveHasAdress;
	}

	public Enseignant getEnseignant() {
		return this.enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

}