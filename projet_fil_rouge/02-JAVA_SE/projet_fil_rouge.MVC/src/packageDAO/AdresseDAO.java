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
 * Description of AdresseDAO.
 * 
 * @author fabrizzio
 */
public class AdresseDAO implements InterfaceDb {
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
	public AdresseDAO(Integer id, String voie, String ville, String cp, String telephone) {
		super();
		this.id = id;
		this.voie = voie;
		this.ville = ville;
		this.cp = cp;
		this.telephone = telephone;
	}

	public AdresseDAO() {
		// Start of user code constructor for AdresseDAO)
		super();
		// End of user code
	}


	/**
	 * Description of the method dbInsert.
	 * @return 
	 */
	public boolean dbInsert() {
		// Start of user code for method dbInsert
		boolean dbInsert = false;
		return dbInsert;
		// End of user code
	}

	/**
	 * Description of the method dbSelectFromId.
	 * @param id 
	 * @return AdresseDAO
	 */
	public static AdresseDAO dbSelectFromId(Integer id) {
		// Start of user code for method dbSelectFromId
		AdresseDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		String sql = "SELECT * FROM Adresse WHERE id="+id;
		Statement stmt = null;
		try {
//			stmt = conn.prepareCall(sql);
			stmt = conn.prepareStatement(sql);
			// ResultSet est une collection
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				AdresseDAO obj = new AdresseDAO (rs.getInt("id"), 
												rs.getString("voie"), 
												rs.getString("ville"), 
												rs.getString("cp"), 
												rs.getString("telephone"));
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return retObj;
		// End of user code
	}

	/**
	 * Description of the method dbDeleteFromId.
	 * @param id 
	 * @return 
	 */
	public static boolean dbDeleteFromId(Integer id) {
		// Start of user code for method dbDeleteFromId
		boolean dbDeleteFromId = false;
		return dbDeleteFromId;
		// End of user code
	}

	/**
	 * Description of the method dbSelectAll.
	 * @return HashSet <AdresseDAO>
	 */
	public static HashSet <AdresseDAO> dbSelectAll() {
		// Start of user code for method dbSelectAll		
		HashSet <AdresseDAO> retObj = new HashSet<AdresseDAO>();
		Connection conn = JDBCConnection.getInstance();
		String sql = "SELECT * FROM Adresse ORDER BY id ASC";
		Statement stmt = null;
		try {
//			stmt = conn.prepareCall(sql);
			stmt = conn.prepareStatement(sql);

			// ResultSet est une collection
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				AdresseDAO obj = new AdresseDAO  (rs.getInt("id"), 
						rs.getString("voie"), 
						rs.getString("ville"), 
						rs.getString("cp"), 
						rs.getString("telephone"));
				retObj.add(obj);
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return retObj;
		
		// End of user code
	}

	/**
	 * Description of the method dbUpdate.
	 * @return 
	 */
	public boolean dbUpdate() {
		// Start of user code for method dbUpdate
		boolean ret = false;
		Connection conn = JDBCConnection.getInstance();
		String sql = "INSERT INTO maTable "
				+ "(voie, ville, cp, telephone) VALUES (?,?,?,?)";
		PreparedStatement pstmt = null;
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, this.voie);
			pstmt.setString(1, this.ville);
			pstmt.setString(1, this.cp);
			pstmt.setString(1, this.telephone);
			int nb = pstmt.executeUpdate();
			ret = true;
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.first();
			this.setId(rs.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
		// End of user code
	}

	/**
	 * Description of the method dbExistFromId.
	 * @param id 
	 * @return 
	 */
	public boolean dbExistFromId(Integer id) {
		// Start of user code for method dbExistFromId
		boolean dbExistFromId = false;
		if (null != AdresseDAO.dbSelectFromId(id))
			dbExistFromId = true;
		return dbExistFromId;
		// End of user code
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
