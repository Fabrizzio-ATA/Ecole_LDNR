package packageORM;

import java.util.ArrayList;

import packageException.InputValueTooLongException;

public interface InterfaceORM <T> {
	
	/**
	 *  COMMON METHODS
	 */
	
	/**
	 * Méthode create
	 * @param obj
	 * @return true if object is created
	 * @throws InputValueTooLongException 
	 * 
	 */
	public abstract boolean create(T obj) throws InputValueTooLongException;
	
	/**
	 * Méthode read (1 value)
	 * @param id
	 * @return the objet referenced by id 
	 * TODO return if error (default null)
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
	public abstract boolean update(T obj);
	
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
