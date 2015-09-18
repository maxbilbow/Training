package fjwa.service;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import fjwa.model.Bomb;
import fjwa.model.WeakBomb;
import fjwa.repository.BombRepository;


@Service("bombService")
public class BombServiceImpl implements BombService {

	private final List<Bomb> bombs;
	
	@Autowired
	private BombRepository bombRepository;
	
	public BombServiceImpl() {
		this.bombs = new ArrayList<Bomb>();
	}
	
	public List<Bomb> findAllBombs() {
		
		return bombs;
	}
	
	public void addBomb(Bomb bomb) {
		bombs.add(bomb);//WeakBomb.newInstance());
	}



	public void defuse() {
		bombs.clear();
	}

	public void cleanUp() {
		System.gc();
	}
	
	public List<Bomb> update() {
		for (int i = 0; i < bombs.size(); ++i) {
			if (bombs.get(i).hasTimeRunOut()) {
//				System.out.println("B" + i + " Expired.");
				if (bombs.get(i).isNull())
					bombs.remove(i--);
			}
		}
		return bombs;
	}

	@Override
	public Bomb save(Bomb bomb) {
		return bombRepository.save(bomb);
	}
	
	

}
