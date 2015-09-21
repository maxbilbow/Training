package fjwa.model;

@Deprecated
public interface BombInterface {

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
	
	BombInterface strongReference();
	
	@Override
	String toString();

	String getDescription();

	@Deprecated
	boolean isWeak();

	@Deprecated
	BombInterface setWeak(boolean weak);
	
}