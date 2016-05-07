package packageDAO;

import java.sql.Connection;
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
	

	boolean dbUpdate = true;
	
	boolean dbInsert = true;
	
	static boolean dbDeleteFromId;
	
	//************************************************************************
	//** GETTERS SETTERS
	//************************************************************************
	
	public MaTableDAO(int int1, String string, LocalDate localDate) {
		// TODO Auto-generated constructor stub
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
	 * 
	 * @param id
	 * @return
	 */

	public MaTableDAO dbSelectById(int id){

		MaTableDAO retObj = null;
		Connection conn = JDBCConnection.getInstance("","");
		String sql = "SELECT * FROM maTable WHERE id="+id;
		Statement stmt = null;
		try {
			stmt = conn.prepareCall(sql);
			// ResultSet est une collection
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				MaTableDAO obj = new MaTableDAO (rs.getInt("Id"), 
												rs.getString("nom"),
												rs.getDate("date").toLocalDate());
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return retObj;
	}
	
	public HashSet <MaTableDAO> dbSelectAll()
	{
		HashSet <MaTableDAO> retObj = new HashSet<MaTableDAO>();
		Connection conn = JDBCConnection.getInstance();
		String sql = "SELECT * FROM maTable ORDER BY id ASC";
		Statement stmt = null;
		try {
			stmt = conn.prepareCall(sql);
			// ResultSet est une collection
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				MaTableDAO obj = new MaTableDAO (rs.getInt("Id"), 
												rs.getString("nom"),
												rs.getDate("date").toLocalDate());
				retObj.add(obj);
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return retObj;
	}
	/**
	 * 
	 * @return
	 * 
	 * @ TODO pour éviter des injections dans les requêtes, on spécifie le retour attendu dans ?
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
		try {
			pstmt = conn.prepareStatement(sql);
			int nb = pstmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ret = true;
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.first();
			this.dbSelectById(rs.getInt(1));
			pstmt.close();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
}