package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.UtilisateurBEAN;
import packageDAO.Role;

public final class UtilisateurForm {
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS  = "motdepasse";
	public static final String CHAMP_CONF = "confirmation";
	public static final String CHAMP_ROLE = "role";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	public UtilisateurBEAN inscrireUtilisateur( HttpServletRequest request ) {
	
		/* Récupération des champs du formulaire */
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String motdepasse = getValeurChamp( request, CHAMP_PASS );
		String confirmation = getValeurChamp( request, CHAMP_CONF);
		String role = getValeurChamp( request, CHAMP_ROLE);

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
			validationMotdepasse( motdepasse, confirmation );
		} catch ( Exception e ) {
			setErreur( CHAMP_PASS, e.getMessage() );
		}
		utilisateurBEAN.setMotdepasse( motdepasse );

		/* Validation du champ role. */
		try {
			validationRole( role );
			utilisateurBEAN.setRole(Role.valueOf(role));
		} catch ( Exception e ) {
			setErreur( CHAMP_ROLE, e.getMessage() );
		}
		
		/* Initialisation du résultat global de la validation. */
		if ( erreurs.isEmpty() ) {
			resultat = "Succès de l'inscription.";
		} else {
			resultat = "Échec de l'inscription.";
		}
		return utilisateurBEAN;
	}
	private void validationRole(String role) throws Exception {
		if (role != null && role.length() != 0) {
			if (Role.valueOf(role) == null)
				throw new Exception(" role invalide :"
									+ Role.DIRECTEUR.toString() + " ou "
									+ Role.ENSEIGNANT.toString());
		}
		 else {
				throw new Exception(" role ");
		}
	}
	/**
	 * Valide l'adresse email saisie.
	 */
	private void validationEmail( String email ) throws Exception {
		if ( email != null && email.length() != 0 ) 
		{
			// [^.@] : tout sauf point ou arobase
			// * : 0 ou plus.
			if ( !email.matches("(^[^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)$") )
			{
				throw new Exception( "mail invalide" );
			}
		}
		else  {
			throw new Exception( "mail");
		}
	}
	/**
	 * Valide le mot de passe saisi.
	 */
	private void validationMotdepasse( String motdepasse, String confirmation ) throws Exception 
	{
		if (motdepasse != null && motdepasse.length() != 0 && confirmation != null && confirmation.length() != 0) {
			if (!motdepasse.equals(confirmation)) {
				throw new Exception("Les mots de passe entrés sont différents");
			} else if (motdepasse.length() < 3) {
				throw new Exception("Saisir au moins 3 caractères.");
			}
		} else {
			throw new Exception(" mot de passe.");
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