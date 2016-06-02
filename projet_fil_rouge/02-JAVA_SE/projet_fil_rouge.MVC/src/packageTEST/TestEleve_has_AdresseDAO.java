package packageTEST;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import packageDAO.AdresseDAO;
import packageDAO.EleveDAO;
import packageDAO.Eleve_has_AdresseDAO;

public class TestEleve_has_AdresseDAO {
	
	static List<Integer> tabIdToDelete = new ArrayList<Integer>();
	static List<Integer> tabIdToDeleteEleve = new ArrayList<Integer>();
	static List<Integer> tabIdToDeleteAdresse = new ArrayList<Integer>();
	
	/*
	 * Méthode outils
	 */
	/**
	 * 
	 * @return un objet valide
	 */
	private static Eleve_has_AdresseDAO newValidObj(){
		Eleve_has_AdresseDAO obj = null;
		AdresseDAO adresse = TestAdresseDAO.dbInsertNewValidObj();
		EleveDAO eleve = TestEleveDAO.dbInsertNewValidObj();
		
		tabIdToDeleteEleve.add(eleve.getId());
		tabIdToDeleteAdresse.add(adresse.getId());
		try{
			obj = new Eleve_has_AdresseDAO(eleve.getId(), adresse.getId());
		} catch(Exception e) {
			fail("Objet conforme non construit");
		}
		return obj;
	}
	/**
	 * 
	 * @return un objet valide mis en base de données
	 */
	private static Eleve_has_AdresseDAO dbInsertNewValidObj(){
		Eleve_has_AdresseDAO obj = newValidObj();
		if(obj.dbInsert()){
			tabIdToDelete.add(obj.getId());
		}
		System.out.println("id "+obj.getId()+" : à été creer lors de la création de l'objet valide avec insertion");
		return obj;
	}
	
	@Test
	public void testDbInsert() {
		Eleve_has_AdresseDAO obj = newValidObj();

		//test de l'insertion
		assertTrue("test insertion", obj.dbInsert());
		
		int id = obj.getId();
		assertTrue("Test résultat d'insertion", id>0);
		
		tabIdToDelete.add(id);
		System.out.println("id "+id+" : à été creer lors du test DbInsert");
		
		
	}
	
	@Test
	public void testDbSelectFromId() {
		Eleve_has_AdresseDAO obj = dbInsertNewValidObj();
		Eleve_has_AdresseDAO obj2 = Eleve_has_AdresseDAO.dbSelectFromId(obj.getId());
		
		System.out.println("id "+obj.getId()+" : à été creer lors du test DbSelectFromId");
		//Vérification de la création d'objet
		assertNotNull("test si le select n'est pas null", obj2);
		
		//L'objet récuperer par le select correspond bien à l'objet inséré
		assertTrue("test du select par id", obj.hasSameContent(obj2));
	}
	
	@Test
	public void testDbExistFromId() {
		//id inexistant doit renvoyer false
		assertFalse("id inexistant doit renvoyer false", Eleve_has_AdresseDAO.dbExistFromId(-1));
		
		//id existant doit renvoyer true
		Eleve_has_AdresseDAO obj = dbInsertNewValidObj();
		assertTrue("id existant doit renvoyer true", Eleve_has_AdresseDAO.dbExistFromId(obj.getId()));
	}
	
	@Test
	public void testDbUpdate() {
		Eleve_has_AdresseDAO obj = newValidObj();
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyé false
		assertFalse("Test d'update sur un enregistrement inexistant", obj.dbUpdate());
		
		Eleve_has_AdresseDAO obj2 = dbInsertNewValidObj();
		AdresseDAO adresse = TestAdresseDAO.dbInsertNewValidObj();
		tabIdToDeleteAdresse.add(adresse.getId());
		obj2.setId_Adresse(adresse.getId());
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyé true
		assertTrue("Test d'update sur un enregistrement existant", obj2.dbUpdate());
		
		//Test si les valeurs en base de donnée correspondent bien à l'update effectué
		Eleve_has_AdresseDAO obj3 = Eleve_has_AdresseDAO.dbSelectFromId(obj2.getId());
		assertTrue("test que les valeurs en base de donnée correspondent bien à l'update effectué", obj2.hasSameContent(obj3));
	}
	
	@AfterClass
	public static void oneTimeTearDown(){
		for (Integer i : tabIdToDelete){
			Eleve_has_AdresseDAO.dbDeleteFromId(i);
			System.out.println("id "+i+" : à bien été supprimé");
		}
		
		for (Integer i : tabIdToDeleteEleve){
			EleveDAO.dbDeleteFromId(i);
			System.out.println("id "+i+" : de Eleve à bien été supprimé");
		}
		
		for (Integer i : tabIdToDeleteAdresse){
			EleveDAO.dbDeleteFromId(i);
			System.out.println("id "+i+" : de Adresse à bien été supprimé");
		}
		
	}

}

