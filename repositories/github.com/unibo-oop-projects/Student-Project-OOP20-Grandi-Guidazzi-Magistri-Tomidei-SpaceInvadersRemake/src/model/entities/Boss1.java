package model.entities;

import model.Model;
import model.entitiesutil.Enemy;
import model.entitiesutil.EntityConstants;
import model.entitiesutil.EntityDirections;
import model.entitiesutil.typeentities.GenericEntity;
import model.physics.EntityCollision.EdgeCollision;
import model.physics.EntityMovementImpl;

/**
 * Generic {@link Enemy} boss
 */
public class Boss1 extends Enemy {

	private final Model model;

	/**
	 * Generic {@link Enemy} boss
	 * 
	 * @param x is the initial x coordinate
	 * @param y is the initial y coordinate
	 * @param model is the model that contains this {{@link GenericEntity}
	 */
	public Boss1(double x, double y, Model model) {
		this.create(SpecificEntityType.BOSS_1, x, y, EntityConstants.Boss1.INITIAL_WIDTH, 
				EntityConstants.Boss1.INITIAL_HEIGHT, EntityConstants.Boss1.INITIAL_MU_X, 
				EntityConstants.Boss1.INITIAL_MU_Y, EntityConstants.Boss1.MAX_HITS, EntityDirections.RIGHT,
				new EntityMovementImpl());
		this.model = model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeDirection() {
		if(this.getDirection().equals(EntityDirections.LEFT)) {
			this.setDirection(EntityDirections.RIGHT);
		}
		else {
			this.setDirection(EntityDirections.LEFT);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEntityPosition() {
		if(this.getDirection().equals(EntityDirections.LEFT)){
			this.getMovementMenager().moveLeft(this);
		}
		else {
			this.getMovementMenager().moveRight(this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shoot() {
		this.model.getNewEntities().add(new MonoDirectionEnemyBullet(this.getX(),
				this.getY() + this.getHeight()/2 + EntityConstants.MonoDirectionEnemyBullet.INITIAL_HEIGHT/2,
				SpecificEntityType.BOSS_BULLET));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canShoot(int cycles) {
		int x = EntityConstants.Boss1.CYCLES_TO_SHOOT;
		return (x == 0) ? true : 
			(cycles % x == 0) ? true : false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEntity(GenericEntity entity) {
		if(entity.getEntityType().equals(SpecificEntityType.PLAYER_1_BULLET) && this.isAlive()) {
			this.incHits();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEdge(EdgeCollision edge) {
		if(edge.equals(EdgeCollision.LEFT) || edge.equals(EdgeCollision.RIGHT)) {
			this.getMovementMenager().moveDown(this);
			this.changeDirection();
		}
		if(edge.equals(EdgeCollision.DOWN)) {
			this.model.processGameOver();
		}
	}
}
