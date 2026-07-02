package model.physics;

import model.entitiesutil.typeentities.GenericEntity;

/**
 * Interface to check collisions
 */
public interface EntityCollision {

	/**
	 * Possible edges that can collide with {@link GenericEntity}
	 */
	public enum EdgeCollision{

		/**
		 * Left edge
		 */
		LEFT,

		/**
		 * Right edge
		 */
		RIGHT,

		/**
		 * Top edge
		 */
		TOP,

		/**
		 * Bottom edge
		 */
		DOWN
	}

	/**
	 * Check collision between all the entities in the level that is running
	 */
	public void checkCollision();

	/**
	 * Check collision between a specific entity in the level that is running
	 * 
	 * @param e is the {@link GenericEntity} implementation that need to be checked
	 */
	public void checkCollision(GenericEntity e);
	
	/**
	 * Check collision between two specific entities
	 * 
	 * @param e				is one {@link GenericEntity} to check
	 * @param entityLevel	is one  {@link GenericEntity} to check
	 */
	public void collision(GenericEntity e, GenericEntity entityLevel);
	
}
