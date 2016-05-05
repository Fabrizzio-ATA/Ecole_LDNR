package packageJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) 
	{

		String USER = args[0].toString();
		String PASS = args[1].toString();
		
		Statement stmt = null;
		
		Connection conn = JDBCConnection.getInstance(USER, PASS);
		
		if (null != conn)
		{
			
			// Exécuter une requête
	
			System.out.println("Statement en cours de création...");
			try {
				stmt = conn.createStatement();
						
				String sql= "SELECT nom FROM Classe";
				ResultSet rs = stmt.executeQuery(sql);
		
				while(rs.next()){
					// Récupération par nom de champ
					String classe  = rs.getString("nom");
					
					// Affichage des valeurs
					System.out.println("CLASSE: " + classe);
				}
				
				// Nettoyage de l'environnement
				rs.close();
				stmt.close();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
