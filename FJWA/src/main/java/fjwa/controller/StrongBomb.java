package fjwa.controller;
import java.time.Duration;
import java.time.Instant;

import org.hibernate.validator.constraints.Range;

import fjwa.model.Bomb;

public class StrongBomb implements Bomb {
	private boolean live = true;

	@Range(min = 1)
	private int timeinSeconds;

	private String name;
	private static int count = 0;
	
	private final Instant startTime;
	public StrongBomb() {
		this.timeinSeconds = (int) (Math.random() * 20 + 5);
		this.name = "B" + ++count;
		this.startTime = Instant.now();
	}
	
	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#isLive()
	 */
	@Override
	public boolean isLive() {
		return live;
	}

	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#setLive(boolean)
	 */
	@Override
	public void setLive(boolean live) {
		this.live = live;
	}

	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#getTimeinSeconds()
	 */
	@Override
	public int getTimeinSeconds() {
		return timeinSeconds;
	}

	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#setTimeinSeconds(int)
	 */
	@Override
	public void setTimeinSeconds(int timeinSeconds) {
		this.timeinSeconds = timeinSeconds;
	}

	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#hasTimeRunOut()
	 */
	@Override
	public boolean hasTimeRunOut() {
		Instant now = Instant.now();
		
		Duration dt = Duration.between(this.startTime, now);
		this.timeinSeconds -= dt.getSeconds();
	
		return this.timeinSeconds < 0;
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println(getName() + " Was DELETED by the GC!");
		super.finalize();
	}
	
	public Bomb get() {
		return this;
	}
	
	public boolean isNull() {
		return false;
	}


}