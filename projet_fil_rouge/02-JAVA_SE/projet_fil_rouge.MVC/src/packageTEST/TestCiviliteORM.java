package packageTEST;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import packageORM.CiviliteORM;

public class TestCiviliteORM {
	
	static List<Integer> tabIdToDelete = new ArrayList<Integer>();
	
	/*
	 * Méthodes de test
	 */
	@Test
	public void testCreate() {
		CiviliteORM obj = null;
		try {
			obj = CiviliteORM.create("Robbin", "Louis", "M", LocalDate.of(2007, 03, 21));
		} catch (SQLException e) {
			fail("erreur de création en db");
		}
		assertNotNull("Objet correct créé", obj);
		assertTrue(obj.getId() != null && obj.getId() != 0);
	}
	
	/*
	 * Méthode outils
	 */
	/**
	 * 
	 * @return un objet valide
	 */
	private static CiviliteORM newValidObj(){
		CiviliteORM obj = null;
		try{
			obj.create("Robbin", "Louis", "M", LocalDate.of(2007, 07, 21));
		} catch(Exception e) {
			
		}
		return obj;
	}
	/**
	 * 
	 * @return un objet valide mis en base de données
	 */
//	private static CiviliteORM dbInsertNewValidObj(){
//		CiviliteORM obj = newValidObj();
//		if(obj.dbInsert()){
//			tabIdToDelete.add(obj.getId());
//		}
//		System.out.println("id "+obj.getId()+" : à été creer lors de la création de l'objet valide avec insertion");
//		return obj;
//	}
//	
//	@Test
//	public void testDbInsert() {
//		CiviliteORM obj = newValidObj();
//
//		//test de l'insertion
//		assertTrue("test insertion", obj.dbInsert());
//		
//		int id = obj.getId();
//		assertTrue("Test résultat d'insertion", id>0);
//		
//		tabIdToDelete.add(id);
//		System.out.println("id "+id+" : à été creer lors du test DbInsert");
//		
//		
//	}
//	
//	@Test
//	public void testDbSelectFromId() {
//		CiviliteORM obj = dbInsertNewValidObj();
//		CiviliteORM obj2 = CiviliteORM.dbSelectFromId(obj.getId());
//		
//		System.out.println("id "+obj.getId()+" : à été creer lors du test DbSelectFromId");
//		//Vérification de la création d'objet
//		assertNotNull("test si le select n'est pas null", obj2);
//		
//		//L'objet récuperer par le select correspond bien à l'objet inséré
//		assertTrue("test du select par id", obj.hasSameContent(obj2));
//	}
//	
//	@Test
//	public void testDbExistFromId() {
//		//id inexistant doit renvoyer false
//		assertFalse("id inexistant doit renvoyer false", CiviliteORM.dbExistFromId(-1));
//		
//		//id existant doit renvoyer true
//		CiviliteORM obj = dbInsertNewValidObj();
//		assertTrue("id existant doit renvoyer true", CiviliteORM.dbExistFromId(obj.getId()));
//	}
//	
//	@Test
//	public void testDbUpdate() {
//		CiviliteORM obj = newValidObj();
//		//Test d'update sur un enregistrement inexistant
//		//Doit renvoyé false
//		assertFalse("Test d'update sur un enregistrement inexistant", obj.dbUpdate());
//		
//		CiviliteORM obj2 = dbInsertNewValidObj();
//		System.out.println("La date de naissance avant l'update : "+obj2.getDate_naiss());
//		obj2.setDate_naiss(LocalDate.of(1992, 3, 20));
//		//Test d'update sur un enregistrement inexistant
//		//Doit renvoyé true
//		assertTrue("Test d'update sur un enregistrement existant", obj2.dbUpdate());
//		
//		//Test si les valeurs en base de donnée correspondent bien à l'update effectué
//		CiviliteORM obj3 = CiviliteORM.dbSelectFromId(obj2.getId());
//		System.out.println("La date de naissance après l'update : "+obj3.getDate_naiss());
//		assertTrue("test que les valeurs en base de donnée correspondent bien à l'update effectué", obj2.hasSameContent(obj3));
//	}
//	
//	@AfterClass
//	public static void oneTimeTearDown(){
//		for (Integer i : tabIdToDelete){
//			CiviliteORM.dbDeleteFromId(i);
//			System.out.println("id "+i+" : à bien été supprimé");
//		}
//		
//	}
//
}

