package packageDAO;

import packageException.InputValueInvalidException;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Niveau.
 * 
 * @author fabrizzio
 */
public enum Niveau {
					/**
					 * Description of PS.
					 */
					PS("PS","Petite Section"),

					/**
					 * Description of MS.
					 */
					MS("MS","Moyenne Section"),

					/**
					 * Description of GS.
					 */
					GS("GS","Grande Section"),

					/**
					 * Description of CP.
					 */
					CP("CP", "Cours Préparatoire"),

					/**
					 * Description of CE1.
					 */
					CE1("CE1", "Cours Elémentaire 1re année"),

					/**
					 * Description of CE2.
					 */
					CE2("CE2", "Cours Elémentaire 2nd année"),

					/**
					 * Description of CM1.
					 */
					CM1("CM1", "Cours Moyen 1re année"),

					/**
					 * Description of CM2.
					 */
					CM2("CM2", "Cours Moyen 2nd année");

	private String labelLong, labelCourt;
	
	private Niveau(String labelCourt, String labelLong){
		setLabelLong(labelLong);
		setLabelCourt(labelCourt);
	}
	
	public String getLabelLong() {
		return labelLong;
	}

	public void setLabelLong(String labelLong) {
		this.labelLong = labelLong;
	}
	
	public String getLabelCourt() {
		return labelCourt;
	}

	public void setLabelCourt(String labelCourt) {
		this.labelCourt = labelCourt;
	}
	
	public static Niveau fromString(String str) throws InputValueInvalidException{
		Niveau obj = null;
		
		if(str.equals("PS")) obj = Niveau.PS;
		else if(str.equals("MS")) obj = Niveau.MS;
		else if(str.equals("GS")) obj = Niveau.GS;
		else if(str.equals("CP")) obj = Niveau.CP;
		else if(str.equals("CE1")) obj = Niveau.CE1;
		else if(str.equals("CE2")) obj = Niveau.CE2;
		else if(str.equals("CM1")) obj = Niveau.CM1;
		else if(str.equals("CM2")) obj = Niveau.CM2;
		else throw new InputValueInvalidException("Niveau inconnue : "+str);
		
		return obj;
	}
}
