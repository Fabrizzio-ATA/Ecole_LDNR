package packageORM;
import java.sql.SQLException;
import java.util.ArrayList;

import packageDAO.ClasseDAO;
import packageDAO.Eleve_has_ClasseDAO;
import packageDAO.Niveau;
import packageException.InputValueTooLongException;

public class ClasseORM {
	
	/**
	 *  Attributs
	 */
	
	private Integer id = Integer.valueOf(0);
		
	private String nom;
	
	private Niveau niveau;
	
	private String periode;
	
	private EnseignantORM enseignant;
	
	private ArrayList <EleveORM> tabEleves;

	/**
	 *  Constructeurs
	 */
	
	private ClasseORM(String nom, Niveau niveau, String periode, EnseignantORM enseignant,
			ArrayList<EleveORM> tabEleves) {
		super();
		this.setNom(nom);
		this.setNiveau(niveau);
		this.setPeriode(periode);
		this.setEnseignant(enseignant);
		this.setTabEleves(tabEleves);
	}
	
	private ClasseORM(Integer id, String nom, Niveau niveau, String periode, EnseignantORM enseignant,
			ArrayList<EleveORM> tabEleves) {
		this (nom,niveau,periode,enseignant,tabEleves);
		this.setId(id);
	}
	
	/**
	 *  Methodes
	 * @throws InputValueTooLongException 
	 * 
	 */

	/**
	 * 	METHODE: CREER CIVILITE
	 * 
	 * 	 Les objets enseignant et eleves doivent déjà exister.
	 * 	 Il faut vérifier que la valeur transmise correspond à la donnée en base
	 * 	// obj.getEnseignant().hasSameContent(EnseignantDAO.dbSelectFromId(obj.getEnseignant().getId()));
	 * 	NOTE : Pas besoin de s'occuper des élèves à la création
	 * @throws InputValueTooLongException 
	 * @throws SQLException 
	 */

	public static ClasseORM create(String nom, Niveau niveau, String periode, EnseignantORM enseignant, ArrayList<EleveORM> tabEleves) throws InputValueTooLongException, SQLException {
			
		ClasseORM objORM = null;
		
		// Créer la classe DAO de l'objet courant en récupérant les id des objets en attribut
		ClasseDAO objDAO = new ClasseDAO(nom,niveau,periode,enseignant.getId());
			
		// Insérer la classe dans la base de données
		if (objDAO.dbInsert())
		{
			objORM = new ClasseORM(nom, niveau, periode, enseignant, tabEleves);
		}
		else {
			throw new  SQLException("Erreur d'enregistrement sur la civilité en base de données");
		}
		
		return objORM;
	}

	// METHODE : LIRE UNE CLASSE
	public static ClasseORM read(Integer id) throws SQLException {
		// Récupérer l'objet DAO correspondant:
		ClasseDAO objDAO = ClasseDAO.dbSelectFromId(id);
		
		// FIXME : Le tableau d'élève ne doit être rempli que lorsque la classe existe et la méthode
		// getElevesFromClasse permet de les extraire de la base de données.
		
		// Récupérer le tableau d'élèves
		ArrayList<EleveORM> tabEleves = ClasseORM.getElevesFromClasse(id);
		
		// Récupérer l'enseignant
		EnseignantORM enseignant = EnseignantORM.read(objDAO.getEnseignant_id());
		
		// Creer un obj ClasseORM avec les valeurs trouvées en base
		ClasseORM obj = new ClasseORM(objDAO.getId(),
								objDAO.getNom(),
								objDAO.getNiveau(),
								objDAO.getPeriode(),
								enseignant,
								tabEleves
								);
		
		return obj;
	}

	// LIRE UNE LISTE DE CLASSES
	public static ArrayList<ClasseORM> read() throws SQLException {
		ArrayList <ClasseORM> tabClasses = new ArrayList<ClasseORM>(); 
		
		for (ClasseDAO objDAO : ClasseDAO.dbSelectAll())
		{
			tabClasses.add(ClasseORM.read(objDAO.getId()));
		}
		return tabClasses;
	}
	
	private static ArrayList<EleveORM> getElevesFromClasse(Integer idClasse) {
		ArrayList <EleveORM> tabEleves = new ArrayList<EleveORM>();
		
		for (Eleve_has_ClasseDAO objDAO : Eleve_has_ClasseDAO.dbSelectAll()	 )
		{
			if ( objDAO.getId_Classe() == idClasse)
			{
				//TODO	tabEleves.add(EleveORM.getEleveFromId(objDAO.getId_Eleve()));
			}
		}
			
		return tabEleves;
	}

	public static ClasseORM update(Integer id, String nom, Niveau niveau, String periode, EnseignantORM enseignant) throws InputValueTooLongException, SQLException {
		
		ClasseORM objORM = null;
		boolean isExistDAO = false;
		
		// Tester si l'objet existe
		isExistDAO = ClasseDAO.dbExistFromId(id);
		
		// Récupérer l'objet en base
		if (isExistDAO)
		{
			ClasseDAO objDAO = ClasseDAO.dbSelectFromId(id);
			objDAO.setNom(nom);
			objDAO.setNiveau(niveau);
			objDAO.setPeriode(periode);
			objDAO.setEnseignant_id(enseignant.getId());
			
			if (objDAO.dbUpdate())
			{
				objORM = new ClasseORM(
							objDAO.getId(),
							objDAO.getNom(),
							objDAO.getNiveau(),
							objDAO.getPeriode(),
							enseignant,
							null);
			}
			else
			{
				throw new  SQLException("Erreur modification de la classe en base de données");
			}
		}
		else
		{
			throw new  SQLException("Erreur id inexistant en base de données");
		}
		return objORM;
		
	}

	//@Override
	public static boolean delete(Integer id) {
		boolean ret = false;
		
		Eleve_has_ClasseDAO.dbDeleteFromIdClasse(id);
		ClasseDAO.dbDeleteFromId(id);
		return ret;
	}

	
	
	/**
	 *  Accesseurs et mutateurs
	 */
	
	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau2) {
		this.niveau = niveau2;
	}

	public EnseignantORM getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(EnseignantORM enseignant) {
		this.enseignant = enseignant;
	}

	public ArrayList<EleveORM> getTabEleves() {
		return tabEleves;
	}

	public void setTabEleves(ArrayList<EleveORM> tabEleves) {
		this.tabEleves = tabEleves;
	}
}
