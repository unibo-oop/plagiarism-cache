package utility;

/**
 * The Class Clamp.
 */
public class Clamp {
	
	/**
	 * Instantiates a new clamp.
	 */
	private Clamp() {
	}
	
	/**
	 * Clamp.
	 *
	 * @param var the var
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public static int clamp(final int var, final int min, final int max) {
		return var < min ? min : (var > max ? max : var);
	}

}
