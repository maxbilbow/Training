package fjwa.model;

public interface Bomb {

	/**
	 * @return the live
	 */
	boolean isLive();

	/**
	 * @param live the live to set
	 */
	void setLive(boolean live);

	/**
	 * @return the timeinSeconds
	 */
	int getTimeInSeconds();

	/**
	 * @param timeinSeconds the timeinSeconds to set
	 */
	void setTimeinSeconds(int timeinSeconds);

	String getName();

	boolean hasTimeRunOut();

	boolean isNull();

	
}