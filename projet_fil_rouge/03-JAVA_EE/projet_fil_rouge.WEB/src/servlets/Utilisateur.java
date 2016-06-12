package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UtilisateurBEAN;
import forms.UtilisateurForm;
import packageORM.UtilisateurORM;

public class Utilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ATT_FORM         = "form";

	private static final String VUE_MENU = "/menuUtilisateur.jsp";
	private static final String VUE_LIST = "/WEB-INF/listUtilisateur.jsp";
	private static final String VUE_INSERT = "/WEB-INF/inc/utilisateur.jsp";
	

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String VUE = VUE_LIST;

		if (request.getParameter("action").equals("List"))
		{
			
			ArrayList<UtilisateurORM> tabORM = null;
			ArrayList<UtilisateurBEAN> tabBEAN = new ArrayList<UtilisateurBEAN>();
			UtilisateurBEAN objBEAN = null;
			try {
				tabORM = UtilisateurORM.read();
				for (UtilisateurORM objORM : tabORM)
				{
					objBEAN = new UtilisateurBEAN();
					objBEAN.setEmail(objORM.getLogin());
					objBEAN.setMotdepasse(objORM.getPassword());
					objBEAN.setRole(objORM.getRole());
					
					tabBEAN.add(objBEAN);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Ajout de l'attribut dans la requete pour le modele ou la vue
			request.setAttribute( "liste", tabBEAN);
			
		}
		else if (request.getParameter("action").equals("Insert"))
		{
			// Prepare l'objet
			UtilisateurForm form = new UtilisateurForm();
			
			//Traitement de la requête et récupération du bean en résultant
			UtilisateurBEAN utilisateurBEAN = form.inscrireUtilisateur(request);
			
			if (form.getErreurs().isEmpty())
			{
				try {
					UtilisateurORM.create(utilisateurBEAN.getEmail(), utilisateurBEAN.getMotdepasse(), utilisateurBEAN.getRole());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO ajouter l'affichage de l'objet ajouté
				VUE = VUE_MENU;
			}
			else
			{
				// Ajout de l'attribut dans la requete pour le modele ou la vue
				request.setAttribute( "utilisateur", utilisateurBEAN);
				request.setAttribute(ATT_FORM, form);
				VUE = VUE_INSERT;
			}
		}
		
		// La servlet propage la requete à la VUE (page JSP)
		this.getServletContext().getRequestDispatcher( VUE ).
						forward( request, response );
	}
}
