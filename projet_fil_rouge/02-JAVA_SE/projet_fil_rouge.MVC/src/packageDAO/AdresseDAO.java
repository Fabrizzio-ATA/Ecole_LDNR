package packageDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import packageJDBC.JDBCConnection;

/*******************************************************************************

 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of AdresseDAO.
 * 
 * @author fabrizzio
 */
public class AdresseDAO implements InterfaceDb {
	
	private static String table = "Adresse";
	
	/**
	 * Description of the property id.
	 */
	private Integer id = Integer.valueOf(0);

	/**
	 * Description of the property voie.
	 */
	private String voie = "";

	/**
	 * Description of the property ville.
	 */
	private String ville = "";

	/**
	 * Description of the property cp.
	 */
	private String cp = "";

	/**
	 * Description of the property telephone.
	 */
	private String telephone = "";

	// Start of user code (user defined attributes for AdresseDAO)

	// End of user code

	/**
	 * 
	 * @param id
	 * @param voie
	 * @param ville
	 * @param cp
	 * @param telephone
	 */
	
	public AdresseDAO(String voie, String ville, String cp, String telephone) {
		setVoie(voie);
		setVille(ville);
		setCp(cp);
		setTelephone(telephone);
	}
	
	private AdresseDAO(Integer id, String voie, String ville, String cp, String telephone) {
		this(voie, ville, cp, telephone);
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
								+"(voie, cp, ville, telephone) "
								+"VALUES(?,?,?,?)";
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, this.getVoie());
			pstmt.setString(2, this.getCp());
			pstmt.setString(3, this.getVille());
			pstmt.setString(4, this.getTelephone());
			
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
	 * @return AdresseDAO
	 */
	public static AdresseDAO dbSelectFromId(int id) {
		AdresseDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table+" WHERE id="+id;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.first()){
				retObj = new AdresseDAO(rs.getInt("id"),
										rs.getString("voie"),
										rs.getString("ville"),
										rs.getString("cp"),
										rs.getString("telephone"));
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
	 * @return ArrayList <AdresseDAO>
	 */
	public static ArrayList <AdresseDAO> dbSelectAll() {
		ArrayList<AdresseDAO> tabAdresse = new ArrayList<AdresseDAO>();
		AdresseDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				do{
					retObj = new AdresseDAO(rs.getInt("id"),
											rs.getString("voie"),
											rs.getString("ville"),
											rs.getString("cp"),
											rs.getString("telephone"));
					tabAdresse.add(retObj);
				}while(rs.next());
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tabAdresse;
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
		
		String sql = "UPDATE "+table+" SET voie=?, cp=?, ville=?, telephone=? WHERE id="+getId();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, this.getVoie());
			pstmt.setString(2, this.getCp());
			pstmt.setString(3, this.getVille());
			pstmt.setString(4, this.getTelephone());
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
	
	public boolean hasSameContent(AdresseDAO obj){
		return(this.ville.equals(obj.getVille())
				&& this.cp.equals(obj.getCp())
				&& this.voie.equals(obj.getVoie())
				&& this.telephone.equals(obj.getTelephone()));
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
	 * Returns voie.
	 * @return voie 
	 */
	public String getVoie() {
		return this.voie;
	}

	/**
	 * Sets a value to attribute voie. 
	 * @param newVoie 
	 */
	public void setVoie(String newVoie) {
		this.voie = newVoie;
	}

	/**
	 * Returns ville.
	 * @return ville 
	 */
	public String getVille() {
		return this.ville;
	}

	/**
	 * Sets a value to attribute ville. 
	 * @param newVille 
	 */
	public void setVille(String newVille) {
		this.ville = newVille;
	}

	/**
	 * Returns cp.
	 * @return cp 
	 */
	public String getCp() {
		return this.cp;
	}

	/**
	 * Sets a value to attribute cp. 
	 * @param newCp 
	 */
	public void setCp(String newCp) {
		this.cp = newCp;
	}

	/**
	 * Returns telephone.
	 * @return telephone 
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * Sets a value to attribute telephone. 
	 * @param newTelephone 
	 */
	public void setTelephone(String newTelephone) {
		this.telephone = newTelephone;
	}


}
