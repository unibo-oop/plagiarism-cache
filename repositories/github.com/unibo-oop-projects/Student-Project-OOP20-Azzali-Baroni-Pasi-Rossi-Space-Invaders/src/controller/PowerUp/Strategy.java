package controller.PowerUp;

/**
 * The Interface Strategy to determinate the power of powerUp.
 */
public interface Strategy {

	/**
	 * Multiply effect.
	 *
	 * @param effect the effect
	 * @return the int
	 */
	int multiplyEffect(int effect);
}
