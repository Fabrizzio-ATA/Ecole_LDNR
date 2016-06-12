package packageTEST;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;

import packageDAO.Role;
import packageORM.UtilisateurORM;

public class TestUtilisateurORM {

	static ArrayList <UtilisateurORM> tabUtilisateurs = new ArrayList <UtilisateurORM>();

	static UtilisateurORM objORM_1;
	static UtilisateurORM objORM_2;
	
	@Test
	public void testCreate() {
		System.out.println("# Test Create:");
		try {
			objORM_1 = UtilisateurORM.create("test1", "pwd1", Role.ENSEIGNANT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("# KO Ne peut créer un utilisateur");
		}
		System.out.println("# OK Utilisateur créé id:"+objORM_1.getId());
	}

	@Test
	public void testReadId() {
		System.out.println("# Test Read: ");
		try {
			objORM_2 = UtilisateurORM.read(objORM_1.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("# KO Ne peut lire l'utilisateur id:"+objORM_1.getId());
		}
		System.out.println("# OK Lire Utilisateur id:"+
									objORM_2.getId()+"\n"+
						"login:"+	objORM_2.getLogin()+"\n"+
						"password:"+objORM_2.getPassword()+"\n"+
						"role:"+	objORM_2.getRole().toString());
	}

	@Test
	public void testReadAll() {
		System.out.println("# Test Read: ");
		ArrayList <UtilisateurORM> tabORM = null;
		try {
			tabORM = UtilisateurORM.read();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("# KO Ne peut lire les utilisateurs");
		}
		
		System.out.println("Liste Utilisateurs:\n");
		for (UtilisateurORM objORM : tabORM)
		{
			System.out.println("# Utilisateur:"+
							objORM.getId()+"\n"+
				"login:"+	objORM.getLogin()+"\n"+
				"password:"+objORM.getPassword()+"\n"+
				"role:"+	objORM.getRole().toString());
		}
	}

	@Test
	public void testUpdate() {
		System.out.println("# Test Update:");

		try {
			UtilisateurORM.update(objORM_1.getId(),"upd_login","upd_password",Role.DIRECTEUR);
			objORM_2 = UtilisateurORM.read(objORM_1.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("# KO Ne peut pas mettre à jour l'utilisateur id:"+objORM_1.getId());
		}
		// Pas de assert possible sans la méthode hasSameContent
		
		System.out.println("# OK Update Utilisateur id:"+
									objORM_2.getId()+"\n"+
						"login:"+	objORM_2.getLogin()+"\n"+
						"password:"+objORM_2.getPassword()+"\n"+
						"role:"+	objORM_2.getRole().toString());
	}

//	@Test
//	public void testDelete() {
//
//	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("# Test Delete:");

		Integer id;
		try {
			id = objORM_1.getId();
			UtilisateurORM.delete(id);
			System.out.println("# OK Utilisateur supprimé id:"+id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("# KO Ne peut supprimerl'utilisateur id:"+objORM_1.getId());
		}
	}

}
