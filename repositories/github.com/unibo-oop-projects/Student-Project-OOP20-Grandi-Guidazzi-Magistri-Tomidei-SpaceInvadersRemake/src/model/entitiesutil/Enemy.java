package model.entitiesutil;

import model.entities.SpecificEntityType;
import model.entitiesutil.typeentities.AutoFiringEntity;
import model.entitiesutil.typeentities.AutoMovableEntity;
import model.entitiesutil.typeentities.GenericEntity;
import model.physics.EntityMovement;
import util.Pair;

/**
 * {@link GenericEntity} that Hero should kill
 */
public abstract class Enemy implements AutoMovableEntity, AutoFiringEntity {

	private Pair<Double, Double> pos;
	private double muX, muY;
	private int width, height, hit, maxHits;
	private EntityMovement move;
	private EntityDirections direction;
	private SpecificEntityType entityType;

	/**
	 * Create a new {@link Enemy}
	 * 
	 * @param type		is the type of the {@link Enemy}
	 * @param x			is the initial x coordinate of the {@link Enemy}
	 * @param y			is the initial y coordinate of the {@link Enemy}
	 * @param width		is the initial width of the {@link Enemy}
	 * @param height	is the initial height of the {@link Enemy}
	 * @param muX		is the initial movement unit of the {@link Enemy} along x-axis
	 * @param muY		is the initial movement unit of the {@link Enemy} along y-axis
	 * @param maxHits	is the max number of hits that {@link Enemy} can take before dying
	 * @param dir		is the initial direction of the {@link Enemy}
	 * @param move		is {@link EntityMovement} implementation
	 */
	protected void create(SpecificEntityType type, double x, double y, int width,int height, 
			double muX, double muY, int maxHits, EntityDirections dir, EntityMovement move) {
		this.width = width;
		this.height = height;
		this.pos = new Pair<>(x, y);
		this.muX = muX;
		this.muY = muY;
		this.move = move;
		this.direction = dir;
		this.hit = 0;
		this.maxHits = maxHits;
		this.entityType = type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pair<Double, Double> getPos() {
		return pos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPos(double x, double y) {
		this.pos.setBoth(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getX() {
		return this.pos.getX();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getY() {
		return this.pos.getY();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setX(double x) {
		this.pos.setX(x);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setY(double y) {
		this.pos.setY(y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return this.width;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		return this.height;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMuX() {
		return muX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMuX(double mux) {
		this.muX = mux;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMuY() {
		return muY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMuY(double muy) {
		this.muY = muy;
	}

	/**
	 * Return the current number of hits that {@link Enemy} take
	 * 
	 * @return an integer which represent the current number of hits that {@link Enemy} take
	 */
	public int getHits() {
		return this.hit;
	}

	/**
	 * Return the max number of hits that {@link Enemy} can take
	 * 
	 * @return an integer which represent the max number of hits that {@link Enemy} can take
	 */
	public int getMaxHits() {
		return this.maxHits;
	}

	/**
	 * Increment the current number of hits that {@link Enemy} take
	 */
	public void incHits() {
		this.hit++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityMovement getMovementMenager() {
		return this.move;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityDirections getDirection() {
		return this.direction;
	}


	/**
	 *{@inheritDoc}
	 */
	@Override
	public void setDirection(EntityDirections dir) {
		this.direction = dir;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAlive() {
		return this.hit < this.maxHits;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void updateEntityPosition();

	/**
	 * Invert the direction of the {@link Enemy}
	 */
	protected abstract void changeDirection();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void  shoot();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract boolean canShoot(int cycles); 

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SpecificEntityType getEntityType() {
		return this.entityType;
	}
}

