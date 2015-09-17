package fjwa.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import fjwa.model.Activity;
import fjwa.model.Bomb;
import fjwa.model.Bomb;
import fjwa.model.WeakBomb;

@Service("bombService")
public class BombServiceImpl implements BombService {

	private static List<Bomb> bombs = new ArrayList<Bomb>();
	public List<Bomb> findAllBombs() {
//		List<Bomb> bombs = Arrays.asList(
//				new Bomb(),
//				new Bomb(),
//				new Bomb(),
//				new Bomb()
//				);
		
		
		
		return bombs;
	}
	
	public void addBomb() {
		bombs.add(WeakBomb.newInstance());
	}

	public void defuse() {
		bombs.clear();
	}

	public void cleanUp() {
		System.gc();
	}
	
	public void update() {
//		int i = 0;
		for (int i = 0; i < bombs.size(); ++i) {
			if (bombs.get(i).hasTimeRunOut()) {
				System.out.println("B" + i + " Expired.");
				if (bombs.get(i).isNull())
					bombs.remove(i--);
			}
		}
	}

}