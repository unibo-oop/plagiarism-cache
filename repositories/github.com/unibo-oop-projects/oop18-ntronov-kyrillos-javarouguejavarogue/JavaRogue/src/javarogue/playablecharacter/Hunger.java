package javarogue.playablecharacter;
/**
 * interface that models the concept of Hunger:
 * it starts at a maximum value, 
 * then decreases at every action done,
 * and can replenish itself.
 */
public interface Hunger {
	/**
	 * Method that
	 * @return the current level of hunger of the character.
	 */
    int getCurrentHunger();
	/**
	 * Method that decreases the hunger level of the character.
	 */
	void decrease();
	/**
	 * Method that permits the possibility of filling the hunger level.
	 * @param toAdd the amount to be added.
	 */
	void addFood(int toAdd);
	/**
	 * Method that allows to fully refill the Hunger level
	 */
	void refill();
}
