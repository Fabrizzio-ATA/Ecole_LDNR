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
	
	public AdresseORM(String voie,  String ville, String cp , String telephone) {
		
		setVoie(voie);
		setCP(cp);
		setVille(ville);
		setTelephone(telephone);
	}
	
	/*
	 * Methode de Gestion vers la DAO
	 */

	//@Override
	public AdresseORM create(String voie, String ville, String cp, String telephone) {
				
		// Instanciation de l'adresseORM avec les zones d'adresse
		AdresseORM 		adresseORM = new AdresseORM(voie, ville , cp , telephone );
		
		// Création de l'objet DAO et enregistrement avec la méthode DAO
		AdresseDAO adresseDAO;
			
		
		// instancier l'objet adresseDAO pour l'enregistrer
		adresseDAO = new AdresseDAO(adresseORM.getVoie()  , adresseORM.getCP() , 
									adresseORM.getVille() , adresseORM.getTelephone() );
		
		// Insérer la classe dans la base de données
		adresseDAO.dbInsert(); 
		
		return adresseORM;
	}
	
	//@Override
	public boolean delete(Integer idAdresse) {
		boolean retour = false;
		
		AdresseDAO.dbDeleteFromId(idAdresse);
		
		return retour;
	}
	
	//@Override
	public AdresseORM update(String voie, String ville, String cp, String telephone) {
		
		AdresseORM adresseORM = new AdresseORM(voie, ville, cp, telephone);
	
		// Créer la classe DAO de l'objet courant en récupérant les id des objets précédents
		AdresseDAO adresseDAO = new AdresseDAO(	adresseORM.getVoie() , adresseORM.getVille () ,
												adresseORM.getCP()   , adresseORM.getTelephone() );
		
		adresseDAO.dbUpdate();
		
		return adresseORM;
	}

	
	//@Override
	public AdresseORM read(Integer idAdresseORM) {
		
		// Récupérer l'objet DAO correspondant:
		AdresseDAO adresseDAO = AdresseDAO.dbSelectFromId(idAdresseORM);
		// Récupérer le tableau d'élèves
		
		AdresseORM adresseORM = new AdresseORM( adresseDAO.getVoie()  , adresseDAO.getVille()  ,
												adresseDAO.getVille() , adresseDAO.getTelephone() );
		
		return adresseORM;
	}
	
	
	public String getCP() {
		return cp;
	}

	public void setCP(String codePostal) {
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