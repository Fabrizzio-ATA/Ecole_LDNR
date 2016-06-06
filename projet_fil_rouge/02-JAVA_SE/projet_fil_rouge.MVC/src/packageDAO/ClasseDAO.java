package packageDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import packageException.InputValueInvalidException;
import packageException.InputValueTooLongException;
import packageJDBC.JDBCConnection;

public class ClasseDAO implements InterfaceDb {
	private static String table = "Classe";
	private static final int MAX_LENGTH_NOM = 5;
	private static final int MAX_LENGTH_PERIODE = 4;
	
	/**
	 * Description of the property id.
	 */
	private Integer id = Integer.valueOf(0);
	
	private Integer Enseignant_id;
	
	private String nom, periode;
	
	private Niveau niveau;
	
	public ClasseDAO(String nom, Niveau niveau, String periode, Integer enseignant_id) throws InputValueTooLongException{
		setNiveau(niveau);
		setNom(nom);
		setPeriode(periode);
		setEnseignant_id(enseignant_id);
	}
	
	private ClasseDAO(Integer id, String nom, Niveau niveau, String periode, Integer Enseignant_id) throws InputValueTooLongException{
		this(nom, niveau, periode, Enseignant_id);
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
								+"(nom, niveau, periode, Enseignant_id) "
								+"VALUES(?,?,?,?)";
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, this.getNom());
			pstmt.setString(2, this.getNiveau().getLabelCourt());
			pstmt.setString(3, this.getPeriode());
			pstmt.setInt(4, this.getEnseignant_id());
			
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
	public static ClasseDAO dbSelectFromId(int id) {
		ClasseDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table+" WHERE id="+id;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.first()){
				retObj = new ClasseDAO(rs.getInt("id"),
										rs.getString("nom"),
										Niveau.valueOf(rs.getString("niveau")),
										rs.getString("periode"),
										rs.getInt("Enseignant_id"));
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
	public static ArrayList <ClasseDAO> dbSelectAll() {
		ArrayList<ClasseDAO> tabClasse = new ArrayList<ClasseDAO>();
		ClasseDAO retObj = null;
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+table;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				while(rs.next()){
					retObj = new ClasseDAO(rs.getInt("id"),
											rs.getString("nom"),
											Niveau.valueOf(rs.getString("niveau")),
											rs.getString("periode"),
											rs.getInt("Enseignant_id"));
					tabClasse.add(retObj);
				}
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tabClasse;
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
		
		String sql = "UPDATE "+table+" SET nom=?, niveau=?, periode=?, Enseignant_id=? WHERE id="+getId();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, this.getNom());
			pstmt.setString(2, this.getNiveau().getLabelCourt());
			pstmt.setString(3, this.getPeriode());
			pstmt.setInt(4, this.getEnseignant_id());
			
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
	
	public boolean hasSameContent(ClasseDAO obj){
		return(this.nom.equals(obj.getNom())
				&& this.periode.equals(obj.getPeriode())
				&& this.niveau.equals(obj.getNiveau())
				&& this.Enseignant_id.equals(obj.getEnseignant_id()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Niveau getNiveau() {
		return this.niveau;
	}

	public void setNiveau(Niveau niveau) {
			this.niveau = niveau;
	}

	public Integer getEnseignant_id() {
		return Enseignant_id;
	}

	public void setEnseignant_id(Integer enseignant_id) {
		Enseignant_id = enseignant_id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) throws InputValueTooLongException{
		if(nom.length()>ClasseDAO.MAX_LENGTH_NOM){
			this.nom = nom.substring(0,ClasseDAO.MAX_LENGTH_NOM);
			throw new InputValueTooLongException("nom de la Classe", ClasseDAO.MAX_LENGTH_NOM, nom.length());
		}
		else this.nom = nom;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) throws InputValueTooLongException {
		if(periode.length()>ClasseDAO.MAX_LENGTH_PERIODE){
			throw new InputValueTooLongException("La periode contient trop de caractere", ClasseDAO.MAX_LENGTH_PERIODE, periode.length());
		}
		else this.periode = periode;
		
	}
	

}
