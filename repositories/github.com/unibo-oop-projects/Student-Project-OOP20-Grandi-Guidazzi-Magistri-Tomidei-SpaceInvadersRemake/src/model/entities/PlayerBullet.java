package model.entities;

import model.entitiesutil.Bullet;
import model.entitiesutil.EntityConstants;
import model.entitiesutil.EntityDirections;
import model.entitiesutil.GenericEntityType;
import model.entitiesutil.typeentities.GenericEntity;
import model.physics.EntityMovementImpl;

/**
 * A class that generate the entity Bullet for the player.
 */
public class PlayerBullet extends Bullet{
	
	private boolean touchEntity;
	/**
	 * The constructor that create the bullet of the player
	 * @param x		is the initial x coordinate of the {@link Bullet}
	 * @param y		is the initial y coordinate of the {@link Bullet}
	 */
	public PlayerBullet(double x, double y) {
		this.touchEntity = false;
		this.create(SpecificEntityType.PLAYER_1_BULLET, x, y, EntityConstants.PlayerBullet.INITIALWIDTH, 
				EntityConstants.PlayerBullet.INITIALHEIGHT, EntityConstants.PlayerBullet.INITIAL_MU_X, 
				EntityConstants.PlayerBullet.INITIAL_MU_Y, EntityDirections.UP, new EntityMovementImpl());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEntity(GenericEntity entity) {
		if(entity.getEntityType().getGenericType().equals(GenericEntityType.GENERIC_ENEMY) 
				|| entity.getEntityType().getGenericType().equals(GenericEntityType.BOSS)) {
			this.setLife();
			this.touchEntity = true;
		} 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEntityPosition() {
		this.getMovementMenager().moveUp(this);
	}

	/**
	 * Method that returns the action of hit a enemies.
	 * @return 
	 */
	public boolean touchEntity() {
		return this.touchEntity;
	}
}
