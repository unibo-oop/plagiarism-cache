package tq2.interfaces.platform;

import tq2.interfaces.Entity;

/**
 * The Interface Active is to be used in for Entities that can be subject to gravity
 * and react when colliding with other Entities.
 * 
 * @author Francesco Gori
 */
public interface Active extends Entity, Fall {
	/**
	 * Tests whether the Entity collides with another Entity on the same layer.
	 */
	abstract void testCollision();	
}
