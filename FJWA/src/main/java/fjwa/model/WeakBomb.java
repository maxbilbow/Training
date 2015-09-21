

package fjwa.model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import fjwa.model.BombInterface;

@Deprecated
public class WeakBomb extends WeakReference<BombInterface> implements BombInterface {
	static ArrayList<BombInterface> safeList  = new ArrayList<>();
	public static Collection<Long> expired = new HashSet<>();
	
	public static BombInterface newInstance(BombInterface bomb) {
		return new WeakBomb(bomb);
	}

	

	public WeakBomb(BombInterface bomb) {
		super(bomb);
		bomb.setWeak(true);
		safeList.add(bomb);
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return get().getId();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return get().getName();
	}

	@Override
	public boolean hasTimeRunOut() {
		if (get() != null && get().hasTimeRunOut()) {
			safeList.remove(get());
		}
		return get() == null || get().hasTimeRunOut();
	}
	
	
	@Override
	public boolean isLive() {
		return get() != null && get().isLive();
	}

	@Override
	public boolean isNull() {
		return get() == null;
	}
	
	@Override
	public long setId(long id) {
		return get().setId(id);
	}

	@Override
	public void setLive(boolean live) {
		get().setLive(live);
	}
	

	@Override
	public void setStartTimeinSeconds(int startTimeInSeconds) {
		get().setStartTimeinSeconds(startTimeInSeconds);
		
	}

	@Override
	public int timeRemaining() {
		// TODO Auto-generated method stub
		return get().timeRemaining();
	}
	
	@Override
	public BombInterface strongReference() {
		return this.get();
	}
	
	@Override
	public String toString() {
		return get().toString();
	}
	
	@Override
	public String getDescription() {
		return this.get().getDescription();
	}

	public static void clearSafe() {
		safeList.clear();
	}


	@Override
	public boolean isWeak() {
		return true;
	}

	@Override
	public BombInterface setWeak(boolean weak) {
		if (weak)
			return this;
		return this.get().setWeak(weak);
	}
}
