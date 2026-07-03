package tq2.interfaces.platform;

import tq2.interfaces.Entity;

/**
 * The Interface CollisionAdvanced is meant for Entities that can react differently to collision depending on the side they collided, or react
 * when another Entity gets close to them.
 * 
 * @author Francesco Gori
 */
public interface CollisionAdvanced {

	/**
	 * Makes the Entity react to the collision from the specified Entity.
	 *
	 * @param en the Entity
	 */
	abstract void collideRight(Entity en);
	
	/**
	 * Makes the Entity react to the collision from the specified Entity.
	 *
	 * @param en the Entity
	 */
	abstract void collideLeft(Entity en);
	
	/**
	 * Makes the Entity react to the collision from the specified Entity.
	 *
	 * @param en the Entity
	 */
	abstract void collideTop(Entity en);
	
	/**
	 * Makes the Entity react to the collision from the bottom with the specified Entity.
	 *
	 * @param en the Entity
	 */
	abstract void collideBottom(Entity en);
	
	/**
	 * Checks whether the Entity collides with other Entities.
	 */
	abstract void testCollision();
	
	/**
	 * Makes the Entity react when the specified Entity gets close.
	 * 
	 * @param en the Entity that entered the field of view
	 */
	abstract void isCloseTo(Entity en);
	
}
