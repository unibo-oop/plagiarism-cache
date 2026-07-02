package controller;

/**
 * The Class ChronometerImpl.
 */
public class ChronometerImpl implements Chronometer {

	/** The chronometer. */
	private int chronometer;
	
	/**
	 * Instantiates a new chronometer impl.
	 *
	 * @param chronometer the chronometer
	 */
	public ChronometerImpl(final int chronometer) {
		this.chronometer = chronometer;
	}
	
	/**
	 * Tick.
	 */
	@Override
	public void tick() {
		this.chronometer--;
		
	}

	/**
	 * Checks if is ended.
	 *
	 * @return true, if is ended
	 */
	@Override
	public boolean isEnded() {
		return this.chronometer <= 0;
	}

	/**
	 * Gets the time left.
	 *
	 * @return the time left
	 */
	@Override
	public int getTimeLeft() {
		return this.chronometer;
	}

}
