package beans;

import packageDAO.Role;

public class UtilisateurBEAN {

    private String email;//64
    private String motdepasse;//16
    private String confirmation;//16
    private Role role;

    public void setEmail(String email) {
	this.email = email;
    }
    public String getEmail() {
	return email;
    }
	public void setMotdepasse(String motdepasse) {
	this.motdepasse = motdepasse;
    }
    public String getMotdepasse() {
	return motdepasse;
    }
    public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
    public void setRole(Role role) {
	this.role = role;
    }
    public Role getRole() {
	return role;
    }
}