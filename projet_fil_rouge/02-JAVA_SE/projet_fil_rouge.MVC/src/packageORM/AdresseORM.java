package packageORM;

import packageDAO.AdresseDAO;

public class AdresseORM {

	/**
	 *  Attributs
	 */
	private 	Integer 	idAdresse = Integer.valueOf(0);
	private		String 		voie;
	private 	String 		cp;
	private		String 		ville;
	private		String		telephone;
	
	/**
	 * Constructeurs
	 */
	public AdresseORM() {
		super();
	}
	
	private AdresseORM(String voie,  String ville, String cp , String telephone) {
		setVoie(voie);
		setCp(cp);
		setVille(ville);
		setTelephone(telephone);
	}
	
	private AdresseORM(Integer idAdresse, String voie,  String ville, String cp , String telephone) {
		this(voie, ville, cp, telephone);
		setIdAdresse(idAdresse);
	}
	
	/*
	 * Methode de Gestion vers la DAO
	 */

	//@Override
	public static AdresseORM create(String voie, String ville, String cp, String telephone) {
				
		// Instanciation de l'adresseORM initialisé à null
		AdresseORM 		adresseORM = null; 
		
		// Création de l'objet DAO et enregistrement avec la méthode DAO
		// instancier l'objet adresseDAO pour l'enregistrer
		 AdresseDAO adresseDAO = new AdresseDAO( voie       , ville         , 
									             cp         , telephone );
		 
		
		// Insérer la classe dans la base de données
		if (adresseDAO.dbInsert()) {

			adresseORM = new AdresseORM( adresseDAO.getId()      , adresseDAO.getVoie()  , 
										 adresseDAO.getVille()   , adresseDAO.getCp()    ,
										 adresseDAO.getTelephone());
		}
		
		return adresseORM;
	}
	
	//@Override
	public static boolean delete(Integer idAdresse) {
		boolean retour = false;
		
		
		if (AdresseDAO.dbDeleteFromId(idAdresse)) {
			retour = true;
		}
		
		return retour;
	}
	
	//@Override
	public static boolean update(Integer idAdresseORM , String voie, String ville, String cp, String telephone) {
		
		boolean retour = false;
		
		AdresseORM objORM = new AdresseORM(idAdresseORM , voie, ville, cp, telephone);
		
		// Créer la classe DAO de l'objet courant avec les valeurs
		AdresseDAO adresseDAO = new AdresseDAO(	objORM.getVoie() 	   , objORM.getVille() 	 , 
												objORM.getCp() 		   , objORM.getTelephone() );
		adresseDAO.setId( objORM.getIdAdresse() ); 
		
		
		if (adresseDAO.dbUpdate() ) {
			retour =  true;
		}
		
		return retour;
	}

	
	//@Override
	public static AdresseORM read(Integer idAdresseORM) {
		
		// Récupérer l'objet DAO correspondant:
		AdresseDAO adresseDAO = AdresseDAO.dbSelectFromId(idAdresseORM);
		
		// Récupérer le tableau d'élèves
		
		AdresseORM adresseORM = new AdresseORM( adresseDAO.getId()     , adresseDAO.getVoie()  , 
												adresseDAO.getVille()  , adresseDAO.getCp() , 
												adresseDAO.getTelephone() );
		
		return adresseORM;
	}
	
	
	public String getCp() {
		return cp;
	}

	public void setCp(String codePostal) {
		this.cp = codePostal;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String numTel) {
		this.telephone = numTel;
	}


	public Integer getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(Integer idAdresse) {
		this.idAdresse = idAdresse;
	}
}