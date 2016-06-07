package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.UtilisateurBEAN;

public final class ConnexionForm {
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS  = "motdepasse";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	public UtilisateurBEAN connecterUtilisateur( HttpServletRequest request ) {
	
		/* Récupération des champs du formulaire */
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		UtilisateurBEAN utilisateurBEAN = new UtilisateurBEAN();
		
		/* Validation du champ email. */
		try {
			validationEmail( email );
		} catch ( Exception e ) {
			setErreur( CHAMP_EMAIL, e.getMessage() );
		}
		utilisateurBEAN.setEmail( email );
		
		/* Validation du champ mot de passe. */
		try {
			validationMotDePasse( motDePasse );
		} catch ( Exception e ) {
			setErreur( CHAMP_PASS, e.getMessage() );
		}
		utilisateurBEAN.setMotDePasse( motDePasse );
		
		/* Initialisation du résultat global de la validation. */
		if ( erreurs.isEmpty() ) {
			resultat = "Succès de la connexion.";
		} else {
			resultat = "Échec de la connexion.";
		}
		return utilisateurBEAN;
	}
	/**
	 * Valide l'adresse email saisie.
	 */
	private void validationEmail( String email ) throws Exception {
		if ( email == 
				null ) {
			throw new Exception( "mail" );
		} else if ( !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
			throw new Exception( "mail");
		}
	}
	/**
	 * Valide le mot de passe saisi.
	 */
	private void validationMotDePasse( String motDePasse ) throws Exception 
	{
		if ( motDePasse != null ) {
			if ( motDePasse.length() < 3 ) {
				throw new Exception("mot de passe" );
			}
		} else {
			throw new Exception( "mot de passe" );
		}
	}
	/*
	 * Ajoute un message correspondant au champ spécifié à la map des
erreurs.
	 */
	private void setErreur( String champ, String message ) {
		erreurs.put( champ, message );
	}
	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son 
contenu
	 * sinon.
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