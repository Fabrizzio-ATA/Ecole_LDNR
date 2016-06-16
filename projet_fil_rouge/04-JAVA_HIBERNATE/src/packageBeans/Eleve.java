package packageBeans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Eleve database table.
 * 
 */
@Entity
@Table(name="Eleve")
@NamedQuery(name="Eleve.findAll", query="SELECT e FROM Eleve e")
public class Eleve implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="StatutEleve", nullable=false, length=16)
	private String statutEleve;

	//bi-directional many-to-one association to Eleve_has_Adresse
	@OneToMany(mappedBy="eleve")
	private List<Eleve_has_Adresse> eleveHasAdresses;

	//bi-directional many-to-one association to Eleve_has_Classe
	@OneToMany(mappedBy="eleve")
	private List<Eleve_has_Classe> eleveHasClasses;

	//bi-directional one-to-one association to Civilite
	@OneToOne
	@JoinColumn(name="Civilite_id", nullable=false)
	private Civilite civilite;

	public Eleve() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatutEleve() {
		return this.statutEleve;
	}

	public void setStatutEleve(String statutEleve) {
		this.statutEleve = statutEleve;
	}

	public List<Eleve_has_Adresse> getEleveHasAdresses() {
		return this.eleveHasAdresses;
	}

	public void setEleveHasAdresses(List<Eleve_has_Adresse> eleveHasAdresses) {
		this.eleveHasAdresses = eleveHasAdresses;
	}

	public Eleve_has_Adresse addEleveHasAdress(Eleve_has_Adresse eleveHasAdress) {
		getEleveHasAdresses().add(eleveHasAdress);
		eleveHasAdress.setEleve(this);

		return eleveHasAdress;
	}

	public Eleve_has_Adresse removeEleveHasAdress(Eleve_has_Adresse eleveHasAdress) {
		getEleveHasAdresses().remove(eleveHasAdress);
		eleveHasAdress.setEleve(null);

		return eleveHasAdress;
	}

	public List<Eleve_has_Classe> getEleveHasClasses() {
		return this.eleveHasClasses;
	}

	public void setEleveHasClasses(List<Eleve_has_Classe> eleveHasClasses) {
		this.eleveHasClasses = eleveHasClasses;
	}

	public Eleve_has_Classe addEleveHasClass(Eleve_has_Classe eleveHasClass) {
		getEleveHasClasses().add(eleveHasClass);
		eleveHasClass.setEleve(this);

		return eleveHasClass;
	}

	public Eleve_has_Classe removeEleveHasClass(Eleve_has_Classe eleveHasClass) {
		getEleveHasClasses().remove(eleveHasClass);
		eleveHasClass.setEleve(null);

		return eleveHasClass;
	}

	public Civilite getCivilite() {
		return this.civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

}