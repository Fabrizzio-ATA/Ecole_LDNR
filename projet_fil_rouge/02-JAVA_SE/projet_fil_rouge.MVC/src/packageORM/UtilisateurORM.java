package packageORM;

import java.sql.SQLException;
import java.util.ArrayList;

import packageDAO.Role;
import packageDAO.UtilisateurDAO;

public class UtilisateurORM {

	private Integer id;
	private String login;
	private String password;
	private Role role;

	/**
	 * 
	 * @param id
	 * @param login
	 * @param password
	 * @param enseignant
	 */
	private UtilisateurORM(Integer id, String login, String password, Role enseignant) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = enseignant;
	}

	
	/**
	 *  Methodes
	 * @throws SQLException 
	 *  
	 */
	
	public static UtilisateurORM create(String login, String password, Role role) throws SQLException {
		
		UtilisateurORM objORM = null;
		
		// Créer la classe DAO de l'objet courant en récupérant les id des objets en attribut
		UtilisateurDAO objDAO = new UtilisateurDAO(login, password,role);
		
		// Insérer la classe dans la base de données
		if (true == objDAO.dbInsert())
		{
			objORM = new UtilisateurORM(objDAO.getId(), 
						objDAO.getLogin(), 
						objDAO.getPassword(),
						objDAO.getRole());
		}
		else
		{
			throw new  SQLException("Erreur d'enregistrement sur l'utilisateur en base de données");
		}
			return objORM;
	}

	//@Override
	public static UtilisateurORM read(Integer id) throws SQLException {

		UtilisateurORM objORM = null;
		boolean isExistDAO = UtilisateurDAO.dbExistFromId(id);
		
		if (isExistDAO)
		{
			// Récupérer l'objet DAO correspondant:
			UtilisateurDAO objDAO = UtilisateurDAO.dbSelectFromId(id);
			
			// Creer un obj ClasseORM avec les valeurs trouvées en base
			objORM = new UtilisateurORM(objDAO.getId(),
									objDAO.getLogin(),
									objDAO.getPassword(),
									objDAO.getRole());
		}
		else {
			throw new SQLException ("UtilisateurBEAN introuvable");
		}
		
		return objORM;
	}
	
	//@Override
	public ArrayList<UtilisateurORM> read() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// METHODE: MISE A JOUR UTILISATEUR
	public static UtilisateurORM update(Integer id, String login, String password,Role role) throws SQLException {
		
		UtilisateurORM objORM = null;
		boolean isExistDAO = false;
		
		// Tester si l'objet existe
		isExistDAO = UtilisateurDAO.dbExistFromId(id);
				
		// Récupérer l'objet en base
		if (isExistDAO)
		{
			UtilisateurDAO objDAO = UtilisateurDAO.dbSelectFromId(id);
			// Mettre à jour les champs
			objDAO.setLogin(login);
			objDAO.setPassword(password);
			objDAO.setRole(role);
			// Mettre à jour la base de données
			if (objDAO.dbUpdate())
			{
				objORM = new UtilisateurORM(objDAO.getId(),
							objDAO.getLogin(),
							objDAO.getPassword(),
							objDAO.getRole());
			}
			else
			{
				throw new  SQLException("Erreur modification de l'utilisateur en base de données");
			}

		}
		else
		{
			throw new  SQLException("Erreur id inexistant en base de données");
		}
		return objORM;
	}

	// TODO METHODE: EFFACER UTILISATEUR
	public static boolean delete(Integer id) {
		
		return UtilisateurDAO.dbDeleteFromId(id);
	}

	
	/**
	 * ACCESSEURS
	 * @return
	 */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
