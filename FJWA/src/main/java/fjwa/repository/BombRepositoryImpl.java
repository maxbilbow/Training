package fjwa.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.UnknownEntityTypeException;
import org.springframework.stereotype.Repository;

import click.rmx.Tests;
import fjwa.RMXError;
import fjwa.RMXException;
import fjwa.model.Bomb;

@Repository("bombRepository")
public class BombRepositoryImpl implements BombRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Bomb save(Bomb bomb) throws RMXException {
		try {
			Tests.note("BombRepositoryImpl::save Bomb::timeRemaining == " + bomb.timeRemaining());
			em.persist(bomb);
			em.flush();
		} catch (HibernateException e) {
			Tests.note("ERROR: " + e.getMessage());
			throw RMXException.expected(e);
		} catch (Exception e) {
			throw RMXException.unexpected(e);
		}
		return bomb;
	}

	@Override
	public Bomb remove(Bomb entity) throws RMXException {	
		if (entity != null) {
			try {
				em.remove(em.contains(entity) ? entity : em.merge(entity));
				em.flush();
			} catch (IllegalArgumentException | PersistenceException e) {
				Tests.note("ERROR: " + e.getMessage());
				throw RMXException.expected(e);
			} catch (Exception e) {
				throw RMXException.unexpected(e);
			} finally {
				if (entity == null)
					throw RMXException.newInstance("Entity is potentually NULL after remiving from DB.", RMXError.Unexpected);
			}
		}
		return entity;
	}


	@Override
	public Bomb getBomb(long id) throws RMXException {
		Bomb expired = null;
		try {
			em.find(Bomb.class, id);
			remove(expired);
		} catch (UnknownEntityTypeException | IllegalArgumentException e) {
			Tests.note("ERROR: " + e.getMessage());
			throw RMXException.expected(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw RMXException.unexpected(e);
		}
		return expired;
	}

	@Override
	public Collection<Bomb> getBombList(Collection<Bomb> entities) throws RMXException {
		Query query = em.createQuery("Select b from Bomb b");
		List<Bomb> qList = query.getResultList();
		this.synchronize(entities);
		entities.clear();
		for (Bomb bomb : qList) {
			entities.add(bomb);
		}
		return entities;
		//throw RMXException.newInstance(RMXError.MethodNotImplementedException);
	}

	@Override
	public Collection<Bomb> synchronize(Collection<Bomb> entities) throws RMXException {
//		List<Bomb> newList = new ArrayList<>();
		for (Bomb bomb : entities) {
//			this.save(bomb);
//			if (em.contains(bomb)) {
				em.merge(bomb);
				em.flush();
//			}
//			this.save(bomb);
		}
		return entities;
	}

}
