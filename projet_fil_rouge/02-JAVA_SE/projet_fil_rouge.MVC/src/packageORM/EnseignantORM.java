package packageORM;

import java.sql.SQLException;
import java.time.LocalDate;

import packageDAO.AdresseDAO;
import packageDAO.CiviliteDAO;
import packageDAO.EnseignantDAO;
import packageDAO.Role;
import packageDAO.UtilisateurDAO;

public class EnseignantORM {

	/**
	 *  Attributs
	 */
	private int id;
	private AdresseORM adresse;
	private CiviliteORM civilite;
	private UtilisateurORM utilisateur;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AdresseORM getAdresse() {
		return adresse;
	}
	public void setAdresse(AdresseORM adresse) {
		this.adresse = adresse;
	}
	public CiviliteORM getCivilite() {
		return civilite;
	}
	public void setCivilite(CiviliteORM civilite) {
		this.civilite = civilite;
	}
	public UtilisateurORM getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(UtilisateurORM utilisateur) {
		this.utilisateur = utilisateur;
	}
	/**
	 *  Constructeurs
	 *  
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param sexe
	 * @param date_naiss
	 * @param voie
	 * @param ville
	 * @param cp
	 * @param telephone
	 */
	private EnseignantORM (Integer id, CiviliteORM civilite, AdresseORM adresse, UtilisateurORM utilisateur)
	{
		this(civilite, adresse, utilisateur);
		setId(id);
		
	}
	public EnseignantORM (	CiviliteORM civilite, AdresseORM adresse, UtilisateurORM utilisateur)
	{
		super();
		setCivilite(civilite);
		setAdresse(adresse);
		setUtilisateur(utilisateur);
	}
	
	/**
	 *  Methodes
	 * @throws SQLException 
	 */

	/**
	 * METHODE: CREATION ENSEIGNANT
	 * 
	 * @param nom
	 * @param prenom
	 * @param sexe
	 * @param date_naiss
	 * @param voie
	 * @param ville
	 * @param cp
	 * @param telephone
	 * @param login
	 * @param password
	 * @param role
	 * @return
	 * @throws SQLException
	 */
	public static EnseignantORM create(String nom, String prenom, String sexe, LocalDate date_naiss,
			String voie,  String ville, String cp , String telephone, String login, String password, Role role) throws SQLException 
	{
		EnseignantORM objORM = null;
		
		CiviliteORM civilite = CiviliteORM.create(nom, prenom, sexe, date_naiss);
		
		AdresseORM adresse = AdresseORM.create(voie, ville, cp, telephone);
		
		UtilisateurORM utilisateur = UtilisateurORM.create(login, password, role);
		
		// Créer la classe DAO de l'objet courant en récupérant les id des objets en attribut
		EnseignantDAO objDAO = new EnseignantDAO(civilite.getId(),adresse.getIdAdresse(),utilisateur.getId());
		
		// Insérer la classe dans la base de données
		if (objDAO.dbInsert())
		{
			objORM = new EnseignantORM(objDAO.getId(),
					civilite, 
					adresse, 
					utilisateur);
		}
		else {
			throw new  SQLException("Erreur d'enregistrement sur la civilité en base de données");
		}
		return objORM;
	}

	/**
	 * METHODE: LECTURE ENSEIGNANT
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static EnseignantORM read(Integer id) throws SQLException 
	{
		EnseignantORM objORM;
		boolean isExist = EnseignantDAO.dbExistFromId(id);
		
		// Récupérer l'objet DAO correspondant:
		if (isExist)
		{
			EnseignantDAO objDAO = EnseignantDAO.dbSelectFromId(id);
			
			// Récupérer la civilité
			CiviliteORM civilite = CiviliteORM.read(objDAO.getCivilite_id());
			// Récupérer l'adresse
			AdresseORM adresse = AdresseORM.read(objDAO.getAdresse_id());
			// Récupérer l'adresse
			UtilisateurORM utilisateur = UtilisateurORM.read(objDAO.getUtilisateur_id());
			
			// Creer un obj ClasseORM avec les valeurs trouvées en base
			objORM = new EnseignantORM(civilite, adresse, utilisateur);

		}
		else 
		{			
			throw new SQLException ("Enseignant introuvable");
		}
		
		return objORM;
	}
	
	/**
	 * METHODE: MISE A JOUR ENSEIGNANT
	 * @param id
	 * @param civilite
	 * @param adresse
	 * @param utilisateur
	 * @return
	 * @throws SQLException
	 */
	public static EnseignantORM update(Integer id, CiviliteORM civilite, AdresseORM adresse, UtilisateurORM utilisateur) throws SQLException {
		
		EnseignantORM objORM = null;
		boolean isExistDAOEns, isExistDAOadr, isExistDAOCiv, isExistDAOUti = false;
		
		// Tester si les objets existent
		isExistDAOEns = EnseignantDAO.dbExistFromId(id);

		// FIXME est-ce nécessaire ?
		isExistDAOCiv = CiviliteDAO.dbExistFromId(id);
		isExistDAOadr = AdresseDAO.dbExistFromId(id);
		isExistDAOUti = UtilisateurDAO.dbExistFromId(id);
		
		// Récupérer l'objet en base
		if (isExistDAOEns 
				&& isExistDAOadr && isExistDAOCiv && isExistDAOUti)
		{
			EnseignantDAO objDAO = EnseignantDAO.dbSelectFromId(id);
			// Mettre à jour les champs
			objDAO.setAdresse_id(adresse.getIdAdresse());
			objDAO.setCivilite_id(civilite.getId());
			objDAO.setUtilisateur_id(utilisateur.getId());
			// Mettre à jour la base de données
			if (objDAO.dbUpdate())
			{
				objORM = new EnseignantORM(objDAO.getId(),
							civilite,
							adresse,
							utilisateur);
			}
			else
			{
				throw new  SQLException("Erreur modification de l'Enseignant en base de données");
			}

		}
		else
		{
			throw new  SQLException("Erreur id inexistant en base de données");
		}
		return objORM;
	}

	/**
	 * METHODE: EFFACER ENSEIGNANT
	 * @param id
	 * @return boolean
	 */
	public boolean delete(Integer id)
	{
		return EnseignantDAO.dbDeleteFromId(id);
	}
	
}
