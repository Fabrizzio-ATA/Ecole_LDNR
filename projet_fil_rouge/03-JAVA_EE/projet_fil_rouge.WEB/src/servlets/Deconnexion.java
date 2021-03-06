package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Deconnexion
 */
//@WebServlet("/URLDeconnexion")
public class Deconnexion extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4096757649950448031L;
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération et destruction de la session en cours */
		HttpSession session = request.getSession();
		session.invalidate();
		
		/* Redirection vers le formulaire de connexion */
		response.sendRedirect( getServletContext().getContextPath());
	}
}
