package packageAppli;

import java.time.LocalDate;

import packageDAO.MaTableDAO;
import packageException.InputValueInvalidException;
import packageException.InputValueTooLongException;

public class MaTable {
	private Integer id = Integer.valueOf(0);
	private MaTableDAO dao_obj = new MaTableDAO();
	protected String name = "";
	protected LocalDate date = LocalDate.now();

	//************************************************************************
	//** CONSTRUCTORS
	//************************************************************************
	
	/**
	 *	Constructeur 
	 * 	FIXME pour l'insertion d'un nouvel objet.
	 * 
	 * @param nom
	 * @param date
	 * @throws InputValueTooLongException
	 * @throws InputValueInvalidException 
	 */
	public MaTable(String nom, LocalDate date) throws InputValueTooLongException, InputValueInvalidException {
		this.setName(nom);
		this.setDate(date);
	}
	
	/**
	 *  Constructeur 
	 *  FIXME pour la mise à jour d'un objet
	 * 
	 * @param id
	 * @param nom
	 * @param date
	 * @throws InputValueTooLongException
	 * @throws InputValueInvalidException
	 */
	public MaTable(Integer id, String nom, LocalDate date) throws InputValueTooLongException, InputValueInvalidException {
		this(nom, date);
		this.setId(id);
	}
	
	/**
	 *  FIXME Constructeur pour la lecture d'un objet
	 * 
	 * @param id
	 */
//	public MaTable(Integer id) {
//		dao_obj.dbSelectFromId(id);
//	}
	
	//************************************************************************
	//** GETTERS SETTERS
	//************************************************************************

	public Integer getId() {
		return id;
	}

	public void setId (Integer id) throws InputValueInvalidException{
		if (null != id && 0 < id)
		{
			this.id = id;
		}
		else
			throw new InputValueInvalidException("# ERROR ! id value");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws InputValueTooLongException {
		if (name.length() > 2 && name.length() < 16)
			this.name = name;
		else
			throw new InputValueTooLongException("# ERREUR ! Nom trop long",16,name.length());
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	//************************************************************************
	//** METHODES
	//************************************************************************
	/**
	 * Description of the method hasSameContent
	 * @param obj
	 * @return boolean
	 */
	public boolean hasSameContent(MaTable obj){
		return(this.getName().equals(obj.getName())
				&& this.getDate().equals(obj.getDate()));
	}
	
	
	// FIXME utiliser directement ces appels de méthodes dnas le constructeur
	public boolean insertElementTable() throws InputValueInvalidException
	{
		// Appel de la méthode DAO pour ajouter un élément
		return dao_obj.dbInsert(this);
	}
	
	public boolean updateElementTable() throws InputValueInvalidException
	{
		// Appel de la méthode DAO pour mettre à jour un élément
		return dao_obj.dbUpdate(this);
	}
	
}
