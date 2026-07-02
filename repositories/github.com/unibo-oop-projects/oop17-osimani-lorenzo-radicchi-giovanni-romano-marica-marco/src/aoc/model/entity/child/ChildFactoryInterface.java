package aoc.model.entity.child;

import aoc.model.entity.EntityInterface;
import aoc.model.level.Level;
import aoc.utilities.Vector;

/**
 * This interface models a child factory.
 */
public interface ChildFactoryInterface {
	
	/**
	 * This method creates a new child, which type is passed as parameter,
	 * and returns his reference.
	 * 
	 * @param type
	 * 			The type of the child that the method will create.
	 * @param position
	 * 			The initial position where the child will be created.
	 * 
	 * @return the reference to the new child.
	 */
	public EntityInterface spawnChild(Children type, Vector position, Level level);

}
