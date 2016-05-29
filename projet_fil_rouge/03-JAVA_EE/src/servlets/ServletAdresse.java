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
		
		// Création et initialisation du Bean
		AdresseWEB adresse = new AdresseWEB();
		adresse.setCodePostal(request.getParameter("codePostal"));
		adresse.setVoie(request.getParameter("voie"));
		adresse.setVille(request.getParameter("ville"));
		
		// Ajout de l'attribut dans la requete pour le modele ou la vue
		request.setAttribute( "adresse", adresse);
		
		// TODO Faire un try not null des champs de civilite sinon forward
		// La servlet propage la requete à la VUE (page JSP)
		this.getServletContext().getRequestDispatcher( "/WEB-INF/JSPAdresse.jsp").
				forward( request, response );
	}
}
