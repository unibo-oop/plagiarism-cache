package model.entities;

import java.util.Random;

import model.entitiesutil.EntityDirections;
import model.entitiesutil.GenericEntityType;
import model.entitiesutil.typeentities.GenericEntity;
import model.entitiesutil.Bullet;
import model.entitiesutil.EntityConstants;
import model.physics.EntityMovementImpl;

/**
 * {@link Bullet} with a single direction
 */
public class MonoDirectionEnemyBullet extends Bullet {

	/**
	 * {@link Bullet} with a single direction
	 * 
	 * @param pos		is the {@link Bullet} initial position
	 * @param type		is the type of this {@link Bullet}
	 */
	public MonoDirectionEnemyBullet(double x,double y, SpecificEntityType type) {
		Random random = new Random();
		double maxSpeed = EntityConstants.MonoDirectionEnemyBullet.MAX_MU_Y;
		double speedY = (maxSpeed == 0) ? 0 :
			(random.nextInt((int)maxSpeed) + 1);
		this.create(type, x, y, EntityConstants.MonoDirectionEnemyBullet.INITIAL_WIDTH, 
				EntityConstants.MonoDirectionEnemyBullet.INITIAL_HEIGHT, 
				0, speedY, EntityDirections.DOWN, new EntityMovementImpl());
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void updateEntityPosition() {
		this.getMovementMenager().moveDown(this);
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
