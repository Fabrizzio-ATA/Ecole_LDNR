package packageMetier;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class CRUD {
	
	static EntityManagerFactory emf;
	private static EntityManager em;
	
	public static EntityManager getManager()
	{
	    // Faire un singleton
	     emf = Persistence.createEntityManagerFactory("TestHibernateMavenJPA");
	    em = emf.createEntityManager();
		return em;
	}
	
	public static void close(){
		
		em.close();
		emf.close();
	}

	public abstract void ajouter();
	public abstract <T> T lire(int id);
	public abstract <T> List<T> lister();
	// Methode Modifier vérifier les arguments génériques <V,T> V: value T: type
	public abstract <V,T> void modifier(T obj);
	public abstract void supprimer(int id);

}
