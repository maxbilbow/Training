

package fjwa.model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import fjwa.model.StrongBomb;
import fjwa.model.Bomb;


public class WeakBomb extends WeakReference<StrongBomb> implements Bomb {
	static ArrayList<StrongBomb> safeList  = new ArrayList<>();
	public static Bomb newInstance() {
		return new WeakBomb();
	}

	public WeakBomb() {
		super(new StrongBomb());
		safeList.add(get());
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
}
