package beans;

import packageDAO.StatutEleve;

public class UtilisateurBEAN {

    private String email;//64
    private String motDePasse;//16
    private StatutEleve status;

    public void setEmail(String email) {
	this.email = email;
    }
    public String getEmail() {
	return email;
    }

    public void setMotDePasse(String motDePasse) {
	this.motDePasse = motDePasse;
    }
    public String getMotDePasse() {
	return motDePasse;
    }

    public void setNom(StatutEleve status) {
	this.status = status;
    }
    public StatutEleve getNom() {
	return status;
    }
}