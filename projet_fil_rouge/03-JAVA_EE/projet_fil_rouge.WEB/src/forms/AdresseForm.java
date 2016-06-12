package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.AdresseBEAN;

public class AdresseForm {
	
	private static final String CHAMP_CP = "codePostal";
	private static final String CHAMP_VOIE = "voie";
	private static final String CHAMP_VILLE = "ville";
	private static final String CHAMP_TELEPHONE = "telephone";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	// TODO ajouter le contrôle de champ et enregistrer les erreur dans les variables locales du formulaire
	public AdresseBEAN enregistrerAdresse(HttpServletRequest request) {
	
		/* Lit les données de la requête et enlève les espaces (trim)*/
		String cp = getValeurChamp(request,CHAMP_CP);
		String voie = getValeurChamp(request, CHAMP_VOIE);
		String ville = getValeurChamp(request, CHAMP_VILLE);
		String telephone = getValeurChamp(request, CHAMP_TELEPHONE);
	    
		/* Une String pour passer un message à la vue */
	    String msg = null;
	    /* Un booleen pour préciser si il y a une erreur */
	    boolean isError = true;
	    
		// Création et initialisation du Bean
		AdresseBEAN adresseBEAN = new AdresseBEAN();
	
		/* Validation du champ cp. */
		try {
			validationCp( cp );
		} catch ( Exception e ) {
			setErreur( CHAMP_CP, e.getMessage() );
		}
		adresseBEAN.setCodePostal(cp);

		/* Validation du champ voie. */
		try {
			validationVoie( voie );
		} catch ( Exception e ) {
			setErreur( CHAMP_VOIE, e.getMessage() );
		}
		adresseBEAN.setVoie(voie);
		
		/* Validation du champ ville. */
		try {
			validationVille( ville );
		} catch ( Exception e ) {
			setErreur( CHAMP_VILLE, e.getMessage() );
		}
		adresseBEAN.setVille(ville);
		
		/* Validation du champ telephone. */
		try {
			validationTelephone( telephone );
		} catch ( Exception e ) {
			setErreur( CHAMP_TELEPHONE, e.getMessage() );
		}
		adresseBEAN.setTelephone(telephone);
		
		/* Initialisation du résultat global de la validation. */
		if ( erreurs.isEmpty() ) {
			resultat = "Succès formulaire adresse.";
		} else {
			resultat = "Échec formulaire adresse.";
		}
		
		/**
		 *  OLD CODE
		// TODO Faire un try not null des champs de civilite sinon forward
		//if (cp.isEmpty() || voie.isEmpty() || ville.isEmpty())
		if (null == cp || null == voie || null == ville)
		{
	        msg = "Erreur dans la création de l'adresse : "
	                + "vous n'avez pas rempli tous les champs obligatoires. <br> "
	                + "Pour accéder de nouveau au formulaire de création d'une adresse : "
	                + "<a href=\"NewAdresse.jsp\">cliquez ici</a>.";
		}
		else if (!isCodePostalValid(cp))
		{
	        msg = "Erreur dans la création de l'adresse : "
	                + "le code postal doit comporter 5 chiffres exactement. <br> "
	                + "Pour accéder de nouveau au formulaire de création d'une adresse : "
	                + "<a href=\"NewAdresse.jsp\">cliquez ici</a>.";
		}
		else {
	        msg = "Adresse correctement créée.";
	        isError = false;
	    }
		
	    // On doit remplir le bean pour permettre la modification du formulaire par l'utilisateur
		adresse.setCodePostal(cp);
		adresse.setVoie(voie);
		adresse.setVille(ville);
	
		// FIXME to be replaced 
		request.setAttribute( "message", msg );
		request.setAttribute("iserror", isError);
		*/
		
		return adresseBEAN;
	}	
	

	private void validationTelephone(String telephone) throws Exception {
		if ( telephone == null ) {
			throw new Exception( "telephone" );	
		}
		else if ( !telephone.matches("^[0-9]{10}$" ) ) {
			throw new Exception( "Format telephone : 10 chiffres");
		}		
	}
	private void validationVille(String ville) throws Exception {
		if ( ville == null ) {
			throw new Exception( "ville" );
		}			
	}
	private void validationVoie(String voie) throws Exception {
		if ( voie == null ) {
			throw new Exception( "voie" );
		}		
	}
	private void validationCp(String cp) throws Exception {
		if ( cp != null ) {
			if ( !isCodePostalValid(cp) ) {
				throw new Exception("Format cp : 5 chiffres" );
			}
		} else {
			throw new Exception( "cp" );
		}
	}
	private boolean isCodePostalValid(String cp)
	{
		// ^:début $:fin
		if (cp.matches("^[0-9]{5}$"))
			return true;
		return false;
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
