package packageBeans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Eleve_has_Adresse database table.
 * 
 */
@Entity
@Table(name="Eleve_has_Adresse")
@NamedQuery(name="Eleve_has_Adresse.findAll", query="SELECT e FROM Eleve_has_Adresse e")
public class Eleve_has_Adresse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to Adresse
	@ManyToOne
	@JoinColumn(name="Adresse_id", nullable=false)
	private Adresse adresse;

	//bi-directional many-to-one association to Eleve
	@ManyToOne
	@JoinColumn(name="Eleve_id", nullable=false)
	private Eleve eleve;

	public Eleve_has_Adresse() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Eleve getEleve() {
		return this.eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

}