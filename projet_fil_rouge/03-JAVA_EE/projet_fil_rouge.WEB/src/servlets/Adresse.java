package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdresseWEB;;

public class Adresse extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String CHAMP_CP = "codePostal";
	private static final String CHAMP_VOIE = "voie";
	private static final String CHAMP_VILLE = "ville";

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Lit les données de la requête et enlève les espaces (trim)*/
		String cp = request.getParameter(CHAMP_CP).trim();
		String voie = request.getParameter(CHAMP_VOIE).trim();
		String ville = request.getParameter(CHAMP_VILLE).trim();
        
		/* Une String pour passer un message à la vue */
        String msg = null;
        /* Un booleen pour préciser si il y a une erreur */
        boolean isError = true;
        
		// Création et initialisation du Bean
		AdresseWEB adresse = new AdresseWEB();

		// TODO Faire un try not null des champs de civilite sinon forward
		if (cp.isEmpty() || voie.isEmpty() || ville.isEmpty())
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

		// Ajout de l'attribut dans la requete pour le modele ou la vue
		request.setAttribute( "adresse", adresse);
		request.setAttribute( "message", msg );
		request.setAttribute("iserror", isError);
		
		// La servlet propage la requete à la VUE (page JSP)
		this.getServletContext().getRequestDispatcher( "/WEB-INF/AfficherAdresse.jsp").
				forward( request, response );
	}
	
	private boolean isCodePostalValid(String cp)
	{
		// ^:début $:fin
		if (cp.matches("^[0-9]{5}$"))
			return true;
		return false;
	}
}
