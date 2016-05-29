package beans;

import java.io.Serializable;
import java.time.LocalDate;

public class CiviliteWEB implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nom;
	private String prenom;
	private String sexe;
	private LocalDate dateNaissance;
	//private String dateNaissance;
	
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

	public void setDateNaissance(String date) {
		// Date de type AAAA-MM-JJ
		this.dateNaissance = LocalDate.parse(date);
	}
}