package aoc.model.entity.slipper;

import aoc.model.entity.EntityInterface;
import aoc.utilities.Vector;

/**
 * This interface models a slipper factory.
 */
public interface SlipperFactoryInterface {
	
	/**
	 * This method creates a new slipper, which type is passed as parameter,
	 * and returns his reference.
	 * 
	 * @param type
	 * 			The type of the slipper that the method will create.
	 * @param position
	 * 			The initial position where the slipper will be created.
	 * 
	 * @return the reference to the new slipper
	 */
	public EntityInterface createSlipper(Projectile type, Vector position);

}
