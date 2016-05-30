package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdresseWEB;;

public class ServletAdresse extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Lit les données de la requête et enlève les espaces (trim)*/
		String cp = request.getParameter("codePostal").trim();
		String voie = request.getParameter("voie").trim();
		String ville = request.getParameter("ville");
        
		/* Une String pour passer un message à la vue */
        String msg = null;
        
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
            
    		adresse.setCodePostal(cp);
    		adresse.setVoie(voie);
    		adresse.setVille(ville);
        }

		// Ajout de l'attribut dans la requete pour le modele ou la vue
		request.setAttribute( "adresse", adresse);
		request.setAttribute( "message", msg );
		
		// La servlet propage la requete à la VUE (page JSP)
		this.getServletContext().getRequestDispatcher( "/WEB-INF/JSPAdresse.jsp").
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
