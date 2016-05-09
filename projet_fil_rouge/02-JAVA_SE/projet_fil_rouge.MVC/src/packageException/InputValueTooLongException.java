package packageException;


public class InputValueTooLongException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -232444291031244137L;

	public InputValueTooLongException(String name, int maxLength, int actualLength) {
		// TODO Auto-generated constructor stub
		super("Variable "+name+" : valeur fourni ("+
					actualLength+") dépasse le max ("+
					maxLength+")");
	}

}
