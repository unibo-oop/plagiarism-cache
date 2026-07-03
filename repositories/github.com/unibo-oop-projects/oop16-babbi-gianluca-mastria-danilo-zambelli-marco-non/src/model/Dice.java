package model;

/**
 * Dice is the interface that corresponds to the dice of the game.
 *
 */
public interface Dice {
	
	/**
	   *
	   * @return the dice's result.
	   */
	int getValue();
	
	/**
	 * Unlike the other, this one imitates the throwing of a loaded dice
	 * The number 6 has more chances to be extract.
	 * @return the dice's result
	 */
	int getValueThief();
	 
}
