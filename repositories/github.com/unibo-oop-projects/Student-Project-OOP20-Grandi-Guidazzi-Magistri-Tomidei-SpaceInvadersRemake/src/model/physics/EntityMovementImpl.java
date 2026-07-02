package model.physics;

import model.entitiesutil.typeentities.MobileEntity;

/**
 * Class that implements {@link MobileEntity}'s movements
 */
public class EntityMovementImpl implements EntityMovement{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveLeft(MobileEntity e) {
		e.setX(e.getX() - e.getMuX());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveRight(MobileEntity e) {
		e.setX(e.getX() + e.getMuX());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveUp(MobileEntity e) {
		e.setY(e.getY() - e.getMuY());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveDown(MobileEntity e) {
		e.setY(e.getY() + e.getMuY());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveBottomLeft(MobileEntity e) {
		e.setY(e.getY() + e.getMuY());
		e.setX(e.getX() - e.getMuX());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveBottomRight(MobileEntity e) {
		e.setY(e.getY() + e.getMuY());
		e.setX(e.getX() + e.getMuX());		
	}

}
