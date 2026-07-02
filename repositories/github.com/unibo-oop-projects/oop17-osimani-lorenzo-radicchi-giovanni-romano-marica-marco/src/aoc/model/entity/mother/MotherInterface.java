package aoc.model.entity.mother;

import aoc.utilities.Direction;
import aoc.utilities.ObservedObject;
import aoc.model.entity.EntityInterface;

/**
 * This interface describes the entity of the mother.
 * She has a method for attack, which means she shots a new slipper,
 * and a method for moving, which requires the direction.
 */
public interface MotherInterface extends EntityInterface, ObservedObject{
	
	/**
     * Returns the reference to the new slipper shot.
     * 
     * @return the slipper shot.
     */
	EntityInterface attack();
	
	/**
     * Orders to the mother to move of one step in the specified direction.
     * 
     * @param direction
     *            the direction of movement.
     */
	void move(Direction direction);
	
}
