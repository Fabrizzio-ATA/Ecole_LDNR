package packageJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Singleton de connection
 * @author fabrizzio
 *
 */
public class JDBCConnection {

	/*
	 *  Des constantes de classe
	 */
	// Le driver JDBC vers la DB MySQL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	// L'URL de ma DB à contacter
	//		static final String DB_URL = "jdbc:mysql://localhost:3306/stag";
	static final String DB_URL = "jdbc:mysql://82.229.232.36:3306/db_ecole_test";	
	
	// Mes paramètres de connexion
	// Il faut passer par des propriétés
//	static final String USER;
//	static final String PASS;
	
	/**
	 * Constructeur Private
	 * @return
	 */
	private JDBCConnection(String USER, String PASS)
	{
		// Enregistrer le driver JDBC
		// PENSEZ à ajouter la bibliothèque de connexion
		// dans le BuildPath de votre projet
		try {
			Class.forName(JDBC_DRIVER);
				
			// Ouvrir une connexion
			System.out.println("Connection à la base de données...");
			connection = DriverManager.getConnection(DB_URL, USER,PASS);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Instance unique non initialisée
	 */
	private static Connection connection = null;
	private static JDBCConnection connectionDB = null;
	
	/**
	 * Methode d'accès à une connection avec définition du user et password
	 * @return connection
	 * 
	 * Attention à l'utilisation du synchronize qui peut alourdir l'exécution. Idéalement, ne synchroniser que l'appel au constructeur.
	 */
	public static synchronized Connection getInstance(String user, String pass)
	{
		if (null == connectionDB)
		{
			connectionDB = new JDBCConnection(user, pass);
		}
		return connection;
	}
	
	public static synchronized Connection getInstance()
	{
		if (null == connectionDB)
		{
			connectionDB = new JDBCConnection("Guest", "guest");
		}
		return connection;
	}
	
	public static synchronized void shutDownConnection()
	{
		try{
			if(null != connectionDB )
				connection.close();
		} catch(SQLException se) {
			se.printStackTrace();
		}
	}
		
//	public static void main(String[] args) 
//	{
//
//		String USER = args[0].toString();
//		String PASS = args[1].toString();
//		
//		Connection conn = null;
//		Statement stmt = null;
//		
//		System.out.println("USER = " + USER);
//		
//		try{
//			// Enregistrer le driver JDBC
//			// PENSEZ à ajouter la bibliothèque de connexion
//			// dans le BuildPath de votre projet
//			Class.forName(JDBC_DRIVER);
//
//			// Ouvrir une connexion
//			System.out.println("Connection à la base de données...");
//			conn = DriverManager.getConnection(DB_URL, USER,PASS);
//
//				// Exécuter une requête
//				System.out.println("Statement en cours de création...");
//				stmt = conn.createStatement();			
//				String sql= "SELECT nom_Classe FROM Classe";
//				ResultSet rs = stmt.executeQuery(sql);
//	
//				while(rs.next()){
//					// Récupération par nom de champ
//					String classe  = rs.getString("nom_Classe");
//					
//					// Affichage des valeurs
//					System.out.println("CLASSE: " + classe);
//				}
//				
//				// Nettoyage de l'environnement
//				rs.close();
//				stmt.close();
//				
//			conn.close();
//			
//		} catch(SQLException se) {
//			// Traiter les erreurs JDBC
//			se.printStackTrace();
//		} catch(Exception e) {
//			// Traiter les erreurs pour Class.forName()
//			e.printStackTrace();
//		} finally {
//			// On ferme les ressources dans le bloc finally
//			try{
//				if(stmt!=null)
//					stmt.close();
//			} catch(SQLException se2) {
//				// Il n'y a rien qu'on puisse faire ici
//			}
//			try{
//				if(conn!=null)
//					conn.close();
//			} catch(SQLException se) {
//				se.printStackTrace();
//			} // Fin du bloc finally
//		} // Fin du bloc try
//		System.out.println("Au revoir !");
//	}
}
