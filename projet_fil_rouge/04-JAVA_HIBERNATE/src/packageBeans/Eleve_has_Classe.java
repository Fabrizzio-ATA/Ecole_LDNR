package packageBeans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Eleve_has_Classe database table.
 * 
 */
@Entity
@Table(name="Eleve_has_Classe")
@NamedQuery(name="Eleve_has_Classe.findAll", query="SELECT e FROM Eleve_has_Classe e")
public class Eleve_has_Classe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to Classe
	@ManyToOne
	@JoinColumn(name="Classe_id", nullable=false)
	private Classe classe;

	//bi-directional many-to-one association to Eleve
	@ManyToOne
	@JoinColumn(name="Eleve_id", nullable=false)
	private Eleve eleve;

	public Eleve_has_Classe() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Classe getClasse() {
		return this.classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Eleve getEleve() {
		return this.eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

}