package model.entities;

import java.util.Random;

import model.entitiesutil.Bullet;
import model.entitiesutil.EntityConstants;
import model.entitiesutil.EntityDirections;
import model.entitiesutil.GenericEntityType;
import model.entitiesutil.typeentities.GenericEntity;
import model.physics.EntityMovementImpl;

/**
 * {@link Bullet} with multiple direction
 */
public class MultiDirectionsEnemyBullet extends Bullet {

	private Random random;

	/**
	 * {@link Bullet} with multiple direction
	 * 
	 * @param pos		is the {@link Bullet} initial position
	 * @param type		is the type of this {@link Bullet} 
	 */
	public MultiDirectionsEnemyBullet(double x, double y, SpecificEntityType type) {
		this.random = new Random();
		double maxSpeed = EntityConstants.MultiDirectionEnemyBullet.MAX_MU_Y;
		double speedY = (maxSpeed == 0) ? 0 :
			(random.nextInt((int)maxSpeed) + 1);
		this.create(type, x, y, EntityConstants.MultiDirectionEnemyBullet.INITIAL_WIDTH, 
				EntityConstants.MultiDirectionEnemyBullet.INITIAL_HEIGHT, 
				EntityConstants.MultiDirectionEnemyBullet.INITIAL_MU_X,
				speedY, this.setRandomDirection(), new EntityMovementImpl());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEntityPosition() {
		switch(this.getDirection()) {
			case DOWN_LEFT:
				this.getMovementMenager().moveBottomLeft(this);
				break;
			case DOWN_RIGHT:
				this.getMovementMenager().moveBottomRight(this);
				break;
			default:
				this.getMovementMenager().moveDown(this);
				break;
		}
	}

	/**
	 * Selects a possible random direction for this entity and returns it
	 * 
	 * @return a possible random direction for this entity
	 */
	private EntityDirections setRandomDirection() {
		switch(this.random.nextInt(EntityConstants.MultiDirectionEnemyBullet.POSSIBLE_DIRECTIONS)) {
			case 1:
				return EntityDirections.DOWN_LEFT;
			case 2:
				return EntityDirections.DOWN_RIGHT;
			default:
				return EntityDirections.DOWN;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEntity(GenericEntity entity) {
		if(entity.getEntityType().getGenericType().equals(GenericEntityType.PLAYER)) {
			this.setLife();
		}
	}

}
