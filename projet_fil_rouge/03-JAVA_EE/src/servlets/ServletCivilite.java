package servlets;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CiviliteWEB;


public class ServletCivilite extends HttpServlet {
		
	private static final long serialVersionUID = 1L;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Lit les données de la requête et enlève les espaces (trim)*/
		String dateNaissance = request.getParameter("dateNaissance").trim();
		String nom = request.getParameter("nom").trim();
		String prenom = request.getParameter("prenom").trim();
		String sexe = request.getParameter("sexe").trim();
		
		/* Une String pour passer un message à la vue */
		String msg=null;

		// Création et initialisation du Bean
		CiviliteWEB civilite = new CiviliteWEB();
		
		// Vérification données
        if ( nom.isEmpty() || prenom.isEmpty() || sexe.isEmpty() || dateNaissance.isEmpty() ) {
            msg = "Erreur dans la création de la civilité : "
                    + "vous n'avez pas rempli tous les champs obligatoires. <br> "
                    + "Pour accéder de nouveau au formulaire de création d'un client : "
                    + "<a href=\"NewCivilite.jsp\">cliquez ici</a>.";
        } else if ( !isDateValid(dateNaissance) ) {
            msg = "Erreur dans la création de la civilité : "
                    + "la date fournie n'est pas au bon format (jj/mm/aaaa) : " + dateNaissance + ". <br> "
                    + "Pour accéder de nouveau au formulaire de création d'un client : "
                    + "<a href=\"NewCivilite.jsp\">cliquez ici</a>.";
        } else {
            msg = "Civilité correctement créée.";
        
			civilite.setDateNaissance(dateNaissance);
			civilite.setNom(nom);
			civilite.setPrenom(prenom);
			civilite.setSexe(sexe);
        }
		
		// Ajout de l'attribut dans la requete pour le modele ou la vue
		request.setAttribute( "civilite", civilite);
		request.setAttribute( "message", msg);
		
		// TODO Faire un try not null des champs de civilite sinon forward
		// La servlet propage la requete à la VUE (page JSP)
		this.getServletContext().getRequestDispatcher( "/WEB-INF/JSPCivilite.jsp").
				forward( request, response );
	}
	
	private boolean isDateValid (String date)
	{	//REGEXP
		if (date.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}"))
			return true;
		return false;
	}
	
}
