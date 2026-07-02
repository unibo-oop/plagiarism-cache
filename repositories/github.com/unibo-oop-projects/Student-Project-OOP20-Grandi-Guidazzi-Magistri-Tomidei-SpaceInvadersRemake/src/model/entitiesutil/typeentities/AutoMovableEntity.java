package model.entitiesutil.typeentities;

import model.entitiesutil.EntityDirections;
import model.physics.EntityMovement;

/**
 * Interface that represents all the {@link GenericEntity} that must move automatically
 */
public interface AutoMovableEntity extends MobileEntity {

	/**
	 * Update {@link AutoMovableEntity} position according its direction
	 */
	public void updateEntityPosition();

	/**
	 * Return the current directions of the {@link GenericEntity}
	 * 
	 * @return a value of {@link EntityDirections} which represents the {@link GenericEntity}'s current direction
	 */
	public EntityDirections getDirection();

	/**
	 * Update the direction of the {@link GenericEntity}
	 * 
	 * @param direction is the new direction of the {@link GenericEntity}
	 */
	public void setDirection(EntityDirections direction);

	/**
	 * Return the implementation of {@link EntityMovement} 
	 * 
	 * @return the object which represents the {@link EntityMovement} implementation
	 */
	public EntityMovement getMovementMenager();
}