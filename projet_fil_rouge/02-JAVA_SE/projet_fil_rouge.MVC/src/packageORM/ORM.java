package packageORM;

import java.util.ArrayList;

import packageException.InputValueTooLongException;

public abstract class ORM {
	
	/**
	 *  COMMON METHODS
	 */
	
	/**
	 * Méthode create
	 * @return true if object is created
	 * @throws InputValueTooLongException 
	 * 
	 */
	public static boolean create() throws InputValueTooLongException {
		return false;
	}
	/**
	 * Méthode read (1 value)
	 * @param id
	 * @return true if insert successfull
	 */
	public static Object read(Integer id)
	{
		return null;
	}
	
	/**
	 * Méthode read (all values)
	 * @return an ArrayList of objects
	 */
	public static ArrayList<Object> read()
	{
		return null;
	}

	/**
	 * 
	 * @param obj
	 * @return true if update successfull
	 */
	public static boolean update() {
		return false;
	}
	
	/**
	 * 
	 * @param id
	 * @return true if deletion successfull
	 */
	public static boolean delete(Integer id)
	{
		return false;
	}
	
}
