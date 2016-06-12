package beans;

public class EnseignantBEAN {

	private CiviliteBEAN civilite;
	private AdresseBEAN adresse;
	private UtilisateurBEAN utilisateur;
	
	public CiviliteBEAN getCivilite() {
		return civilite;
	}
	public void setCivilite(CiviliteBEAN civilite) {
		this.civilite = civilite;
	}
	public AdresseBEAN getAdresse() {
		return adresse;
	}
	public void setAdresse(AdresseBEAN adresse) {
		this.adresse = adresse;
	}
	public UtilisateurBEAN getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(UtilisateurBEAN utilisateur) {
		this.utilisateur = utilisateur;
	}
}
