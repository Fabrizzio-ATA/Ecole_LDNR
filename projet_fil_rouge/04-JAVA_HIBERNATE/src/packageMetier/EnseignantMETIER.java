package packageMetier;

import java.util.List;

import javax.persistence.EntityManager;

public class EnseignantMETIER extends CRUD {

	// TODO confirm EntityManager creation
	private EntityManager em;
	
	public EnseignantMETIER() {
		super();
		em = this.getEntityManager();
	}

	@Override
	public <T extends CRUD> void ajouter(T obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends CRUD> T lire(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends CRUD> List<T> lister() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends CRUD> void modifier(T obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimer(int id) {
		// TODO Auto-generated method stub

	}

}
