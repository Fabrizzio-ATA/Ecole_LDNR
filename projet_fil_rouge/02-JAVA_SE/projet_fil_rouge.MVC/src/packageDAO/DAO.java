package packageDAO;

import java.util.ArrayList;
import java.util.Vector;

import packageAppli.MaTable;
import packageException.InputValueInvalidException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import packageJDBC.JDBCConnection;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of DAO.
 * 
 * @author fabrizzio
 */
public abstract class DAO <T> {
	// Start of user code (user defined attributes for DAO)
	Connection conn;

	// End of user code

	/**
	 * The constructor.
	 * Au choix, connection prédéfinie dans JDBC ou connection créée par l'utilisateur
	 */
	public DAO() {
		// Start of user code constructor for DAO)
		//super();
		this(JDBCConnection.getInstance());
		// End of user code
	}
	
	public DAO(Connection conn) {
		// Start of user code constructor for DAO)
		//super();
		this.conn = conn;
		// End of user code
	}
	
	//************************************************************************
	//** GETTERS SETTERS
	//************************************************************************

	public abstract String getTable();

	/**
	 *  COMMON METHODS
	 */
	
	/**
	 * Description of the method dbExistFromId.
	 * @param id 
	 * @return 
	 */
	public boolean dbExistFromId(Integer id) {
		// Start of user code for method dbExistFromId
		boolean retBool = false; 
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "SELECT * FROM "+this.getTable()+" WHERE id="+id;
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
		// End of user code
	}
	
	/**
	 * Description of the method dbDeleteFromId.
	 * @param id 
	 * @return 
	 */
	public boolean dbDeleteFromId(Integer id) {
		// Start of user code for method dbDeleteFromId
		// Start of user code for method dbDeleteFromId
		boolean dbDeleteFromId = false;
		
		Connection conn = JDBCConnection.getInstance();
		
		String sql = "DELETE FROM "+this.getTable()+" WHERE id="+id;
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
		// End of user code
	}

	/**
	 *   SPECIFIC ABSTRACT METHODS
	 */
	
	/**
	 * Description of the method dbInsert.
	 * @return 
	 * @throws InputValueInvalidException 
	 */
	public abstract boolean dbInsert(T obj) throws InputValueInvalidException;

	/**
	 * Description of the method dbSelectFromId.
	 * @param id 
	 * @return 
	 */
	public abstract T dbSelectFromId(Integer id);

	/**
	 * Description of the method dbSelectAll.
	 * @return 
	 */
	public abstract ArrayList<MaTable> dbSelectAll();

	/**
	 * Description of the method dbUpdate.
	 * @return 
	 */
	public abstract boolean dbUpdate(T obj);

	/**
	 * Description of the method hasSameContent.
	 * @return 
	 */
	//public abstract boolean hasSameContent(T obj);

	// Start of user code (user defined methods for DAO)

	// End of user code

}
