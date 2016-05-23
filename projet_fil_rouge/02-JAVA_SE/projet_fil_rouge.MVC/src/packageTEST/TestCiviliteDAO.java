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

import packageDAO.CiviliteDAO;

public class TestCiviliteDAO {
	
	static List<Integer> tabIdToDelete = new ArrayList<Integer>();
	
	/*
	 * Méthode outils
	 */
	/**
	 * 
	 * @return un objet valide
	 */
	private static CiviliteDAO newValidObj(){
		CiviliteDAO obj = null;
		try{
			obj = new CiviliteDAO("Robbin", "Louis", "M", Date.valueOf(LocalDate.of(1992, 12, 31)));
		} catch(Exception e) {
			fail("Objet conforme non construit");
		}
		return obj;
	}
	/**
	 * 
	 * @return un objet valide mis en base de données
	 */
	private static CiviliteDAO dbInsertNewValidObj(){
		CiviliteDAO obj = newValidObj();
		if(obj.dbInsert()){
			tabIdToDelete.add(obj.getId());
		}
		System.out.println("id "+obj.getId()+" : à été creer lors de la création de l'objet valide avec insertion");
		return obj;
	}
	
	@Test
	public void testDbInsert() {
		CiviliteDAO obj = newValidObj();

		//test de l'insertion
		assertTrue("test insertion", obj.dbInsert());
		
		int id = obj.getId();
		assertTrue("Test résultat d'insertion", id>0);
		
		tabIdToDelete.add(id);
		System.out.println("id "+id+" : à été creer lors du test DbInsert");
		
		
	}
	
	@Test
	public void testDbSelectFromId() {
		CiviliteDAO obj = dbInsertNewValidObj();
		CiviliteDAO obj2 = CiviliteDAO.dbSelectFromId(obj.getId());
		
		System.out.println("id "+obj.getId()+" : à été creer lors du test DbSelectFromId");
		//Vérification de la création d'objet
		assertNotNull("test si le select n'est pas null", obj2);
		
		//L'objet récuperer par le select correspond bien à l'objet inséré
		assertTrue("test du select par id", obj.hasSameContent(obj2));
	}
	
	@Test
	public void testDbExistFromId() {
		//id inexistant doit renvoyer false
		assertFalse("id inexistant doit renvoyer false", CiviliteDAO.dbExistFromId(-1));
		
		//id existant doit renvoyer true
		CiviliteDAO obj = dbInsertNewValidObj();
		assertTrue("id existant doit renvoyer true", CiviliteDAO.dbExistFromId(obj.getId()));
	}
	
	@Test
	public void testDbUpdate() {
		CiviliteDAO obj = newValidObj();
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyé false
		assertFalse("Test d'update sur un enregistrement inexistant", obj.dbUpdate());
		
		CiviliteDAO obj2 = dbInsertNewValidObj();
		System.out.println("La date de naissance avant l'update : "+obj2.getDate_naiss());
		obj2.setDate_naiss(LocalDate.of(1992, 3, 20));
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyé true
		assertTrue("Test d'update sur un enregistrement existant", obj2.dbUpdate());
		
		//Test si les valeurs en base de donnée correspondent bien à l'update effectué
		CiviliteDAO obj3 = CiviliteDAO.dbSelectFromId(obj2.getId());
		System.out.println("La date de naissance après l'update : "+obj3.getDate_naiss());
		assertTrue("test que les valeurs en base de donnée correspondent bien à l'update effectué", obj2.hasSameContent(obj3));
	}
	
	@AfterClass
	public static void oneTimeTearDown(){
		for (Integer i : tabIdToDelete){
			CiviliteDAO.dbDeleteFromId(i);
			System.out.println("id "+i+" : à bien été supprimé");
		}
		
	}

}

