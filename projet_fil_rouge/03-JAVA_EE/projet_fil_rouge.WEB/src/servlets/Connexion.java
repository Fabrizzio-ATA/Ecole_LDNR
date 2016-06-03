package servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import forms.ConnexionForm;


@SuppressWarnings("serial")
public class Connexion extends HttpServlet {
	
	public static final String ATT_USER         = "utilisateur";
	public static final String ATT_FORM         = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE              = "/WEB-INF/connexion.jsp";

	public void doGet( HttpServletRequest request, HttpServletResponse 
			response ) throws ServletException, IOException {
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher( VUE ).forward(request, response );
	}

	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// Prepare l'objet
		ConnexionForm form = new ConnexionForm();
		
		//Traitement de la requête et récupération du bean en résultant
		Utilisateur utilisateur = form.connecterUtilisateur(request);
		
		// Récupération de la session
		HttpSession session = request.getSession();
		
		
		// Si validation ok alors ajout du bean utilisateur à la session sinon suppression
		if (form.getErreurs().isEmpty()){
			
			session.setAttribute(ATT_SESSION_USER, utilisateur);
		}
		else {
			session.setAttribute(ATT_SESSION_USER, null);
		}
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, utilisateur);
		this.getServletContext().getRequestDispatcher(request.getHeader("referer")).forward(request, response);
	}
	
}