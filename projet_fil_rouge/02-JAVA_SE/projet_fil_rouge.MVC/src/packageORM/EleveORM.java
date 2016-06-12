package packageORM;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.AdresseBEAN;
import beans.CiviliteBEAN;
import packageDAO.CiviliteDAO;
import packageDAO.EleveDAO;
import packageDAO.StatutEleve;

public class EleveORM {

	/**
	 *  Attributs
	 */
	private int id;
	private StatutEleve statut;
	private ArrayList <AdresseORM> tabAdresses = new ArrayList <AdresseORM>();
	private CiviliteORM civilite;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public StatutEleve getStatut() {
		return statut;
	}
	public void setStatut(StatutEleve statut) {
		this.statut = statut;
	}
	public ArrayList<AdresseORM> getAdresse() {
		return tabAdresses;
	}
	public void setAdresse(ArrayList<AdresseORM> adresse) {
		this.tabAdresses = adresse;
	}
	public CiviliteORM getCivilite() {
		return civilite;
	}
	public void setCivilite(CiviliteORM civilite) {
		this.civilite = civilite;
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
	private EleveORM (Integer id, StatutEleve statut, CiviliteORM civilite)
	{
		this(statut, civilite);
		setId(id);
		
	}
	public EleveORM ( StatutEleve statut, CiviliteORM civilite)
	{
		super();
		setStatut(statut);
		setCivilite(civilite);
		setAdresse(null);
	}
	
	/**
	 *  Methodes
	 * @throws SQLException 
	 */

	/**
	 * METHODE: CREATION Eleve
	 * 
	 * @param nom
	 * @param prenom
	 * @param sexe
	 * @param date_naiss
	 * @param voie
	 * @param ville
	 * @param cp
	 * @param telephone
	 * @return EleveORM
	 * @throws SQLException
	 */
	public static EleveORM create(StatutEleve statut, CiviliteBEAN civilite,
			ArrayList <AdresseBEAN> tabAdresses) throws SQLException 
	{
		EleveORM eleveORM = null;
		
		// Créer la civilité
		CiviliteORM civiliteORM = CiviliteORM.create(
				civilite.getNom(), 
				civilite.getPrenom(), 
				civilite.getSexe(), 
				civilite.getDateNaissance());		
				
		// Créer l'objet DAO de l'objet courant en récupérant les id des objets en attribut
		EleveDAO objDAO = new EleveDAO(civiliteORM.getId(),statut);
		
		// Insérer l'objet dans la base de données
		if (objDAO.dbInsert())
		{
			// Initialiser l'objet ORM à retourner
			eleveORM = new EleveORM(objDAO.getId(),
									statut,
									civiliteORM);
		}
		else {
			throw new  SQLException("Erreur d'enregistrement sur la civilité en base de données");
		}
		
		// Créer les adresses et les référencer dans la table de liaison
		ArrayList <AdresseORM> tabAdresseORM = new ArrayList<AdresseORM>();
		AdresseORM adresseORM = null;

		/* FIXME Ajouter d'abord les méthodes de lecture et de supression par id d'Eleve dans Eleve_has_classe
		for (AdresseBEAN adresse : tabAdresses)
		{
			if (null != ( adresseORM = AdresseORM.create(
					adresse.getVoie(),
					adresse.getVille(),
					adresse.getCodePostal(),
					adresse.getTelephone())
						)
				)
			{
				tabAdresseORM.add(adresseORM);
				
				// Créer l'objet DAO
				Eleve_has_AdresseDAO eleveHasAdresse = new Eleve_has_AdresseDAO(
										eleveORM.getId(),
										adresseORM.getIdAdresse());
				
				// Insérer l'objet dans la base de données
				if (!eleveHasAdresse.dbInsert())
				{
					throw new  SQLException("Erreur ajout table de liaison");
				}
			}
		}
		*/
		
		return eleveORM;
	}

	/**
	 * METHODE: LECTURE Eleve
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static EleveORM read(Integer id) throws SQLException 
	{
		EleveORM objORM;
		boolean isExist = EleveDAO.dbExistFromId(id);
		
		// Récupérer l'objet DAO correspondant:
		if (isExist)
		{
			EleveDAO objDAO = EleveDAO.dbSelectFromId(id);
			
			// Récupérer la civilité
			CiviliteORM civilite = CiviliteORM.read(objDAO.getCivilite_id());
			
			// Creer un obj EleveORM avec les valeurs trouvées en base
			objORM = new EleveORM(objDAO.getStatutEleve(),civilite);
			
			// Récupérer les adresses
			/* TODO boucle for sur les Eleve_has_Adresse de objORM.getId();
			 * 		A voir, ajouter une méthode d'acces dans Eleve_Has_Adresse
			 * FIXME AdresseORM adresse = AdresseORM.read(objDAO.getAdresse_id());
			 */
		}
		else 
		{			
			throw new SQLException ("Eleve introuvable");
		}
		
		return objORM;
	}
	
	/**
	 * METHODE: LECTURE DE TOUS LES ELEVES
	 * @return ArrayList<EleveORM >
	 * @throws SQLException
	 */
	public static ArrayList<EleveORM > read() throws SQLException 
	{
		ArrayList<EleveORM > tabORM = new ArrayList<EleveORM >();
		ArrayList<EleveDAO > tabDAO = new ArrayList<EleveDAO>();
		
		// LIT les valeurs en base de donnée
		tabDAO = EleveDAO.dbSelectAll();
		if (!tabDAO.isEmpty())
		{
			// DECLARE et INITIALISE les valeurs pour la couche ORM
			EleveORM objORM;
			for (EleveDAO objDAO : tabDAO)
			{
				objORM = new EleveORM(
						objDAO.getStatutEleve(),
						CiviliteORM.read(objDAO.getCivilite_id()));
				tabORM.add(objORM);
				
				//FIXME affichage des adresses ?
			}
		}
		else 
		{			
			throw new SQLException ("Pas d'Eleve dans la BDD");
		}
		
		return tabORM;
	}
	
	/**
	 * METHODE: MISE A JOUR Eleve
	 * @param id
	 * @param statut
	 * @param civilite
	 * @param adresse
	 * @return
	 * @throws SQLException
	 */
	public static EleveORM update(Integer id,StatutEleve statut, CiviliteORM civilite) throws SQLException {
		
		EleveORM objORM = null;
		boolean isExistDAOElev, isExistDAOCiv = false;
		
		// Tester si les objets existent
		isExistDAOElev = EleveDAO.dbExistFromId(id);

		// FIXME est-ce nécessaire ?
		isExistDAOCiv = CiviliteDAO.dbExistFromId(id);
		
		// Récupérer l'objet en base
		if (isExistDAOElev && isExistDAOCiv)
		{
			EleveDAO objDAO = EleveDAO.dbSelectFromId(id);
			// Mettre à jour les champs
			objDAO.setStatutEleve(statut);
			objDAO.setCivilite_id(civilite.getId());
			// Mettre à jour la base de données
			if (objDAO.dbUpdate())
			{
				objORM = new EleveORM(objDAO.getId(),
						statut,	
						civilite);
			}
			else
			{
				throw new  SQLException("Erreur modification de l'Eleve en base de données");
			}

		}
		else
		{
			throw new  SQLException("Erreur id inexistant en base de données");
		}
		return objORM;
	}

	/**
	 * METHODE: EFFACER Eleve
	 * @param id
	 * @return boolean
	 * @throws SQLException 
	 */
	public boolean delete(Integer id) throws SQLException
	{
		boolean ret = false;
		// FIXME : il faut détruire les adresses et le lien de la table de liaison (méthode de Eleve_has_Adresse)
		if (CiviliteDAO.dbDeleteFromId(EleveORM.read(id).getCivilite().getId()))
			ret = EleveDAO.dbDeleteFromId(id);
		return ret;
	}
	
}
