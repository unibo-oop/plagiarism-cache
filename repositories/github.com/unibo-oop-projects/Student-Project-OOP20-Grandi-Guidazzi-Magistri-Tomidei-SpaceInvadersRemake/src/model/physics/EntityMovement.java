package model.physics;

import model.entitiesutil.typeentities.MobileEntity;

/**
 * Interface that represent {@link MobileEntity}'s movements
 */
public interface EntityMovement {

	/**
	 * Method that move the {@link MobileEntity} to the left
	 * 
	 * @param e	is the {@link MobileEntity} that needs to be moved
	 */
	public void moveLeft(MobileEntity e);

	/**
	 * Method that move the {@link GenericEntity} to the right
	 * 
	 * @param e	is the {@link GenericEntity} that needs to be moved
	 */
	public void moveRight(MobileEntity e);

	/**
	 * Method that move the {@link MobileEntity} up
	 * 
	 * @param e	is the {@link MobileEntity} that needs to be moved
	 */
	public void moveUp(MobileEntity e);

	/**
	 * Method that move the {@link MobileEntity} down
	 * 
	 * @param e	is the {@link MobileEntity} that needs to be moved
	 */
	public void moveDown(MobileEntity e);

	/**
	 * Method that move the {@link MobileEntity} to the bottom left
	 * 
	 * @param e	is the {@link MobileEntity} that needs to be moved
	 */
	public void moveBottomLeft(MobileEntity e);

	/**
	 * Method that move the {@link MobileEntity} to the bottom right
	 * 
	 * @param e	is the {@link MobileEntity} that needs to be moved
	 */
	public void moveBottomRight(MobileEntity e);

}
