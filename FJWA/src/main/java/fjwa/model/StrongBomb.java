package fjwa.model;
import java.time.Duration;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "bomb_table")
public class StrongBomb implements Bomb {
	
	public static Bomb newInstance() {
		return new StrongBomb();
	}
	
	private boolean live = false;
	
	@Range(min = 1)
	private int startTimeInSeconds;
	
	
	@Id
	@GeneratedValue
	private long id;
	
	private Instant startTime;
	
	public StrongBomb() {
		this.setStartTimeinSeconds(
				(int) (Math.random() * 20 + 5)
				);
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println(getName() + " Was DELETED by the GC!");
		super.finalize();
	}

	public Bomb get() {
		return this;
	}

	@Override
	public long getId() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "B" + this.id;
	}
	
	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#getTimeinSeconds()
	 */
	@Override
	public int timeRemaining() {
		Instant now = Instant.now();
		
		Duration dt = Duration.between(this.startTime, now);
		int timeInSeconds = (int) (this.startTimeInSeconds - dt.getSeconds());
		return timeInSeconds;
	}
	
	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#hasTimeRunOut()
	 */
	@Override
	public boolean hasTimeRunOut() {
		return timeRemaining() < 0;
	}
	
	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#isLive()
	 */
	@Override
	public boolean isLive() {
		return live && !this.hasTimeRunOut();
	}
	
	public boolean isNull() {
		return false;
	}

	@Override
	public long setId(long id) {
		return this.id = id;
	}

	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#setLive(boolean)
	 */
	@Override
	public void setLive(boolean live) {
		this.live = live;
	}


	/* (non-Javadoc)
	 * @see fjwa.model.IBomb#setTimeinSeconds(int)
	 */
	@Override
	public void setStartTimeinSeconds(int timeInSeconds) {
		this.startTimeInSeconds =  timeInSeconds;
		this.live = true;
		this.startTime = Instant.now();
	}
}
