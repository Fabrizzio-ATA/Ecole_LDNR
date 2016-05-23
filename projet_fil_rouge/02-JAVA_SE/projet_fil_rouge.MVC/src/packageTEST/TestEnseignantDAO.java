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

import packageDAO.EnseignantDAO;

public class TestEnseignantDAO {
	
	static List<Integer> tabIdToDelete = new ArrayList<Integer>();
	
	/*
	 * Méthode outils
	 */
	/**
	 * 
	 * @return un objet valide
	 */
	private static EnseignantDAO newValidObj(){
		EnseignantDAO obj = null;
		try{
			obj = new EnseignantDAO(1, 1, 1);
		} catch(Exception e) {
			fail("Objet conforme non construit");
		}
		return obj;
	}
	/**
	 * 
	 * @return un objet valide mis en base de données
	 */
	private static EnseignantDAO dbInsertNewValidObj(){
		EnseignantDAO obj = newValidObj();
		if(obj.dbInsert()){
			tabIdToDelete.add(obj.getId());
		}
		System.out.println("id "+obj.getId()+" : à été creer lors de la création de l'objet valide avec insertion");
		return obj;
	}
	
	@Test
	public void testDbInsert() {
		EnseignantDAO obj = newValidObj();

		//test de l'insertion
		assertTrue("test insertion", obj.dbInsert());
		
		int id = obj.getId();
		assertTrue("Test résultat d'insertion", id>0);
		
		tabIdToDelete.add(id);
		System.out.println("id "+id+" : à été creer lors du test DbInsert");
		
		
	}
	
	@Test
	public void testDbSelectFromId() {
		EnseignantDAO obj = dbInsertNewValidObj();
		EnseignantDAO obj2 = EnseignantDAO.dbSelectFromId(obj.getId());
		
		System.out.println("id "+obj.getId()+" : à été creer lors du test DbSelectFromId");
		//Vérification de la création d'objet
		assertNotNull("test si le select n'est pas null", obj2);
		
		//L'objet récuperer par le select correspond bien à l'objet inséré
		assertTrue("test du select par id", obj.hasSameContent(obj2));
	}
	
	@Test
	public void testDbExistFromId() {
		//id inexistant doit renvoyer false
		assertFalse("id inexistant doit renvoyer false", EnseignantDAO.dbExistFromId(-1));
		
		//id existant doit renvoyer true
		EnseignantDAO obj = dbInsertNewValidObj();
		assertTrue("id existant doit renvoyer true", EnseignantDAO.dbExistFromId(obj.getId()));
	}
	
	@Test
	public void testDbUpdate() {
		EnseignantDAO obj = newValidObj();
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyé false
		assertFalse("Test d'update sur un enregistrement inexistant", obj.dbUpdate());
		
		EnseignantDAO obj2 = dbInsertNewValidObj();
		obj2.setAdresse_id(2);
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyé true
		assertTrue("Test d'update sur un enregistrement existant", obj2.dbUpdate());
		
		//Test si les valeurs en base de donnée correspondent bien à l'update effectué
		EnseignantDAO obj3 = EnseignantDAO.dbSelectFromId(obj2.getId());
		assertTrue("test que les valeurs en base de donnée correspondent bien à l'update effectué", obj2.hasSameContent(obj3));
	}
	
	@AfterClass
	public static void oneTimeTearDown(){
		for (Integer i : tabIdToDelete){
			EnseignantDAO.dbDeleteFromId(i);
			System.out.println("id "+i+" : à bien été supprimé");
		}
		
	}

}

