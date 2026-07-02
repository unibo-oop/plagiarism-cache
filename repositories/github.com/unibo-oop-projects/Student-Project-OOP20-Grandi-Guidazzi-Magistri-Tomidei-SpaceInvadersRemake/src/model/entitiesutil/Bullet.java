package model.entitiesutil;

import model.physics.EntityMovement;
import model.entities.SpecificEntityType;
import model.entitiesutil.typeentities.AutoMovableEntity;
import model.physics.EntityCollision.EdgeCollision;
import util.Pair;

/**
 * Object which is throw
 */
public abstract class Bullet implements AutoMovableEntity {

	private Pair<Double, Double> pos;
	private double  muX, muY;
	private int width, height;
	private boolean life;
	private EntityMovement move;
	private EntityDirections direction;
	private SpecificEntityType entityType;

	/**
	 * Create a new {@link Bullet}
	 * 
	 * @param type		is the type of the {@link Bullet}
	 * @param pos		is the initial position of the {@link Bullet}
	 * @param width		is the initial width of the {@link Bullet}
	 * @param height	is the initial height of the {@link Bullet}
	 * @param muX		is the initial movement unit of the {@link Bullet} along x-axis 
	 * @param muY		is the initial movement unit of the {@link Bullet} along y-axis 
	 * @param dir		is the initial direction of the {@link Bullet}
	 * @param move		is {@link EntityMovement} implementation
	 */
	protected void create(SpecificEntityType type, double x, double y, int width,int height, 
			double muX, double muY, EntityDirections dir, 
			EntityMovement move) {
		this.width = width;
		this.height = height;
		this.pos = new Pair<>(x, y);
		this.muX = muX;
		this.muY = muY;
		this.move = move;
		this.direction = dir;
		this.life = true;
		this.entityType = type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pair<Double, Double> getPos() {
		return this.pos;
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
		return this.muX;
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
		return this.muY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMuY(double muy) {
		this.muY = muy;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAlive() {
		return this.life;
	}

	/**
	 * Set the life of the {@link Bullet} to false
	 */
	public void setLife() {
		this.life = false;
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
	 * {@inheritDoc}
	 */
	@Override
	public void setDirection(EntityDirections dir) {
		this.direction = dir;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEdge(EdgeCollision edge) {
		this.setLife();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void updateEntityPosition();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SpecificEntityType getEntityType() {
		return this.entityType;
	}
}
