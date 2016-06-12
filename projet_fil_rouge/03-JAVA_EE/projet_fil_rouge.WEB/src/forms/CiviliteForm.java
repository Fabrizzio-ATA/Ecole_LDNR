package forms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sun.org.apache.xerces.internal.impl.validation.ValidationState;

import beans.AdresseBEAN;
import beans.CiviliteBEAN;

public class CiviliteForm {

	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_SEXE = "sexe";
	private static final String CHAMP_DATE_NAISS = "dateNaissance";

	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	// TODO ajouter le contrôle de champ et enregistrer les erreur dans les variables locales du formulaire
	public CiviliteBEAN enregistrerCivilite(HttpServletRequest request) {
	
		/* Lit les données de la requête et enlève les espaces (trim)*/
		String nom = getValeurChamp(request,CHAMP_NOM);
		String prenom = getValeurChamp(request,CHAMP_PRENOM);
		String sexe = getValeurChamp(request,CHAMP_SEXE);
		String dateNaissance = getValeurChamp(request,CHAMP_DATE_NAISS);


		// Création et initialisation du Bean
		CiviliteBEAN civiliteBEAN = new CiviliteBEAN();
			
		/* Validation du champ nom. */
		try {
			validationNom( nom );
		} catch ( Exception e ) {
			setErreur( CHAMP_NOM, e.getMessage() );
		}
		civiliteBEAN.setNom(nom);

		/* Validation du champ prenom. */
		try {
			validationPrenom( prenom );
		} catch ( Exception e ) {
			setErreur( CHAMP_PRENOM, e.getMessage() );
		}
		civiliteBEAN.setPrenom(prenom);
		
		/* Validation du champ sexe. */
		try {
			validationSexe( sexe );
		} catch ( Exception e ) {
			setErreur( CHAMP_SEXE, e.getMessage() );
		}
		civiliteBEAN.setSexe(sexe);
		
		/* Validation du champ dateNaissance. */
		try {
			validationDateNaissance( dateNaissance);
			// On enregistre la date naissance ici pour éviter les exceptions
			civiliteBEAN.setDateNaissance(LocalDate.parse(dateNaissance));
		} catch ( Exception e ) {
			setErreur( CHAMP_DATE_NAISS, e.getMessage() );
		}
		
		/* Initialisation du résultat global de la validation. */
		if ( erreurs.isEmpty() ) {
			resultat = "Succès formulaire civilite.";
		} else {
			resultat = "Échec formulaire civilite.";
		}
		
		return civiliteBEAN;
	}
	
	
	

	 private void validationNom(String nom) throws Exception {
			if ( nom == null ) {
				throw new Exception( "nom" );
			}		
	}
	private void validationPrenom(String prenom) throws Exception {
		if ( prenom == null ) {
			throw new Exception( "prenom" );
		}		
	}
	private void validationSexe(String sexe) throws Exception {
		if ( sexe == null ) {
			throw new Exception( "F ou G" );
		}		
	}
	private void validationDateNaissance(String dateNaissance) throws Exception {
		if ( dateNaissance == null ) {
			throw new Exception( "Format date de naissance : AAAA-MM-JJ" );
		}		
	}
	
	/* Ajoute un message correspondant au champ spécifié à la map des erreurs. */
	private void setErreur( String champ, String message ) {
	 		erreurs.put( champ, message );
	}
	
	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu sinon.
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} else {
			return valeur;
		}
	}

}
