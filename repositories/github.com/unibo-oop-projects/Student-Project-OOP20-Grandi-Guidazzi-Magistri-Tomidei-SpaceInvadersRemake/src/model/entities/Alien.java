package model.entities;

import model.Model;


import model.entitiesutil.Enemy;
import model.entitiesutil.EntityConstants;
import model.entitiesutil.EntityDirections;
import model.entitiesutil.typeentities.GenericEntity;
import model.physics.EntityCollision.EdgeCollision;
import model.physics.EntityMovementImpl;
/**
 * 
 * The class that models the entity alien.
 *
 */
public class Alien extends Enemy{

	private final AlienGroup alienGroup;
	private final Model model;
	
	public Alien(int x, int y, AlienGroup alienGroup, Model model, SpecificEntityType type) {
		this.model = model;
		this.create(type, x, y, EntityConstants.Alien.INITIAL_WIDTH, EntityConstants.Alien.INITIAL_HEIGHT, 
				EntityConstants.Alien.INITIAL_MU_X, EntityConstants.Alien.INITIAL_MU_Y,
				EntityConstants.Alien.MAX_HIT, EntityDirections.LEFT, new EntityMovementImpl());
		this.alienGroup = alienGroup;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void changeDirection() {
		if(this.getDirection().equals(EntityDirections.LEFT)) {
			this.setDirection(EntityDirections.RIGHT);
		}
		else {
			this.setDirection(EntityDirections.LEFT);
		}
		this.getMovementMenager().moveDown(this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEdge(EdgeCollision edge) {
		if(edge.equals(EdgeCollision.LEFT)){ 
			alienGroup.alienGroupDown(EdgeCollision.LEFT);
		} else if(edge.equals(EdgeCollision.RIGHT)) {
			alienGroup.alienGroupDown(EdgeCollision.RIGHT);
		} else if(edge.equals(EdgeCollision.DOWN)) {
			this.model.processGameOver();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEntity(GenericEntity entity) {
		if(this.isAlive() && entity.getEntityType().equals(SpecificEntityType.PLAYER_1_BULLET)) {
			this.incHits();
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
		this.getY() + EntityConstants.MonoDirectionEnemyBullet.INITIAL_WIDTH / 2 + this.getHeight() / 2, SpecificEntityType.ALIEN_BULLET));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canShoot(int cycles) {
		int cycleToShoot = EntityConstants.Alien.CYCLES_TO_SHOOT;
		return (cycleToShoot == 0) ? true : 
			(cycles % cycleToShoot == 0) ? true : false;
	}

}
