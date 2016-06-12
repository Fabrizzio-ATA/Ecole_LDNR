package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.EnseignantBEAN;
import forms.EnseignantForm;

public class Enseignant extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final String ATT_FORM         = "form";
	private static final String VUE = "/WEB-INF/inc/enseignant.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Prepare l'objet
		EnseignantForm form = new EnseignantForm();
		
		//Traitement de la requête et récupération du bean en résultant
		EnseignantBEAN enseignantBEAN = form.enregistrerEnseignant(request);	

		// Ajout de l'attribut dans la requete pour le modele ou la vue
		// FIXME (les autres attributs sont ajoutés directement par le formulaire)
		request.setAttribute( "enseignant", enseignantBEAN);
		
		
		request.setAttribute(ATT_FORM, form);
		
		// La servlet propage la requete à la VUE (page JSP)
		this.getServletContext().getRequestDispatcher( VUE).
				forward( request, response );
	}

}
