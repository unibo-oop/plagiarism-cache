package controller.PowerUp;

/**
 * The Class HighStrategy.
 */
public final class HighStrategy implements Strategy{

	/** The Constant MULTI. */
	private static final double MULTI =5;
	
	/**
	 * Multiply effect.
	 *
	 * @param effect the effect
	 * @return the int
	 */
	@Override
	public int multiplyEffect(int effect) {
		return (int)(effect * MULTI);
	}

}
