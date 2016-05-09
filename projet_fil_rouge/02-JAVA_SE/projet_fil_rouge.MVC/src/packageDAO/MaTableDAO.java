package packageDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;

import packageJDBC.JDBCConnection;

public class MaTableDAO 
{
	private Integer id;
	private String nom = "nom_test";
	private LocalDate date;
	private static final String table = "Adresse";

	//************************************************************************
	//** CONSTRUCTORS
	//************************************************************************

	public MaTableDAO(String nom, LocalDate date) {
		this.setNom(nom);
		this.setDate(date);
	}
	
	public MaTableDAO(Integer id, String nom, LocalDate date) {
		this(nom,date);
		this.setId(id);
	}
	
	//************************************************************************
	//** GETTERS SETTERS
	//************************************************************************

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	//************************************************************************
	//** METHODES
	//************************************************************************
	
	/**
	 * Description of the method dbSelectById
	 * @param id
	 * @return MaTableDAO
	 */

	public MaTableDAO dbSelectById(int id){

		MaTableDAO retObj = null;
		Connection conn = JDBCConnection.getInstance("","");
		String sql = "SELECT * FROM "+ table + "WHERE id="+id;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareCall(sql);
			// ResultSet est une collection
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				MaTableDAO obj = new MaTableDAO (rs.getInt("id"), 
												rs.getString("nom"),
												rs.getDate("date").toLocalDate());
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retObj;
	}
	
	/**
	 * Description of the method dbSelectAll
	 * @return HashSet <MaTableDAO> 
	 */
	public HashSet <MaTableDAO> dbSelectAll()
	{
		HashSet <MaTableDAO> retObj = new HashSet<MaTableDAO>();
		Connection conn = JDBCConnection.getInstance();
		String sql = "SELECT * FROM "+ table + " ORDER BY id ASC";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareCall(sql);
			// ResultSet est une collection
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				MaTableDAO obj = new MaTableDAO (rs.getInt("id"), 
												rs.getString("nom"),
												rs.getDate("date").toLocalDate());
				retObj.add(obj);
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retObj;
	}
	/**
	 * Description of the method dbInsert
	 * @return boolean
	 * 
	 * - pour éviter des injections dans les requêtes, on spécifie le retour attendu dans ?
	 * 
	 */
	public boolean dbInsert(){

		boolean ret = false;
//		Connection conn = JDBCConnection.getInstance();
//		String sql = "INSERT INTO maTable"
//					+ "(nom, date) VALUES ("
//				+ this.nom + "," + this.date + ")";
//		Statement stmt = null;
//		try {
//			stmt = conn.prepareStatement(sql);
//			int nb = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
//			ret = true;
//			ResultSet rs = stmt.getGeneratedKeys();
//			rs.first();
//			this.dbSelectById(rs.getId(1));
//			stmt.close();
//		} 
		Connection conn = JDBCConnection.getInstance();
		String sql = "INSERT INTO maTable"
					+ "(nom, date) VALUES (?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(0, this.getNom());
			pstmt.setDate(1, Date.valueOf(this.getDate()));
			
			int nb = pstmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if (1 == nb)
			{
				ret = true;
				rs = pstmt.getGeneratedKeys();
				if (rs.first())
				{
					this.setId(rs.getInt(1));
				}
			}
			pstmt.close();
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Description of the method dbUpdateFromId.
	 * @param id 
	 * @return boolean
	 */
	public boolean dbUpdate() {
		// FIXME Why rowcount if it just 1 ?
		int updateRowCount = 0;

		Connection conn = JDBCConnection.getInstance();
		String sql = "UPDATE "+table+" SET nom=?, date=? WHERE id="+getId();

		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, this.getNom());
			stmt.setDate(2, Date.valueOf(this.getDate()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (1 == updateRowCount);
	}
	/**
	 * Description of the method hasSameContent
	 * @param obj
	 * @return boolean
	 */
	public boolean hasSameContent(MaTableDAO obj){
		return(this.nom.equals(obj.getNom())
				&& this.date.equals(obj.getDate()));
	}

	/**
	 * Description of the method dbExistFromId.
	 * @param id
	 * @return
	 * 
	 * @NOTE - Identical method for all classes !
	 */
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

	/**
	 * Description of the method dbDeleteFromId.
	 * @param id 
	 * @return boolean
	 * 
	 * @NOTE - Identical method for all classes !
	 */
	public static boolean dbDeleteFromId(int id) {
		boolean dbDeleteFromId = false;
		
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "DELETE FROM "+table+" WHERE id="+id;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			dbDeleteFromId = (1 == stmt.executeUpdate(sql));
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbDeleteFromId;
	}
	
	
}