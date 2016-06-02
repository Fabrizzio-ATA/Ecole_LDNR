package packageTEST;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import packageDAO.ClasseDAO;
import packageException.InputValueTooLongException;

public class TestClasseDAO {
	
	static List<Integer> tabIdToDelete = new ArrayList<Integer>();
	
	/*
	 * Méthode outils
	 */
	/**
	 * 
	 * @return un objet valide
	 */
	private static ClasseDAO newValidObj() throws InputValueTooLongException{
		ClasseDAO obj = null;
		try {
			obj = new ClasseDAO("Rob", "CM1", "2016", 3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Objet conforme non construit");
		}
		return obj;
	}
	/**
	 * 
	 * @return un objet valide mis en base de données
	 */
	protected static ClasseDAO dbInsertNewValidObj(){
		ClasseDAO obj=null;
		try {
			obj = newValidObj();
		} catch (InputValueTooLongException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		if(obj.dbInsert()){
			tabIdToDelete.add(obj.getId());
		}
		System.out.println("id "+obj.getId()+" : à été creer lors de la création de l'objet valide Classe avec insertion");
		
		return obj;
		
	}
	
	@Test
	public void testDbInsert() {
		ClasseDAO obj=null;
		try {
			obj = newValidObj();
		} catch (InputValueTooLongException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}

		//test de l'insertion
		assertTrue("test insertion", obj.dbInsert());
		
		int id = obj.getId();
		assertTrue("Test résultat d'insertion", id>0);
		
		tabIdToDelete.add(id);
		System.out.println("id "+id+" : à été creer lors du test DbInsert");
		
		
	}
	
	@Test
	public void testDbSelectFromId() {
		ClasseDAO obj = dbInsertNewValidObj();
		ClasseDAO obj2 = ClasseDAO.dbSelectFromId(obj.getId());
		
		System.out.println("id "+obj.getId()+" : à été creer lors du test DbSelectFromId");
		//Vérification de la création d'objet
		assertNotNull("test si le select n'est pas null", obj2);
		
		//L'objet récuperer par le select correspond bien à l'objet inséré
		assertTrue("test du select par id", obj.hasSameContent(obj2));
	}
	
	@Test
	public void testDbExistFromId() {
		//id inexistant doit renvoyer false
		assertFalse("id inexistant doit renvoyer false", ClasseDAO.dbExistFromId(-1));
		
		//id existant doit renvoyer true
		ClasseDAO obj = dbInsertNewValidObj();
		assertTrue("id existant doit renvoyer true", ClasseDAO.dbExistFromId(obj.getId()));
	}
	
	@Test
	public void testDbUpdate() {
		ClasseDAO obj=null;
		try {
			obj = newValidObj();
		} catch (InputValueTooLongException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyé false
		assertFalse("Test d'update sur un enregistrement inexistant", obj.dbUpdate());
		
		ClasseDAO obj2 = dbInsertNewValidObj();
		
		obj2.setNiveau("CM2");
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyé true
		assertTrue("Test d'update sur un enregistrement existant", obj2.dbUpdate());
		
		//Test si les valeurs en base de donnée correspondent bien à l'update effectué
		ClasseDAO obj3 = ClasseDAO.dbSelectFromId(obj2.getId());
		assertTrue("test que les valeurs en base de donnée correspondent bien à l'update effectué", obj2.hasSameContent(obj3));
	}
	
	@AfterClass
	public static void oneTimeTearDown(){
		for (Integer i : tabIdToDelete){
			ClasseDAO.dbDeleteFromId(i);
			System.out.println("id "+i+" : à bien été supprimé");
		}
		
	}

}

