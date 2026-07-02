package model.entities;

import model.Model;
import model.entitiesutil.Enemy;
import model.entitiesutil.EntityConstants;
import model.entitiesutil.EntityDirections;
import model.entitiesutil.bossutil.BossState;
import model.entitiesutil.typeentities.GenericEntity;
import model.physics.EntityCollision.EdgeCollision;
import model.physics.EntityMovementImpl;

/**
 * {@link Enemy} boss with a lot of life but that moves slowly
 */
public class Boss2 extends Enemy{

	private final Model model;
	private BossState state;

	/**
	 * {@link Enemy} boss with a lot of life but that moves slowly
	 * 
	 * @param x is the initial x coordinate
	 * @param y is the initial y coordinate
	 * @param model is the model that contains this {{@link GenericEntity}
	 */
	public Boss2(double x, double y, Model model) {
		this.create(SpecificEntityType.BOSS_2, x, y, EntityConstants.Boss2.INITIAL_WIDTH, 
				EntityConstants.Boss2.INITIAL_HEIGHT, 0, 
				EntityConstants.Boss2.INITIAL_MU_Y, EntityConstants.Boss2.MAX_HITS, EntityDirections.DOWN, 
				new EntityMovementImpl());
		this.state = BossState.NORMAL;
		this.model = model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeDirection() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEntityPosition() {
		this.changeState();
		if(this.state.equals(BossState.UPSET)) {
			this.getMovementMenager().moveDown(this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shoot() {
		for(int i = 2; i > 0; i--) {
			this.model.getNewEntities().add(new MultiDirectionsEnemyBullet(this.getX() +
					(i%2 == 0 ? +1 : -1) * this.getWidth()/4,
					this.getY() + this.getHeight()/2 + EntityConstants.MultiDirectionEnemyBullet.INITIAL_HEIGHT/2,
					SpecificEntityType.BOSS_BULLET));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canShoot(int cycles) {
		int x = EntityConstants.Boss2.CYCLES_TO_SHOOT;
		return (x == 0) ? true : 
			(cycles % x == 0) ? true : false;
	}

	/**
	 * Change the state of the boss after it took too many hits 
	 */
	private void changeState() {
		if(this.getHits() >= EntityConstants.Boss2.HITS_2ND_PHASE && !this.state.equals(BossState.UPSET)) {
			this.state = BossState.UPSET;
		}
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
		if(edge.equals(EdgeCollision.DOWN)) {
			this.model.processGameOver();
		}
	}
}
