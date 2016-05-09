package packageDAO;


/*******************************************************************************

 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of InterfaceDb.
 * 
 * @author fabrizzio
 */
public interface InterfaceDb {
	// Start of user code (user defined attributes for InterfaceDb)

	// End of user code

	/**
	 * Description of the method dbInsert.
	 * @return 
	 */
	public abstract boolean dbInsert();

	/**
	 * Description of the method dbSelectFromId.
	 * @param id 
	 * @return 
	 */
	public static Object dbSelectFromId(Integer id) {
		return null;
	}

	/**
	 * Description of the method dbDeleteFromId.
	 * @param id 
	 * @return 
	 */
	public static boolean dbDeleteFromId(Integer id) {
		return false;
	}

	/**
	 * Description of the method dbSelectAll.
	 * @return 
	 */
	public static Object dbSelectAll(){
		return null;
	}

	/**
	 * Description of the method dbUpdate.
	 * @return 
	 */
	public abstract boolean dbUpdate();

	/**
	 * Description of the method dbExistFromId.
	 * @param id 
	 * @return 
	 */
	public static boolean dbExistFromId(Integer id) {
		boolean retBool = false; 
		
		return retBool;
	}

	// Start of user code (user defined methods for InterfaceDb)

	// End of user code

}
