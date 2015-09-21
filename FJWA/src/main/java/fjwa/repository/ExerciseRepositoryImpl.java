package fjwa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fjwa.model.Exercise;

@Repository("exerciseRepository")
public class ExerciseRepositoryImpl implements ExerciseRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Exercise save(Exercise entity) {
		em.persist(entity);
		em.flush();
		return entity;
	}

}
