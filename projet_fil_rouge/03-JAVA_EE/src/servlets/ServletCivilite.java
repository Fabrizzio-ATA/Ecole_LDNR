package servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CiviliteWEB;


public class ServletCivilite extends HttpServlet {
		
	private static final long serialVersionUID = 1L;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Création et initialisation du Bean
		CiviliteWEB civilite = new CiviliteWEB();
		civilite.setDateNaissance(request.getParameter("dateNaissance"));
		civilite.setNom(request.getParameter("nom"));
		civilite.setPrenom(request.getParameter("prenom"));
		civilite.setSexe(request.getParameter("sexe"));
		
		// Ajout de l'attribut dans la requete pour le modele ou la vue
		request.setAttribute( "civilite", civilite);
		
		// TODO Faire un try not null des champs de civilite sinon forward
		// La servlet propage la requete à la VUE (page JSP)
		this.getServletContext().getRequestDispatcher( "/WEB-INF/JSPCivilite.jsp").
				forward( request, response );
	}
	
}
