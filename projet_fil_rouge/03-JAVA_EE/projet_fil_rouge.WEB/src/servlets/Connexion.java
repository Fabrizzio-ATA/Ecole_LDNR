package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import forms.ConnexionForm;

public class Connexion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8766153369545932598L;
	
	public static final String ATT_USER         = "utilisateur";
	public static final String ATT_FORM         = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String REFERER = "pageReferer";
	public static final String VUE = "/index.jsp";
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// Récupération de la session
		HttpSession session = request.getSession();
		String pageReferent;
		
		if (session.getAttribute(REFERER) == null){
			pageReferent = request.getHeader("referer");
		}else {
			pageReferent = (String) session.getAttribute(REFERER);
		}
		
		// Prepare l'objet
		ConnexionForm form = new ConnexionForm();
		
		//Traitement de la requête et récupération du bean en résultant
		Utilisateur utilisateur = form.connecterUtilisateur(request);
		
		
		
		// Si validation ok alors ajout du bean utilisateur à la session sinon suppression
		if (form.getErreurs().isEmpty()){
			session.setAttribute(ATT_SESSION_USER, utilisateur);
			response.sendRedirect( pageReferent );
		}
		else {
			session.setAttribute(REFERER, pageReferent);
			session.setAttribute(ATT_SESSION_USER, null);
			request.setAttribute(ATT_FORM, form);
			this.getServletContext().getRequestDispatcher("/"+pageReferent.substring(pageReferent.lastIndexOf("/"))).forward(request, response);
		}
		
	}

}
