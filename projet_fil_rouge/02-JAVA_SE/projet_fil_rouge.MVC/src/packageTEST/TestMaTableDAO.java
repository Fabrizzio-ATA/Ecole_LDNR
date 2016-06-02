package packageTEST;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import packageORM.MaTable;
import packageDAO.MaTableDAO;
import packageException.InputValueInvalidException;
import packageException.InputValueTooLongException;

public class TestMaTableDAO {
	
	static List<Integer> tabIdToDelete = new ArrayList<Integer>();
	
	static MaTableDAO dao_obj = new MaTableDAO();
	/*
	 * Méthode outils
	 */
	/**
	 * 
	 * @return un objet valide
	 */
	private MaTable newValidObj(){
		MaTable new_obj = null;
		try{
			new_obj = new MaTable("TestName", LocalDate.now());
		} catch(Exception e) {
			fail("Objet conforme non construit");
		}
		return new_obj;
	}
	/**
	 * 
	 * @return un objet valide mis en base de données
	 */
	private MaTable dbInsertNewValidObj(){
		MaTable obj = newValidObj();
		try {
			if(dao_obj.dbInsert(obj)){
				tabIdToDelete.add(obj.getId());
			}
		} catch (InputValueInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("id "+obj.getId()+" : à été créé lors de la création de l'objet valide avec insertion");
		return obj;
	}
	
	
	@Test
	public void testDbInsert() {
		System.out.println("Test dbInsert sur la table : " + dao_obj.getTable());
		MaTable obj = newValidObj();

		//test de l'insertion
		try {
			assertTrue("test insertion", dao_obj.dbInsert(obj));
		} catch (InputValueInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int id = obj.getId();
		assertTrue("Test résultat d'insertion", id>0);
		
		tabIdToDelete.add(id);
		System.out.println("id "+id+" : à été créé lors du test DbInsert");
		
		
	}
	
	@Test
	public void testDbSelectFromId() {
		MaTable obj = dbInsertNewValidObj();
		MaTable obj2 = dao_obj.dbSelectFromId(obj.getId());
		
		System.out.println("id "+obj.getId()+" : à été créé lors du test DbSelectFromId");
		//Vérification de la création d'objet
		assertNotNull("test si le select n'est pas null", obj2);
		
		//L'objet récuperé par le select correspond bien à l'objet inséré
		assertTrue("test du select par id", obj.hasSameContent(obj2));
	}
	
	@Test
	public void testDbExistFromId() {
		//id inexistant doit renvoyer false
		assertFalse("id inexistant doit renvoyer false", dao_obj.dbExistFromId(-1));
		
		//id existant doit renvoyer true
		MaTable obj = dbInsertNewValidObj();
		assertTrue("id existant doit renvoyer true", dao_obj.dbExistFromId(obj.getId()));
	}
	
	@Test
	public void testDbUpdate() {
		MaTable obj = newValidObj();
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyer false
		assertFalse("Test d'update sur un enregistrement inexistant", dao_obj.dbUpdate(obj));
		
		MaTable obj2 = dbInsertNewValidObj();
		try {
			obj2.setName("Updated");
		} catch (InputValueTooLongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Test d'update sur un enregistrement existant
		//Doit renvoyer true
		assertTrue("Test d'update sur un enregistrement existant", dao_obj.dbUpdate(obj2));
		
		//Test si les valeurs en base de donnée correspondent bien à l'update effectué
		MaTable obj3 = dao_obj.dbSelectFromId(obj2.getId());
		assertTrue("test que les valeurs en base de donnée correspondent bien à l'update effectué", obj2.hasSameContent(obj3));
	}
	
	@AfterClass
	public static void oneTimeTearDown(){
		for (Integer i : tabIdToDelete){
			dao_obj.dbDeleteFromId(i);
			System.out.println("id "+i+" : à bien été supprimé");
		}
		
	}

}
