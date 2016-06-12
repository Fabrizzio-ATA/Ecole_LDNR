package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.AdresseBEAN;
import beans.CiviliteBEAN;
import beans.EnseignantBEAN;
import beans.UtilisateurBEAN;

public class EnseignantForm {

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public EnseignantBEAN enregistrerEnseignant(HttpServletRequest request) {
	    
		// Création et initialisation du Bean
		EnseignantBEAN enseignantBEAN = new EnseignantBEAN();
		
		// Prepare les objets
		CiviliteForm civiliteForm = new CiviliteForm();
		UtilisateurForm utilisateurForm = new UtilisateurForm();
		AdresseForm adresseForm = new AdresseForm();

		
		//Traitement de la requête et récupération du bean en résultant
		CiviliteBEAN civiliteBEAN = civiliteForm.enregistrerCivilite(request);
		UtilisateurBEAN utilisateurBEAN = utilisateurForm.inscrireUtilisateur(request);
		AdresseBEAN adresseBEAN = adresseForm.enregistrerAdresse(request);		
		
		resultat = 	utilisateurForm.getResultat() + "\n" +
					civiliteForm.getResultat() + "\n" +
					adresseForm.getResultat();
		
		erreurs.putAll(utilisateurForm.getErreurs());
		erreurs.putAll(civiliteForm.getErreurs());
		erreurs.putAll(adresseForm.getErreurs());
		
		request.setAttribute( "utilisateur", utilisateurBEAN);
		request.setAttribute( "civilite", civiliteBEAN);
		request.setAttribute( "adresse", adresseBEAN);
		
		return enseignantBEAN;
	}

}
