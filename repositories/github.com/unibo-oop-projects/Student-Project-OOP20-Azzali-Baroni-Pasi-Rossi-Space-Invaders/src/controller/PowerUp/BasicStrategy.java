package controller.PowerUp;

/**
 * The Class BasicStrategy.
 */
public final class BasicStrategy implements Strategy {

	/**
	 * Multiply effect.
	 *
	 * @param effect the effect
	 * @return the int
	 */
	@Override
	public int multiplyEffect(final int effect) {
		
		return effect * 2;
	}

}
