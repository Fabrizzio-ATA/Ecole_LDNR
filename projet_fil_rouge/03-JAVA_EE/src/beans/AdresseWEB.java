package beans;

import java.io.Serializable;

public class AdresseWEB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String voie;
	private String codePostal;
	private String ville;
	
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) {
		this.voie = voie;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
}
