package fjwa.model;


import java.lang.ref.WeakReference;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import org.hibernate.validator.constraints.Range;

import fjwa.controller.StrongBomb;

public class WeakBomb extends WeakReference<StrongBomb> implements Bomb {
	static ArrayList<StrongBomb> safeList  = new ArrayList<>();
	public WeakBomb() {
		super(new StrongBomb());
		safeList.add(get());
	}

	@Override
	public boolean isLive() {
		return get() != null && get().isLive();
	}

	@Override
	public void setLive(boolean live) {
		get().setLive(live);
	}

	@Override
	public int getTimeinSeconds() {
		// TODO Auto-generated method stub
		return get().getTimeinSeconds();
	}

	@Override
	public void setTimeinSeconds(int timeinSeconds) {
		get().setTimeinSeconds(timeinSeconds);
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
	public boolean isNull() {
		return get() == null;
	}

	public static Bomb newInstance() {
		// TODO Auto-generated method stub
		return new WeakBomb();
	}
	

}
