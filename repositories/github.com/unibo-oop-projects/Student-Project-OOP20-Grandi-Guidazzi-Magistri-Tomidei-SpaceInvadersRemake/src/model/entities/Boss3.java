package model.entities;

import java.util.Random;

import model.Model;
import model.entitiesutil.Enemy;
import model.entitiesutil.EntityConstants;
import model.entitiesutil.EntityDirections;
import model.entitiesutil.bossutil.BossState;
import model.entitiesutil.typeentities.GenericEntity;
import model.physics.EntityCollision.EdgeCollision;
import model.physics.EntityMovementImpl;

/**
 * {@link Enemy} boss that after taking a certain number of hits can teleport and move quickly
 */
public class Boss3 extends Enemy{

	private final Model model;
	private boolean teleport;
	private BossState state;
	private Random random;

	/**
	 * {@link Enemy} boss that after taking a certain number of hits can teleport and move quickly
	 * 
	 * @param x is the initial x coordinate
	 * @param y is the initial y coordinate
	 * @param model is the model that contains this {{@link GenericEntity}
	 */
	public Boss3(double x, double y, Model model) {
		super.create(SpecificEntityType.BOSS_3, x, y, EntityConstants.Boss3.INITIAL_WIDTH, 
				EntityConstants.Boss3.INITIAL_HEIGHT, EntityConstants.Boss3.INITIAL_MU_X,
				EntityConstants.Boss3.INITIAL_MU_Y, EntityConstants.Boss3.MAX_HITS, EntityDirections.LEFT, 
				new EntityMovementImpl());
		this.teleport = true;
		this.state = BossState.NORMAL;
		this.random = new Random();
		this.model = model;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEntityPosition() {
		this.changeState();
		if(this.getDirection().equals(EntityDirections.LEFT)) {
			this.getMovementMenager().moveLeft(this);
		}
		else {
			this.getMovementMenager().moveRight(this);
		}
		this.teleport();
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
		if(this.state.equals(BossState.UPSET)) {
			this.teleport = false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shoot() {
		this.changeState();
		if(this.state.equals(BossState.UPSET)) {
			for(int i = 3; i > 0; i--) {
				this.model.getNewEntities().add(new MultiDirectionsEnemyBullet(this.getX() +
						(i%3 == 0 ? +1 : (i%2 == 0 ? +0 : -1)) * this.getWidth()/4,
						this.getY() + this.getHeight()/2 + EntityConstants.MultiDirectionEnemyBullet.INITIAL_HEIGHT/2,
						SpecificEntityType.BOSS_BULLET));
			}
		}
		else {
			this.model.getNewEntities().add(new MonoDirectionEnemyBullet(this.getX(),
					this.getY() + this.getHeight()/2 + EntityConstants.MonoDirectionEnemyBullet.INITIAL_HEIGHT / 2, 
					SpecificEntityType.BOSS_BULLET));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canShoot(int cycles) {
		int x = this.state.equals(BossState.NORMAL) ? EntityConstants.Boss3.CYCLES_TO_SHOOT_1ST_PHASE
				: EntityConstants.Boss3.CYCLES_TO_SHOOT_2ND_PHASE;
		return (x == 0) ? true : 
			(cycles % x == 0) ? true : false;
	}

	/**
	 * Change the state of the boss after it took too many hits 
	 */
	private void changeState() {
		if(this.getHits() >= EntityConstants.Boss3.HITS_2ND_PHASE && !this.state.equals(BossState.UPSET)) {
			this.state = BossState.UPSET;
			this.setMuX(EntityConstants.Boss3.MAX_SPEED);
		}
	}

	/**
	 * Teleport the boss in a range took from input
	 * 
	 * @param minX is the minimum value of the range
	 * @param maxX is the maximum value of the range
	 */
	private void teleport() {
		double x = 0;
		if(this.state.equals(BossState.UPSET) && 
				this.getHits() % (this.getMaxHits()/2 - 1) == 0 && !this.teleport) {
			do {
				x = this.random.nextInt((int)(this.model.getMaxWorldWidth() - this.getMuX()));
			}while(x < this.model.getMinWorldWidth());

			this.setX(x);
			this.teleport = true;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEntity(GenericEntity entity) {
		if(entity.getEntityType().equals(SpecificEntityType.PLAYER_1_BULLET)) {
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
