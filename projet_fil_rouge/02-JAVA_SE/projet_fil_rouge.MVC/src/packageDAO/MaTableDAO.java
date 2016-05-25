package packageDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import packageAppli.MaTable;
import packageException.InputValueInvalidException;
import packageException.InputValueTooLongException;

/**
 * 
 * @author fabrizzio
 *	2016-05-16
 *	added Table classe to show the difference between DAO layer and application layer
 *	removed conection which is done through the constructor of the DAO class
 */
public class MaTableDAO extends DAO<MaTable>
{
	private static final String table = "TestTable";

	//************************************************************************
	//** CONSTRUCTORS
	//************************************************************************

	public MaTableDAO(Connection conn)
	{
		super(conn);
	}
	
	public MaTableDAO()
	{
		super();
	}
	
	//************************************************************************
	//** GETTERS SETTERS
	//************************************************************************
	
	public String getTable()
	{
		return table;
	}
	
	//************************************************************************
	//** METHODES
	//************************************************************************
	
	/**
	 * Description of the method dbSelectById
	 * @param id
	 * @return MaTable
	 */

	public MaTable dbSelectFromId(Integer id){

		MaTable retObj = null;

		String sql = "SELECT * FROM "+ table + " WHERE id="+id;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareCall(sql);
			// ResultSet est une collection
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				retObj = new MaTable ( rs.getInt("id"), 
										rs.getString("name"),
										rs.getDate("date").toLocalDate());
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException | InputValueTooLongException | InputValueInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retObj;
	}
	
	/**
	 * Description of the method dbSelectAll
	 * @return ArrayList <MaTableDAO> 
	 */
	public ArrayList <MaTable> dbSelectAll()
	{
		ArrayList <MaTable> retObj = new ArrayList<MaTable>();

		String sql = "SELECT * FROM "+ table + " ORDER BY id ASC";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareCall(sql);
			// ResultSet est une collection
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				MaTable obj = new MaTable ( rs.getInt("id"), 
											rs.getString("nom"),
											rs.getDate("date").toLocalDate());
				retObj.add(obj);
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException | InputValueTooLongException | InputValueInvalidException e) {
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
	 * @throws InputValueInvalidException 
	 * 
	 */
	public boolean dbInsert(MaTable obj) throws InputValueInvalidException{

		boolean ret = false;

		String sql = "INSERT INTO "+ table + " (name, date) VALUES (?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, obj.getName());
			pstmt.setDate(2, Date.valueOf(obj.getDate()));
			
			int nb = pstmt.executeUpdate();
			if (1 == nb)
			{
				ret = true;
				rs = pstmt.getGeneratedKeys();
				if (rs.first())
				{
					obj.setId(rs.getInt(1));
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
	 * @param new_id 
	 * @return boolean
	 */
	public boolean dbUpdate(MaTable obj) {
		// FIXME Why rowcount if it just 1 ?
		int updateRowCount = 0;

		String sql = "UPDATE "+table+" SET name=?, date=? WHERE id="+obj.getId();

		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getName());
			pstmt.setDate(2, Date.valueOf(obj.getDate()));
			updateRowCount = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (1 == updateRowCount);
	}

	/**
	 * Description of the method dbExistFromId.
	 * @param id
	 * @return
	 * 
	 * @NOTE - Identical method for all classes !
	 */
	public boolean dbExistFromId(Integer id) {
		boolean retBool = false; 
		
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
	public boolean dbDeleteFromId(int id) {
		boolean dbDeleteFromId = false;
				
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