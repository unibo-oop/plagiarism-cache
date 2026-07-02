package aoc.model.entity;

/**
 * This interface describes the behavior of an object with life.
 * It has methods to decrease life and for obtaining the remaining life.
 */
public interface WithLife extends EntityInterface {
	
	/**
     * Returns the current life as a percentage of the total life of the object.
     * 
     * @return a number between 0 and 1.
     */
	double getLifeAsPercentage();
	
	/**
     * Damages the object of a certain amount of life.
     * If life goes under zero, the entity dies.
     * 
     * @param amount
     *            the amount of life lost.
     */
	void damaged(int amount);

}
