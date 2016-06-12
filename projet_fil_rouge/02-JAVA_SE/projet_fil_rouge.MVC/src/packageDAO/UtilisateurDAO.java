package packageDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import packageJDBC.JDBCConnection;

public class UtilisateurDAO implements InterfaceDb {

	private static final String table = "Utilisateur";
	private int id;
	private String login;
	private String password;
	private Role role;

	/**
	 * Constructeurs 
	 * @param login
	 * @param password
	 * @param role
	 */

	public UtilisateurDAO(String login, String password, Role role) {
		super();
		setLogin(login);
		setPassword(password);
		setRole(role);
	}
	
	private UtilisateurDAO(int id, String login, String password, Role role) {
		this(login, password, role);
		setId(id);
	}

	/**
	 *  METHODES
	 */
	
	/**
	 * Description of the method dbInsert.
	 * @return 
	 * 
	 * FIXME Laisser la base de données générer le mot de passe
	 */
	@Override
	public boolean dbInsert() {
		// Start of user code for method dbInsert
		boolean retBool = false;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "INSERT INTO "+table+" "
								+"(login, pwd, role) "
								+"VALUES(?,?,?)";
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, this.getLogin());
			pstmt.setString(2, this.getPassword());
			pstmt.setString(3, this.getRole().toString());
			
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

	@Override
	/**
	 * Description of the method dbUpdate.
	 * @return boolean
	 */
	public boolean dbUpdate() {
		// Start of user code for method dbUpdate
		int updateRowCount = 0;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "UPDATE "+table+" SET login=?, pwd=?, role=? WHERE id="+getId();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, this.getLogin());
			pstmt.setString(2, this.getPassword());
			pstmt.setString(3, this.getRole().toString());
			
			updateRowCount = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (updateRowCount==1);
		// End of user code
	}
	
	
	/**
	 * Description of the method dbSelectFromId.
	 * @param id 
	 * @return UtilisateurDAO
	 */
	public static UtilisateurDAO dbSelectFromId(int id) {
		UtilisateurDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table+" WHERE id="+id;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.first()){
				retObj = new UtilisateurDAO(rs.getInt("id"),
										rs.getString("login"),
										rs.getString("pwd"),
										Role.valueOf(rs.getString("role")));
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
	 * @return ArrayList <UtilisateurDAO>
	 */
	public static ArrayList<UtilisateurDAO> dbSelectAll() {

		ArrayList<UtilisateurDAO> tabUtilisateur = new ArrayList<UtilisateurDAO>();
		UtilisateurDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				do{
					retObj = new UtilisateurDAO(rs.getInt("id"),
											rs.getString("login"),
											rs.getString("pwd"),
											Role.valueOf(rs.getString("role")));
					tabUtilisateur.add(retObj);
				}while(rs.next());
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tabUtilisateur;
		// Start of user code for method dbSelectFromId
		// End of user code
	}
	

	/**
	 * Description of the method dbDeleteFromId.
	 * @param id 
	 * @return boolean
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
	
	public boolean hasSameContent(UtilisateurDAO obj){
		return(this.getLogin().equals(obj.getLogin())
				&& this.getPassword().equals(obj.getPassword())
				&& this.getRole().equals(obj.getRole()));
	}

	/**
	 * Accesseurs
	 * @return
	 */
	
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
