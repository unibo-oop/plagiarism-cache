package model.games;

/**
 * @author Stefano Benelli
 * This enum represents the list of all the possible choices used to compose the Secret Code
 */
public enum Choice {
	NONE(0),
	FIRST(1),
	SECOND(2),
	THIRD(3),
	FORTH(4),
	FIFTH(5),
	SIXTH(6),
	SEVENTH(7),
	EIGHTH(8);
	
	private final int value;
	
	private Choice(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
