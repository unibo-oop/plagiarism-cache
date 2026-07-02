package aoc.model.entity.slipper;

import aoc.model.entity.EntityInterface;

/**
 * This interface describes a generic slipper, which is the projectile thrown by the mother.
 * It has a method for hitting another object.
 */
public interface SlipperInterface extends EntityInterface {
	
	/**
	 * This method must be called when the slipper hits a child.
	 * 
	 * @return the damage inflicted.
	 */
	int hit();

}
