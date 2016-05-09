package packageTEST;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import packageDAO.AdresseDAO;

public class TestAdresseDAO {
	
	static List<Integer> tabIdToDelete = new ArrayList<Integer>();
	
	/*
	 * Méthode outils
	 */
	/**
	 * 
	 * @return un objet valide
	 */
	private static AdresseDAO newValidObj(){
		AdresseDAO obj = null;
		try{
			obj = new AdresseDAO("3 avenue de la montagne", "Toulouse", "31300", "0512451245");
		} catch(Exception e) {
			fail("Objet conforme non construit");
		}
		return obj;
	}
	/**
	 * 
	 * @return un objet valide mis en base de données
	 */
	private static AdresseDAO dbInsertNewValidObj(){
		AdresseDAO obj = newValidObj();
		if(obj.dbInsert()){
			tabIdToDelete.add(obj.getId());
		}
		System.out.println("id "+obj.getId()+" : à été creer lors de la création de l'objet valide avec insertion");
		return obj;
	}
	
	@Test
	public void testDbInsert() {
		AdresseDAO obj = newValidObj();

		//test de l'insertion
		assertTrue("test insertion", obj.dbInsert());
		
		int id = obj.getId();
		assertTrue("Test résultat d'insertion", id>0);
		
		tabIdToDelete.add(id);
		System.out.println("id "+id+" : à été creer lors du test DbInsert");
		
		
	}
	
	@Test
	public void testDbSelectFromId() {
		AdresseDAO obj = dbInsertNewValidObj();
		AdresseDAO obj2 = AdresseDAO.dbSelectFromId(obj.getId());
		
		System.out.println("id "+obj.getId()+" : à été creer lors du test DbSelectFromId");
		//Vérification de la création d'objet
		assertNotNull("test si le select n'est pas null", obj2);
		
		//L'objet récuperer par le select correspond bien à l'objet inséré
		assertTrue("test du select par id", obj.hasSameContent(obj2));
	}
	
	@Test
	public void testDbExistFromId() {
		//id inexistant doit renvoyer false
		assertFalse("id inexistant doit renvoyer false", AdresseDAO.dbExistFromId(-1));
		
		//id existant doit renvoyer true
		AdresseDAO obj = dbInsertNewValidObj();
		assertTrue("id existant doit renvoyer true", AdresseDAO.dbExistFromId(obj.getId()));
	}
	
	@Test
	public void testDbUpdate() {
		AdresseDAO obj = newValidObj();
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyé false
		assertFalse("Test d'update sur un enregistrement inexistant", obj.dbUpdate());
		
		AdresseDAO obj2 = dbInsertNewValidObj();
		obj2.setCp("31000");
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyé true
		assertTrue("Test d'update sur un enregistrement existant", obj2.dbUpdate());
		
		//Test si les valeurs en base de donnée correspondent bien à l'update effectué
		AdresseDAO obj3 = AdresseDAO.dbSelectFromId(obj2.getId());
		assertTrue("test que les valeurs en base de donnée correspondent bien à l'update effectué", obj2.hasSameContent(obj3));
	}
	
	@AfterClass
	public static void oneTimeTearDown(){
		for (Integer i : tabIdToDelete){
			AdresseDAO.dbDeleteFromId(i);
			System.out.println("id "+i+" : à bien été supprimé");
		}
		
	}

}
