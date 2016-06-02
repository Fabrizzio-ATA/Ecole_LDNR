package packageDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import packageJDBC.JDBCConnection;

public class EnseignantDAO implements InterfaceDb {
	private static String table = "Enseignant";
	
	/**
	 * Description of the property id.
	 */
	private Integer id = Integer.valueOf(0);
	
	private Integer Civilite_id, Adresse_id, Utilisateur_id;
	
	public EnseignantDAO(Integer Civilite_id, Integer Adresse_id, Integer Utilisateur_id) {
		setCivilite_id(Civilite_id);
		setAdresse_id(Adresse_id);
		setUtilisateur_id(Utilisateur_id);
	}
	
	private EnseignantDAO(Integer id, Integer Civilite_id, Integer Adresse_id, Integer Utilisateur_id){
		this(Civilite_id, Adresse_id, Utilisateur_id);
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
								+"(Civilite_id, Adresse_id, Utilisateur_id) "
								+"VALUES(?,?,?)";
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, this.getCivilite_id());
			pstmt.setInt(2, this.getAdresse_id());
			pstmt.setInt(3, this.getUtilisateur_id());
			
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
	 * @return EleveDAO
	 */
	public static EnseignantDAO dbSelectFromId(int id) {
		EnseignantDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table+" WHERE id="+id;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.first()){
				retObj = new EnseignantDAO(rs.getInt("id"),
										rs.getInt("Civilite_id"),
										rs.getInt("Adresse_id"),
										rs.getInt("Utilisateur_id"));
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
	 * @return ArrayList <EnseignantDAO>
	 */
	public static ArrayList <EnseignantDAO> dbSelectAll() {
		ArrayList<EnseignantDAO> tabEnseignant = new ArrayList<EnseignantDAO>();
		EnseignantDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				while(rs.next()){
					retObj = new EnseignantDAO(rs.getInt("id"),
												rs.getInt("Civilite_id"),
												rs.getInt("Adresse_id"),
												rs.getInt("Utilisateur_id"));
					tabEnseignant.add(retObj);
				}
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tabEnseignant;
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
		
		String sql = "UPDATE "+table+" SET Civilite_id=?, Adresse_id=?, Utilisateur_id=? WHERE id="+getId();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, this.getCivilite_id());
			pstmt.setInt(2, this.getAdresse_id());
			pstmt.setInt(3, this.getUtilisateur_id());
			
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
	
	public boolean hasSameContent(EnseignantDAO obj){
		return(this.Civilite_id.equals(obj.getCivilite_id())
				&& this.Adresse_id.equals(obj.getAdresse_id())
				&& this.Utilisateur_id.equals(obj.getUtilisateur_id()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCivilite_id() {
		return Civilite_id;
	}

	public void setCivilite_id(Integer civilite_id) {
		Civilite_id = civilite_id;
	}

	public Integer getAdresse_id() {
		return Adresse_id;
	}

	public void setAdresse_id(Integer adresse_id) {
		Adresse_id = adresse_id;
	}

	public Integer getUtilisateur_id() {
		return Utilisateur_id;
	}

	public void setUtilisateur_id(Integer utilisateur_id) {
		Utilisateur_id = utilisateur_id;
	}
	
	

}
