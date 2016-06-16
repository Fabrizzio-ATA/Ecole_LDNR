package packageMetier;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe abstraite pour définir les méthodes de la couche métier
 * Un type générique est défini afin de s'adapter aux différentes classes qui vont implémenter 
 * les méthodes CRUD 
 * cf http://stackoverflow.com/questions/27427798/overriding-abstract-generic-method-in-java
 * 
 * FIXME:
 * en utilisant les methodes init et destroy de la servlet
 * on peut faire appel au getManager et au close du CRUD
 * 
 * TODO :
 * définir le scope de l'Entity Manager
 * 
 * @author fabrizzio
 *
 */
public abstract class CRUD {
	
	protected static EntityManagerFactory emf = null;
	
	private EntityManager em = null;
	
	// Constructeur de superclasse qui intialise l'entity manager.
	CRUD ()
	{
		this.getEntityManager();
	}
	
	public EntityManager getEntityManager()
	{
	    // TODO :Faire un singleton
		if (null == em)
		{
			if (null == emf)
			{
			    emf = Persistence.createEntityManagerFactory("TestHibernateMavenJPA");
			}
		    em = emf.createEntityManager();
		}
		return em;
	}
	
	public void closeEntityManager(){
		
		em.close();
		emf.close();
	}
	
	/**
	 * 	
	 */
	public abstract <T extends CRUD> void ajouter(T obj);
	public abstract <T extends CRUD> T lire(int id);
	public abstract <T extends CRUD> List<T> lister();
	public abstract <T extends CRUD> void modifier(T obj);
	public abstract void supprimer(int id);

}
