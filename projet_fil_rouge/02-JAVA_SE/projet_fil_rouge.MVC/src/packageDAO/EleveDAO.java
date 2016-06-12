package packageDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import packageJDBC.JDBCConnection;

public class EleveDAO implements InterfaceDb {
	
	private static String table = "Eleve";
	
	/**
	 * Description of the property id.
	 */
	private Integer id = Integer.valueOf(0);
	
	private int Civilite_id;
	
	private StatutEleve statutEleve;

	public EleveDAO(int Civilite_id, StatutEleve statut) {
		setCivilite_id(Civilite_id);
		setStatutEleve(statut);
	}
	
	private EleveDAO(Integer id, int Civilite_id, StatutEleve statutEleve){
		this(Civilite_id, statutEleve);
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
								+"(Civilite_id, StatutEleve) "
								+"VALUES(?,?)";
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, this.getCivilite_id());
			
			// FIXME Fab 7/6/2016 : Modification de l'enregistrement du StatutEleve
			pstmt.setString(2, this.getStatutEleve().toString());
			
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
	public static EleveDAO dbSelectFromId(int id) {
		EleveDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table+" WHERE id="+id;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.first()){
				retObj = new EleveDAO(rs.getInt("id"),
										rs.getInt("Civilite_id"),
										StatutEleve.valueOf(rs.getString("StatutEleve")));
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
	 * @return HashSet <EleveDAO>
	 */
	public static ArrayList <EleveDAO> dbSelectAll() {
		ArrayList<EleveDAO> tabEleve = new ArrayList<EleveDAO>();
		EleveDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				do{
					retObj = new EleveDAO(rs.getInt("id"),
											rs.getInt("Civilite_id"),
											StatutEleve.valueOf(rs.getString("StatutEleve")));
					tabEleve.add(retObj);
				}while(rs.next());
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tabEleve;
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
		
		String sql = "UPDATE "+table+" SET Civilite_id=?, StatutEleve=? WHERE id="+getId();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, this.getCivilite_id());
			// FIXME Fab 7/6/2016 : Modification de l'enregistrement du StatutEleve
			pstmt.setString(2, this.getStatutEleve().toString());
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
	
	public boolean hasSameContent(EleveDAO obj){
		return((this.Civilite_id == obj.getCivilite_id())
				&& this.statutEleve.equals(obj.getStatutEleve()));
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

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCivilite_id() {
		return Civilite_id;
	}

	public void setCivilite_id(int Civilite_id) {
		this.Civilite_id = Civilite_id;
	}

	public StatutEleve getStatutEleve() {
		return statutEleve;
	}

	public void setStatutEleve(StatutEleve statut) {
			this.statutEleve = statut;
	}
}
