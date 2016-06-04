package servlets;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CiviliteWEB;


public class Civilite extends HttpServlet {
		
	private static final long serialVersionUID = 1L;
	
	private static final String CHAMP_DATE_NAISS = "dateNaissance";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_SEXE = "sexe";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Lit les données de la requête et enlève les espaces (trim)*/
		String dateNaissance = request.getParameter(CHAMP_DATE_NAISS).trim();
		String nom = request.getParameter(CHAMP_NOM).trim();
		String prenom = request.getParameter(CHAMP_PRENOM).trim();
		String sexe = request.getParameter(CHAMP_SEXE).trim();
		
		/* Une String pour passer un message à la vue */
		String msg=null;
        /* Un booleen pour préciser si il y a une erreur */
		boolean isError = true;

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
                    + "la date fournie n'est pas au bon format (aaaa-mm-jj) : " + dateNaissance + ". <br> "
                    + "Pour accéder de nouveau au formulaire de création d'un client : "
                    + "<a href=\"NewCivilite.jsp\">cliquez ici</a>.";
        } else {
    		civilite.setDateNaissance(dateNaissance);
        	
            msg = "Civilité correctement créée.";
            isError = false;
        }
        // On doit remplir le bean pour permettre la modification du formulaire par l'utilisateur
		civilite.setNom(nom);
		civilite.setPrenom(prenom);
		civilite.setSexe(sexe);
		
		// Ajout de l'attribut dans la requete pour le modele ou la vue
		request.setAttribute( "civilite", civilite);
		request.setAttribute( "message", msg);
		request.setAttribute("iserror", isError);
		
		// TODO Faire un try not null des champs de civilite sinon forward
		// La servlet propage la requete à la VUE (page JSP)
		this.getServletContext().getRequestDispatcher( "/WEB-INF/AfficherCivilite.jsp").
				forward( request, response );
	}
	
	private boolean isDateValid (String date)
	{	//REGEXP
		if (date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}"))
			return true;
		return false;
	}
	
}
