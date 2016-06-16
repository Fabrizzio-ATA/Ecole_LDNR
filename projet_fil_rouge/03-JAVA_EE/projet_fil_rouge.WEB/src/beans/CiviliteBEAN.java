package beans;

import java.io.Serializable;
import java.time.LocalDate;

public class CiviliteBEAN implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nom;
	private String prenom;
	private String sexe;
	private LocalDate dateNaissance;
	private int id;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate date) {
		this.dateNaissance = date;
	}
}
