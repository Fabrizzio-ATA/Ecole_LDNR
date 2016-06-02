package packageORM;
import java.util.ArrayList;

import packageDAO.ClasseDAO;
import packageDAO.EleveDAO;
import packageDAO.Eleve_has_ClasseDAO;
import packageDAO.EnseignantDAO;
import packageException.InputValueTooLongException;

public class ClasseORM {
	
	/**
	 *  Attributs
	 */
	
	private Integer id = Integer.valueOf(0);
		
	private String nom;
	
	private String periode;
	
	private String niveau;
	
	private EnseignantORM enseignant;
	
	private ArrayList <EleveORM> tabEleves;

	/**
	 *  Constructeurs
	 */
	
	private ClasseORM(String nom, String periode, String niveau, EnseignantORM enseignant,
			ArrayList<EleveORM> tabEleves) {
		super();
		this.setNom(nom);
		this.setPeriode(periode);
		this.setNiveau(niveau);
		this.setEnseignant(enseignant);
		this.setTabEleves(tabEleves);
	}
	
	private ClasseORM(Integer id, String nom, String periode, String niveau, EnseignantORM enseignant,
			ArrayList<EleveORM> tabEleves) {
		this (nom,periode,niveau,enseignant,tabEleves);
		this.setId(id);
	}
	
	/**
	 *  Methodes
	 * @throws InputValueTooLongException 
	 * 
	 */

	/**
	 * 	 Les objets enseignant et eleves doivent déjà exister.
	 * 	 Il faut vérifier que la valeur transmise correspond à la donnée en base
	 * 	// obj.getEnseignant().hasSameContent(EnseignantDAO.dbSelectFromId(obj.getEnseignant().getId()));
	 * 	NOTE : Pas besoin de s'occuper des élèves à la création
	 */

	public static boolean create(String nom, String periode, String niveau, EnseignantORM enseignant, ArrayList<EleveORM> tabEleves) {
		
		
		boolean ret = false;
		
		ClasseORM obj = new ClasseORM(nom, periode, niveau, enseignant, null);
	
		// Créer la classe DAO de l'objet courant en récupérant les id des objets précédents
		ClasseDAO objDAO;
		try {
			objDAO = new ClasseDAO(obj.getNom(),obj.getNiveau(),obj.getPeriode(),obj.getEnseignant().getId());
			//TODO EnseignantORM.getID()
			
			// Insérer la classe dans la base de données
			ret = objDAO.dbInsert();
		} catch (InputValueTooLongException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		return ret;
	}

	//@Override
	public static ClasseORM read(Integer id) {
		// Récupérer l'objet DAO correspondant:
		ClasseDAO objDAO = ClasseDAO.dbSelectFromId(id);
		// Récupérer le tableau d'élèves
		ArrayList<EleveORM> tabEleves = ClasseORM.getElevesFromClasse(id);
		
		EnseignantORM enseignant = null;
		// TODO enseignant = new EnseignantORM.read(objDAO.getEnseignant_id());
		
		// Creer un obj ClasseORM avec les valeurs trouvées en base
		ClasseORM obj = new ClasseORM(objDAO.getId(),
								objDAO.getNom(),
								objDAO.getPeriode(),
								objDAO.getNiveau().toString(),
								enseignant,
								tabEleves
								);
		
		return obj;
	}

	private static ArrayList<EleveORM> getElevesFromClasse(Integer id2) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public ArrayList<ClasseORM> read() {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean update(String nom, String periode, String niveau, EnseignantORM enseignant) {
		
		boolean ret = false;
		
		ClasseORM obj = new ClasseORM(nom, periode, niveau, enseignant, null);
	
		// Créer la classe DAO de l'objet courant en récupérant les id des objets précédents
		try {
			ClasseDAO objDAO = new ClasseDAO(obj.getNom(),obj.getNiveau(),obj.getPeriode(),obj.getEnseignant().getId());
		} catch (InputValueTooLongException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		return ret;
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

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau2) {
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
