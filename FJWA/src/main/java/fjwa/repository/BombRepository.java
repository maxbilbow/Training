package fjwa.repository;

import java.util.Collection;
import java.util.List;

import fjwa.RMXException;
import fjwa.model.Bomb;

public interface BombRepository {
	
	Bomb save(Bomb bomb) throws RMXException;

	Bomb remove(Bomb bomb)  throws RMXException;

	Bomb getBomb(long id) throws RMXException;

	Collection<Bomb> synchronize(Collection<Bomb> entities) throws RMXException;

	Collection<Bomb> getBombList(Collection<Bomb> entities) throws RMXException;

	
}
