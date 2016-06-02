package packageORM;
import java.util.ArrayList;

import packageDAO.ClasseDAO;
import packageDAO.EleveDAO;
import packageDAO.EnseignantDAO;
import packageException.InputValueTooLongException;

public class ClasseORM implements InterfaceORM<ClasseORM> {
	
	/**
	 *  Attributs
	 */
	
	private Integer id = Integer.valueOf(0);
		
	private String nom;
	
	private String periode;
	
	private String niveau;
	
	private EnseignantDAO enseignant;
	
	private ArrayList <EleveDAO> tabEleves;

	/**
	 *  Constructeurs
	 */
	
	public ClasseORM(String nom, String periode, String niveau, EnseignantDAO enseignant,
			ArrayList<EleveDAO> tabEleves) {
		super();
		this.setNom(nom);
		this.setPeriode(periode);
		this.setNiveau(niveau);
		this.setEnseignant(enseignant);
		this.setTabEleves(tabEleves);
	}
	
	public ClasseORM(Integer id, String nom, String periode, String niveau, EnseignantDAO enseignant,
			ArrayList<EleveDAO> tabEleves) {
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
	@Override
	public boolean create(ClasseORM obj) throws InputValueTooLongException {
		
		boolean ret = false;
	
		// Créer la classe DAO de l'objet courant en récupérant les id des objets précédents
		ClasseDAO objDAO = new ClasseDAO(obj.getNom(),obj.getNiveau(),obj.getPeriode(),obj.getEnseignant().getId());
		
		// Insérer la classe dans la base de données
		ret = objDAO.dbInsert();
		
		return ret;
	}

	//@Override
	public static ClasseORM read(Integer id) {

		ClasseORM obj;

		// Récupérer l'objet DAO correspondant:
		ClasseDAO objDAO = ClasseDAO.dbSelectFromId(id);
		// TODO Récupérer le tableau d'élèves
		ArrayList<EleveDAO> tabEleves = new ArrayList<EleveDAO>();
//		for (EleveDAO eleve : EleveHasClasse.getElevesFromClasse())
//		{
//			tabEleves.add(eleve);
//		}
		
		// Compléter l'objet courant avec les valeurs trouvées en base
		obj = new ClasseORM(objDAO.getId(),
								objDAO.getNom(),
								objDAO.getPeriode(),
								objDAO.getNiveau().toString(),
								EnseignantDAO.dbSelectFromId(objDAO.getEnseignant_id()),
								tabEleves
								);
		
		return obj;
	}

	//@Override
	public ArrayList<ClasseORM> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ClasseORM obj) {
		// TODO Auto-generated method stub
		for (EleveDAO eleve : obj.getTabEleves())
		{
			// TODO associer les élèves à la table de liaison Eleve_has_Classe
		}
		
		return false;
	}

	// TODO pourquoi enlever @Override
	public static boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return true;
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

	public EnseignantDAO getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(EnseignantDAO enseignant) {
		this.enseignant = enseignant;
	}

	public ArrayList<EleveDAO> getTabEleves() {
		return tabEleves;
	}

	public void setTabEleves(ArrayList<EleveDAO> tabEleves) {
		this.tabEleves = tabEleves;
	}
}
