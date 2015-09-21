package fjwa.service;

import java.util.Collection;
import java.util.List;

import fjwa.RMXException;
import fjwa.model.Bomb;

public interface BombService {
	Collection<Bomb> findAllBombs() throws RMXException;
	@Deprecated
	BombService addBomb(Bomb bomb);
	
	@Deprecated
	BombService addBomb();
	void defuse() throws RMXException;
	Collection<Bomb> update();
	void cleanUp() throws RMXException;	
	
	Bomb save(Bomb bomb);
//	Bomb save();
	boolean remove(Bomb bomb) throws RMXException;
	
//	@Deprecated
//	Bomb remove(long id) throws RMXException;
	
	
	String getErrors();
	RMXException addError(RMXException e);
	
	Collection<Bomb> synchronize();
//	void checkForErrors() throws RMXException;
	
}
