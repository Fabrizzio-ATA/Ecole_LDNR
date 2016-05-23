package packageDAO;

import packageException.InputValueInvalidException;

/*******************************************************************************

 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of StatutEleve.
 * 
 * @author fabrizzio
 */
public enum StatutEleve {
							/**
							 * Description of NORMAL.
							 */
							NORMAL("normal"),

							/**
							 * Description of VA_REDOUBLER.
							 */
							VA_REDOUBLER("va_redoubler"),

							/**
							 * Description of VA_PARTIR.
							 */
							VA_PARTIR("va_partir"),

							/**
							 * Description of PARTI.
							 */
							PARTI("parti");
							
							private String statut;
	
							private StatutEleve(String a){
								setStatut(a);
							}
							
							public String getStatut() {
								return statut;
							}

							public void setStatut(String statut) {
								this.statut = statut;
							}
							
							public static StatutEleve fromString(String str) throws InputValueInvalidException{
								StatutEleve obj = null;
								
								if(str.equals("normal")) obj = StatutEleve.NORMAL;
								else if(str.equals("va_redoubler")) obj = StatutEleve.VA_REDOUBLER;
								else if(str.equals("va_partir")) obj = StatutEleve.VA_PARTIR;
								else if(str.equals("parti")) obj = StatutEleve.PARTI;
								else throw new InputValueInvalidException("Statut de l'eleve inconnue : "+str);
								
								return obj;
							}
							
}
