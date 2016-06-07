package packageTEST;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import packageDAO.Role;
import packageDAO.UtilisateurDAO;

public class TestUtilisateurDAO {

	static int compteur = 0;
	static List<Integer> tabIdToDelete = new ArrayList<Integer>();
	
	/*
	 * Méthode outils
	 */
	/**
	 * 
	 * @return un objet valide
	 */
	private static UtilisateurDAO newValidObj(){
		
		UtilisateurDAO obj = null;
		try{
			obj = new UtilisateurDAO("utilisateur_test", "password_test", Role.ENSEIGNANT);
			compteur++;
		} catch(Exception e) {
			fail("Objet conforme non construit");
		}
		return obj;
	}
	/**
	 * 
	 * @return un objet valide mis en base de données
	 */
	protected static UtilisateurDAO dbInsertNewValidObj(){
		UtilisateurDAO obj = newValidObj();
		if(obj.dbInsert()){
			tabIdToDelete.add(obj.getId());
		}
		System.out.println("id "+obj.getId()+" : à été créé lors de la création de l'objet valide Utilisateur avec insertion");
		return obj;
	}

	@Test
	public void testDbInsert() {
		UtilisateurDAO obj = newValidObj();

		//test de l'insertion
		assertTrue("test insertion", obj.dbInsert());
		
		int id = obj.getId();
		assertTrue("Test résultat d'insertion", id>0);
		
		tabIdToDelete.add(id);
		System.out.println("id "+id+" : à été créé lors du test DbInsert");
	}

	@Test
	public void testDbSelectFromId() {
		UtilisateurDAO obj = dbInsertNewValidObj();
		UtilisateurDAO obj2 = UtilisateurDAO.dbSelectFromId(obj.getId());
		
		System.out.println("id "+obj.getId()+" : à été créé lors du test DbSelectFromId");
		//Vérification de la création d'objet
		assertNotNull("test si le select n'est pas null", obj2);
		
		//L'objet récuperer par le select correspond bien à l'objet inséré
		assertTrue("test du select par id", obj.hasSameContent(obj2));
	}

	@Test
	public void testDbExistFromId() {
		//id inexistant doit renvoyer false
		assertFalse("id inexistant doit renvoyer false", UtilisateurDAO.dbExistFromId(-1));
		
		//id existant doit renvoyer true
		UtilisateurDAO obj = dbInsertNewValidObj();
		assertTrue("id existant doit renvoyer true", UtilisateurDAO.dbExistFromId(obj.getId()));
	}

	@Test
	public void testDbUpdate() {
		UtilisateurDAO obj = newValidObj();
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyer false
		assertFalse("Test d'update sur un enregistrement inexistant", obj.dbUpdate());
		
		UtilisateurDAO obj2 = dbInsertNewValidObj();
		obj2.setRole(Role.DIRECTEUR);
		//Test d'update sur un enregistrement inexistant
		//Doit renvoyer true
		assertTrue("Test d'update sur un enregistrement existant", obj2.dbUpdate());
		
		//Test si les valeurs en base de donnée correspondent bien à l'update effectué
		UtilisateurDAO obj3 = UtilisateurDAO.dbSelectFromId(obj2.getId());
		assertTrue("test que les valeurs en base de donnée correspondent bien à l'update effectué", obj2.hasSameContent(obj3));
	}
	
	@Test
	public void testDbDeleteFromId() {
		//Test suppression sur un enregistrement inexistant
		//Doit renvoyer false
		assertFalse("Test suppression id inexistant", UtilisateurDAO.dbDeleteFromId(-1));
		// Test de suppression cf AfterClass
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {		
		for (Integer i : tabIdToDelete){
			UtilisateurDAO.dbDeleteFromId(i);
			System.out.println("id "+i+" : à bien été supprimé");
		}
	}
}
