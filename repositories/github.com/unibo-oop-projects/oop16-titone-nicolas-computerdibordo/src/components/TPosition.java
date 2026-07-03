package components;

/**
 * This enumeration represents the possible position to be used with a layout manager
 *
 */
public enum TPosition {
	TOP("top"), BOT("bot"), MID("mid"), RIGHT("right"), LEFT("left");
	
	/**
	 * A string representation of this position
	 */
	private final String s;
	
	private TPosition(String s) {
		this.s = s;
	}
	
	/**
	 * Get a String representation of this position
	 * @return a String
	 */
	public String getStringValue() {
		return this.s;
	}
}