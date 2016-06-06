package packageORM;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import packageDAO.CiviliteDAO;

public class CiviliteORM {

	private Integer id;
	private String nom, prenom,sexe;
	private LocalDate date_naiss;

	// CONTRUCTEURS
	private CiviliteORM(String nom, String prenom, String sexe, LocalDate date_naiss){

		this.setNom(nom);
		this.setPrenom(prenom);
		this.setSexe(sexe);
		this.setDate_naiss(date_naiss);
	}

	private CiviliteORM(Integer id,String nom, String prenom, String sexe, LocalDate date_naiss){

		this(nom, prenom, sexe, date_naiss);
		this.setId(id);	
	}


	// METHODE: CREER CIVILITE
	public static CiviliteORM create(String nom, String prenom, String sexe, LocalDate date_naiss) throws SQLException{
		CiviliteORM civ = null;

		CiviliteDAO civDAO = new CiviliteDAO(nom, prenom, sexe,Date.valueOf(date_naiss));

		if(civDAO.dbInsert()){
			civ = new CiviliteORM(civDAO.getId(),
					civDAO.getNom(),
					civDAO.getPrenom(),
					civDAO.getSexe(),
					civDAO.getDate_naiss().toLocalDate());
		}
		else {
			throw new  SQLException("Erreur d'enregistrement sur la civilité en base de données");
		}

		return civ;
	}

	// METHODE: LIRE CIVILITE
	public static CiviliteORM read(Integer id) throws SQLException{
		CiviliteORM civ;
		boolean ifexistcivDAO = CiviliteDAO.dbExistFromId(id);

		if (ifexistcivDAO){

			CiviliteDAO civDAO= CiviliteDAO.dbSelectFromId(id);

			civ = new CiviliteORM(civDAO.getId(),
					civDAO.getNom(),
					civDAO.getPrenom(),
					civDAO.getSexe(),
					civDAO.getDate_naiss().toLocalDate());
		}
		else {
			throw new SQLException ("La civilité est introuvable");
		}

		return civ;
	}

	// METHODE: MISE A JOUR CIVILITE

	public static CiviliteORM update(int id,String nom, String prenom, String sexe, LocalDate date_naiss) throws SQLException{

		CiviliteORM objORM = null;
		boolean isExistDAO = false;
		
		// Tester si l'objet existe
		isExistDAO = CiviliteDAO.dbExistFromId(id);
				
		// Récupérer l'objet en base
		if (isExistDAO)
		{
			CiviliteDAO objDAO = CiviliteDAO.dbSelectFromId(id);
			// Mettre à jour les champs
			objDAO.setNom(nom);
			objDAO.setPrenom(prenom);
			objDAO.setSexe(sexe);
			objDAO.setDate_naiss(date_naiss);
			
			// Mettre à jour la base de données
			if (objDAO.dbUpdate())
			{
				objORM = new CiviliteORM(objDAO.getId(),
						objDAO.getNom(),
						objDAO.getPrenom(),
						objDAO.getSexe(),
						objDAO.getDate_naiss().toLocalDate());
			}
			else
			{
				throw new  SQLException("Erreur modification de l'Civilite en base de données");
			}
		}
		else
		{
			throw new  SQLException("Erreur id inexistant en base de données");
		}
		return objORM;
	}
	
// METHODE: EFFACER CIVILITE
	
	public static boolean delete(int id){
		
		return	CiviliteDAO.dbDeleteFromId(id);
		
	}

	// GETTERS & SETTERS

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public LocalDate getDate_naiss() {
		return date_naiss;
	}
	public void setDate_naiss(LocalDate date_naiss) {
		this.date_naiss = date_naiss;
	}

}