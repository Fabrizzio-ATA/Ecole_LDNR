package packageTEST;

import static org.junit.Assert.*;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;

import packageDAO.AdresseDAO;
import packageORM.AdresseORM;

public class TestAdresseORM {

	static List<Integer> tabIdToDelete = new ArrayList<Integer>();
	
	/**
	 * 
	 * @retourne un objet valide
	 */
	/*
	@SuppressWarnings("unused")
	private static AdresseORM newValidObj(){
		
		AdresseORM objORM = null;
		try{
			objORM = AdresseORM.create("15 rue de la poupée qui tousse","GAILLAC", "81500", "0566676869");
		} catch(Exception e) {
			fail("Objet conforme non construit");
		}
		return objORM;
	} */
	
	@Test
	public void testDbInsert() {
		
		
		// AdresseORM objORM = newValidObj();
		AdresseORM objORM = AdresseORM.create("1 Rue des capucines","ALBI", "81000", "0566676869");

		//test de l'insertion
		System.out.println(objORM.getIdAdresse() + "  " + objORM.getVoie() + "  " + objORM.getVille() );
		
		int id = objORM.getIdAdresse();
	
		tabIdToDelete.add(id);
		System.out.println("id "+id+" : a été créé lors du test DbInsert");
		
	}
	
	
	
	@Test
	public void testRead() {
		
		AdresseORM objORM = AdresseORM.create("2 Chemin des lilas","BRIATEXTE", "81100", "0566676869");
		tabIdToDelete.add(objORM.getIdAdresse());
		System.out.println("id " + objORM.getIdAdresse() + " a été créé dans la Lecture " + objORM.getVoie() + "  " + 
                													objORM.getVille() + "  " + objORM.getCp() );
		
		Integer indice = objORM.getIdAdresse();
		
		AdresseORM objORM2 = AdresseORM.read(indice);
		
		System.out.println("L'id a été lu : " + objORM2.getIdAdresse() + "  : " + objORM2.getVoie() + "  " + 
		                                        objORM2.getVille() + "  " + objORM2.getCp() );
			
	}
	
	@Test
	public void testDelete() {
		
		
		Integer indice = 114;
		if (AdresseORM.delete(indice) ) {
			System.out.println("Suppression effectuée : " + indice);
		}
		else {
			System.out.println("Impossible d'effectuer la suppression : " + indice);
		}
		
				
	}

	@Test
	public void testModifie() {
		
		// on a un enregistrement, avec son id
		AdresseORM objORM = AdresseORM.create("3 Avenue des chats","GAILLAC", "81500", "0566676869");
		Integer indice = objORM.getIdAdresse();
		tabIdToDelete.add(indice);
		
		System.out.println("id " + objORM.getIdAdresse() + " a été créé dans la Modification " + objORM.getVoie() + "  " + 
											objORM.getVille() + "  " + objORM.getCp() );

		
		//*** A partir de cet Id, on va modifier la BDD
		//	
		// 2. Modification de la ville et du code postal (Gaillac devient Lavaur et 81500 devient 81300
		
		AdresseORM objetORM2 = null;
		try {
			objetORM2 = AdresseORM.update( objORM.getIdAdresse() , "Chemin rural" , "LAVAUR" , "81300" , "66778899");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (null != objetORM2)
		{
			
			System.out.println("id " + objetORM2.getIdAdresse() + " Modification OK   " + objetORM2.getVoie() + "  " + 
					objetORM2.getVille() + "  " + objetORM2.getCp() );
		}
		else {
			System.out.println("Problème de mise à jour de la BDD ");
		}
		
	}
	
	@AfterClass
	public static void SuppressionFinale(){
		for (Integer i : tabIdToDelete){
			if (AdresseORM.delete(i) ) {
				System.out.println("id "+i+" : a bien été supprimé");
			}
			else {
				System.out.println("id "+i+" : n'a pas été supprimé");
			}
		}
		
	}
	
}
