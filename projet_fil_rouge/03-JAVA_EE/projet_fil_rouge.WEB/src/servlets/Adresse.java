package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdresseBEAN;
import beans.UtilisateurBEAN;
import forms.AdresseForm;
import forms.ConnexionForm;;

public class Adresse extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String ATT_FORM         = "form";
//	private static final String VUE = "/WEB-INF/AfficherAdresse.jsp";
	private static final String VUE = "/WEB-INF/inc/adresse.jsp";

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Prepare l'objet
		AdresseForm form = new AdresseForm();
		
		//Traitement de la requête et récupération du bean en résultant
		AdresseBEAN adresseBEAN = form.enregistrerAdresse(request);	

		// Ajout de l'attribut dans la requete pour le modele ou la vue
		
		request.setAttribute( "adresse", adresseBEAN);
		request.setAttribute(ATT_FORM, form);
		
		// La servlet propage la requete à la VUE (page JSP)
		this.getServletContext().getRequestDispatcher( VUE).
				forward( request, response );
	}
}
