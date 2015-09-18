package fjwa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fjwa.model.Bomb;

@Repository("bombRepository")
public class BombRepositoryImpl implements BombRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Bomb save(Bomb bomb) {

		em.persist(bomb);
		
		return bomb;
	}

}
