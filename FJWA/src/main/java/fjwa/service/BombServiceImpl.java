package fjwa.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fjwa.RMXException;
import fjwa.model.Bomb;
import fjwa.model.Bombs;
import fjwa.repository.BombRepository;


@Service("bombService")
public class BombServiceImpl implements BombService {

	private Collection<Bomb> bombs = new HashSet<Bomb>();

	@Autowired
	private BombRepository bombRepository;

	

	Set<String> seriousErrors = new HashSet<>();
	
	@Override
	@Transactional
	public Collection<Bomb> findAllBombs() throws RMXException {
		try {
			Collection<Bomb> allBombs = bombRepository.getBombList(this.bombs);
			if (allBombs != null)
				this.bombs = allBombs;
			
//			this.bombs.clear();
//
//
//			for (Bomb bomb : allBombs) {
////				bomb.setLive(true);
//				this.bombs.add(bomb);
//			}
//			return this.bombs;
		} catch (RMXException e) {
			this.addError(e);
			e.printStackTrace();
		} catch (Exception e) {
			throw this.addError(RMXException.unexpected(e));
		}
		return this.bombs;
	}



	@Override
	public BombService addBomb(Bomb bomb) {
		bombs.add(bomb);//WeakBomb.newInstance());
		return this;
	}

	@Override
	public BombService addBomb() {
		bombs.add(Bombs.newBomb());
		return this;
	}



	@Override
	public void defuse() {
		for (Bomb bomb : bombs)
			bomb.setLive(false);
	}

	private Collection<Bomb> expiredList = new HashSet<>();

	private String errorLog = "";

	@Override
	@Transactional
	public void cleanUp() throws RMXException {
		try {
			if (expiredList.size() == 0)
				return;
			for (Bomb bomb : expiredList)
				this.remove(bomb);
			expiredList.clear();
			
		} catch (RMXException e) {
			this.addError(e);
			e.printStackTrace();
		} catch (Exception e) {
			throw this.addError(RMXException.unexpected(e));
		} finally {
			System.gc();
		}

	}


	@Override
	public Collection<Bomb> update() {
		for (Bomb bomb: bombs)
			if (bomb.hasTimeRunOut()) 
				expiredList.add(bomb);
		return bombs;
	}

	@Override
	public String getErrors() {
		String log = "ERRORS: " + this.errorLog;
		for (String error : seriousErrors) {
			log += "<br/>" + error;
		}
		this.errorLog = "";
		return log;//.replace("\n", "<br/>");
	}

	@Override
	@Transactional
	public Bomb save(Bomb bomb) {
		try {
			this.bombs.add(bomb);
			return bombRepository.save(bomb);
		} catch (RMXException e) {
			this.addError(e);
		} catch (Exception e) {
			this.addError(RMXException.unexpected(e));
		}
		return bomb;
	}

//	@Override
//	@Transactional
//	public Bomb save(){
//		int lastBomb = bombs.size() - 1;
//		if (lastBomb < 0)
//			return null;
//		else
//			return bombs.
//	}


	
	@Override
	@Transactional
	public boolean remove(Bomb bomb) throws RMXException {
		Bomb expired = bomb; boolean success = false;
		try { 
			expired = bombRepository.remove(expired);
			success = true;
		} catch (RMXException e) {
			this.addError(e);
		} finally {
			if (expired != null && bombs.contains(expired))
				bombs.remove(expired); 
		}
		return success;
	}

//	@Override
//	@Transactional
	@Deprecated
	public Bomb remove(final long id) throws RMXException {
		try {
			Object[] arr = bombs.stream().filter(new Predicate<Bomb>(){
				@Override
				public boolean test(Bomb b) {
					return b != null && b.getId() == id;
				}
			}).toArray();

			Bomb expired;
			if (arr.length == 0) {
				expired = bombRepository.getBomb(id);
			} else {
				expired = (Bomb) arr[0];
				bombs.remove(expired);
			}
			this.remove(expired);
			return expired;
		} catch (RMXException e) {
			this.addError(e);
			e.printStackTrace();
		} catch (Exception e) {
			this.addError(RMXException.unexpected(e));
		} 
		return null;
	}

	@Override
	public RMXException addError(RMXException e) {
		if (e.isSerious())
			this.seriousErrors.add(e.html());
		else 
			this.errorLog += "<br/>" + e.html();
		return e;
	}



	@Override
	@Transactional
	public Collection<Bomb> synchronize() {
		try {
			return this.bombs = bombRepository.synchronize(this.bombs);
		} catch (RMXException e) {
			this.addError(e);
			e.printStackTrace();
		} catch (Exception e) {
			this.addError(RMXException.unexpected(e));
		} 
		return null;
	}


}
