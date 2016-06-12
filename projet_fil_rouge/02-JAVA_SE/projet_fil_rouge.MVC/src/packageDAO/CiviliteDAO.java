package packageDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import packageJDBC.JDBCConnection;

public class CiviliteDAO implements InterfaceDb {
	private static String table = "Civilite";
	
	/**
	 * Description of the property id.
	 */
	private Integer id = Integer.valueOf(0);
	
	private String nom, prenom, sexe;
	
	private LocalDate date_naiss;
	
	public CiviliteDAO(String nom, String prenom, String sexe, Date date_naiss) {
		setNom(nom);
		setPrenom(prenom);
		setSexe(sexe);
		setDate_naiss(date_naiss.toLocalDate());
	}
	
	private CiviliteDAO(Integer id, String nom, String prenom, String sexe, Date date_naiss){
		this(nom, prenom, sexe, date_naiss);
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
								+"(nom, prenom, sexe, date_naiss) "
								+"VALUES(?,?,?,?)";
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, this.getNom());
			pstmt.setString(2, this.getPrenom());
			pstmt.setString(3, this.getSexe());
			pstmt.setDate(4, this.getDate_naiss());
			
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
	public static CiviliteDAO dbSelectFromId(int id) {
		CiviliteDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table+" WHERE id="+id;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.first()){
				retObj = new CiviliteDAO(rs.getInt("id"),
										rs.getString("nom"),
										rs.getString("prenom"),
										rs.getString("sexe"),
										rs.getDate("date_naiss"));
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
	 * @return ArrayList <CiviliteDAO>
	 */
	public static ArrayList <CiviliteDAO> dbSelectAll() {
		ArrayList<CiviliteDAO> tabCivilite = new ArrayList<CiviliteDAO>();
		CiviliteDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				do{
					retObj = new CiviliteDAO(rs.getInt("id"),
											rs.getString("nom"),
											rs.getString("prenom"),
											rs.getString("sexe"),
											rs.getDate("date_naiss"));
					tabCivilite.add(retObj);
				}while(rs.next());
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tabCivilite;
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
		
		String sql = "UPDATE "+table+" SET nom=?, prenom=?, sexe=?, date_naiss=? WHERE id="+getId();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, this.getNom());
			pstmt.setString(2, this.getPrenom());
			pstmt.setString(3, this.getSexe());
			pstmt.setDate(4, this.getDate_naiss());
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
	
	public boolean hasSameContent(CiviliteDAO obj){
		return(this.nom.equals(obj.getNom())
				&& this.prenom.equals(obj.getPrenom())
				&& this.sexe.equals(obj.getSexe())
				&& this.date_naiss.equals(obj.getDate_naiss().toLocalDate()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Date getDate_naiss() {
		return Date.valueOf(this.date_naiss);
	}

	public void setDate_naiss(LocalDate date_naiss) {
		this.date_naiss = date_naiss;
	}

}
