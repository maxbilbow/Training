package fjwa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fjwa.model.Goal;

@Repository("goalRepository")
public class GoalRepositoryImpl implements GoalRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Goal save(Goal goal) {

		em.persist(goal);
		
		return goal;
	}

}
