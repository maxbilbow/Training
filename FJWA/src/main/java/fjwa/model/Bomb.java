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
public class Bomb {
	
	@Id
	@GeneratedValue
	private long id;
	
	private Instant startTime;

	private String description;
	
	private boolean live = true;
	
	public static Bomb newInstance() {
		return new Bomb();
	}
	
	
	
	@Range(min = 1)
	private int startTimeInSeconds;
	
	
	

	public Bomb() {
		this.setStartTimeInSeconds(
				(int) (Math.random() * 20 + 5)
				);
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println(getName() + " Was DELETED by the GC!");
		super.finalize();
	}

	public String getDescription() {
		this.description = "Bomb " + this.getName() + ": ";
		if (this.hasTimeRunOut())
			description += "<span style=\"color: red;\">BOOM!</span>";
		else if (!live)
			description += "<span style=\"color: green;\">Diffused with " + this.getStartTimeInSeconds() + " remaining.</span>";
		else
			description += this.timeRemaining() + " seconds!";
		return description;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return "B" + this.id;
	}


	public Instant getStartTime() {
		return startTime;
	}


	public int getStartTimeInSeconds() {
		return startTimeInSeconds;
	}

	public boolean hasTimeRunOut() {
		return timeRemaining() < 0;
	}	

	public boolean isLive() {
		return live && !this.hasTimeRunOut();
	}
	
	public long setId(long id) {
		return this.id = id;
	}

	public void setLive(boolean live) {
		this.setStartTimeInSeconds(this.timeRemaining());
//		this.startTime = Instant.now();
		this.live = live;
	}

	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	public void setStartTimeInSeconds(int timeInSeconds) {
		this.startTimeInSeconds = timeInSeconds > 0 ? timeInSeconds : 0;
		this.startTime = Instant.now();
	}


	
	public int timeRemaining() {
		if (!live) {
			this.startTime = Instant.now();
			return this.startTimeInSeconds;
		}
		Instant now = Instant.now();
		Duration dt = Duration.between(this.startTime, now);
		int timeInSeconds = (int) (this.startTimeInSeconds - dt.getSeconds());
		return timeInSeconds;
	}
	
}
