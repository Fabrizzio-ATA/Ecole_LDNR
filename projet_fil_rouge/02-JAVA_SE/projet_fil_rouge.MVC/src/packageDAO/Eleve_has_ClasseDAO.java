package packageDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import packageJDBC.JDBCConnection;

/*******************************************************************************

 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Eleve_has_Classe.
 * 
 * @author Eyf_Gy
 */
public class Eleve_has_ClasseDAO implements InterfaceDb {
	
	private static String table = "Eleve_has_Classe";
	
	/**
	 * Description of the property id.
	 */
	private Integer id = Integer.valueOf(0);

	/**
	 * Description of the property id_Eleve.
	 */
	private Integer id_Eleve;

	/**
	 * Description of the property id_Adresse.
	 */
	private Integer id_Classe;


	/**
	 * 
	 * @param id
	 * @param id_Eleve
	 * @param id_Classe
	 */
	
	public Eleve_has_ClasseDAO(Integer id_Eleve, Integer id_Classe) {
		setId_Eleve(id_Eleve);
		setId_Classe(id_Classe);
	}
	
	private Eleve_has_ClasseDAO(Integer id, Integer id_Eleve, Integer id_Classe) {
		this(id_Eleve, id_Classe);
		setId(id);
	}

	/**
	 * Description of the method dbInsert.
	 * @return 
	 */
	public boolean dbInsert() {
		// Start of user code for method dbInsert
		boolean retBool = false;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "INSERT INTO "+table+" "
								+"(Eleve_id, Classe_id) "
								+"VALUES(?,?)";
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, this.getId_Eleve());
			pstmt.setInt(2, this.getId_Classe());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			
			if (rs.first()){
				this.setId(rs.getInt(1)); 
			}
			
			rs.close();
			pstmt.close();
			retBool = true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retBool;
		// End of user code
	}

	/**
	 * Description of the method dbSelectFromId.
	 * @param id 
	 * @return ClasseDAO
	 */
	public static Eleve_has_ClasseDAO dbSelectFromId(int id) {
		Eleve_has_ClasseDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table+" WHERE id="+id;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.first()){
				retObj = new Eleve_has_ClasseDAO(rs.getInt("id"),
										rs.getInt("Eleve_id"),
										rs.getInt("Classe_id"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retObj;
		// Start of user code for method dbSelectFromId
		// End of user code
	}
	
	/**
	 * Description of the method dbSelectAll.
	 * @return HashSet <Eleve_has_ClasseDAO>
	 */
	public static HashSet <Eleve_has_ClasseDAO> dbSelectAll() {
		HashSet<Eleve_has_ClasseDAO> tabEleve_has_Classe = new HashSet<Eleve_has_ClasseDAO>();
		Eleve_has_ClasseDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				while(rs.next()){
					retObj = new Eleve_has_ClasseDAO(rs.getInt("id"),
														rs.getInt("Eleve_id"),
														rs.getInt("Classe_id"));
					tabEleve_has_Classe.add(retObj);
				}
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tabEleve_has_Classe;
		// Start of user code for method dbSelectFromId
		// End of user code
	}

	/**
	 * Description of the method dbDeleteFromId.
	 * @param id 
	 * @return 
	 */
	public static boolean dbDeleteFromId(int id) {
		// Start of user code for method dbDeleteFromId
		boolean dbDeleteFromId = false;
		
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "DELETE FROM "+table+" WHERE id="+id;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			dbDeleteFromId =(stmt.executeUpdate(sql) == 1);
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbDeleteFromId;
		// End of user code
	}

	/**
	 * Description of the method dbUpdate.
	 * @return 
	 */
	public boolean dbUpdate() {
		// Start of user code for method dbUpdate
		int updateRowCount = 0;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "UPDATE "+table+" SET Eleve_id=?, Classe_id=? WHERE id="+getId();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, this.getId_Eleve());
			pstmt.setInt(2, this.getId_Classe());
			updateRowCount = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (updateRowCount==1);
		// End of user code
	}


	public static boolean dbExistFromId(Integer id) {
		boolean retBool = false; 
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table+" WHERE id="+id;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.first()){
				retBool = true;
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retBool;
	}
	
	public boolean hasSameContent(Eleve_has_ClasseDAO obj){
		return(this.id_Eleve.equals(obj.getId_Eleve())
				&& this.id_Classe.equals(obj.getId_Classe()));
	}
	
	// Start of user code (user defined methods for AdresseDAO)

	// End of user code
	/**
	 * Returns id.
	 * @return id 
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Sets a value to attribute id. 
	 * @param newId 
	 */
	public void setId(Integer newId) {
		this.id = newId;
	}

	/**
	 * Returns id_Eleve.
	 * @return id_Eleve 
	 */
	public Integer getId_Eleve() {
		return this.id_Eleve;
	}

	/**
	 * Sets a value to attribute id_Eleve. 
	 * @param id_Eleve 
	 */
	public void setId_Eleve(Integer id_Eleve) {
		this.id_Eleve = id_Eleve;
	}

	/**
	 * Returns id_Classe.
	 * @return id_Classe 
	 */
	public Integer getId_Classe() {
		return this.id_Classe;
	}

	/**
	 * Sets a value to attribute id_Classe. 
	 * @param id_Classe 
	 */
	public void setId_Classe(Integer id_Classe) {
		this.id_Classe = id_Classe;
	}

	public static void dbDeleteFromIdClasse(Integer id2) {
		// TODO Auto-generated method stub
		
	}


}
