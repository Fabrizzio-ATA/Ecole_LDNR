package packageBeans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Classe database table.
 * 
 */
@Entity
@Table(name="Classe")
@NamedQuery(name="Classe.findAll", query="SELECT c FROM Classe c")
public class Classe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=3)
	private String niveau;

	@Column(nullable=false, length=5)
	private String nom;

	@Column(nullable=false, length=4)
	private String periode;

	//bi-directional many-to-one association to Eleve_has_Classe
	@OneToMany(mappedBy="classe")
	private List<Eleve_has_Classe> eleveHasClasses;

	//bi-directional one-to-one association to Enseignant
	@OneToOne
	@JoinColumn(name="Enseignant_id", nullable=false)
	private Enseignant enseignant;

	public Classe() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNiveau() {
		return this.niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPeriode() {
		return this.periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public List<Eleve_has_Classe> getEleveHasClasses() {
		return this.eleveHasClasses;
	}

	public void setEleveHasClasses(List<Eleve_has_Classe> eleveHasClasses) {
		this.eleveHasClasses = eleveHasClasses;
	}

	public Eleve_has_Classe addEleveHasClass(Eleve_has_Classe eleveHasClass) {
		getEleveHasClasses().add(eleveHasClass);
		eleveHasClass.setClasse(this);

		return eleveHasClass;
	}

	public Eleve_has_Classe removeEleveHasClass(Eleve_has_Classe eleveHasClass) {
		getEleveHasClasses().remove(eleveHasClass);
		eleveHasClass.setClasse(null);

		return eleveHasClass;
	}

	public Enseignant getEnseignant() {
		return this.enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

}