package fjwa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Range;

//@Entity
public interface Bomb {

//	@Id
//	@GeneratedValue
	long getId();

	String getName();

	/**
	 * @return the timeinSeconds
	 */
	int timeRemaining();

	boolean hasTimeRunOut();

	/**
	 * @return the live
	 */
	boolean isLive();

	boolean isNull();
	
	long setId(long id);
	
	/**
	 * @param live the live to set
	 */
	void setLive(boolean live);

	/**
	 * sets a timestamp as well
	 * @param setStartTimeinSeconds the setStartTimeinSeconds to set
	 */
	void setStartTimeinSeconds(int startTimeInSeconds);

	
}