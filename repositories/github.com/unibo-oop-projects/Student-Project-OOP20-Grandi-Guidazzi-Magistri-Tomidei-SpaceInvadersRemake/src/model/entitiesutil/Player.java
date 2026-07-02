package model.entitiesutil;

import model.entities.SpecificEntityType;
import model.entitiesutil.typeentities.UserEntity;
import util.Pair;

/**
 * A class that create the player entity.
 */
public abstract class Player implements UserEntity{

	private SpecificEntityType entityType;
	private Pair<Double,Double> position;
	private double movimentUnitX;
	private double movimentUnitY;
	private int height;
	private int width;
	private int hit;
	private int maxHits;
	
	protected void create(SpecificEntityType type, double x, double y, int width,int height, 
			double muX, double muY, int maxHits) {
		this.width = width;
		this.height = height;
		this.position = new Pair<>(x, y);
		this.movimentUnitX = muX;
		this.movimentUnitY = muY;
		this.hit = 0;
		this.maxHits = maxHits;
		this.entityType = type;
	}

	@Override
	public Pair<Double, Double> getPos() {
		return this.position;
	}

	@Override
	public void setPos(double x, double y) {
		this.position.setBoth(x, y);
	}

	@Override
	public double getX() {
		return this.position.getX();
	}

	@Override
	public double getY() {
		return this.position.getY();
	}

	@Override
	public void setX(double x) {
		this.position.setX(x);		
	}

	@Override
	public void setY(double y) {
		this.position.setY(y);
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public boolean isAlive() {
		return this.hit < this.maxHits;
	}

	protected void incHits() {
		this.hit++;
	}

	protected int getHits() {
		return this.hit;
	}

	@Override
	public double getMuX() {
		return this.movimentUnitX;
	}

	@Override
	public void setMuX(double mux) {
		this.movimentUnitX = mux;
	}

	@Override
	public double getMuY() {
		return this.movimentUnitY;
	}

	@Override
	public void setMuY(double muy) {
		this.movimentUnitY = muy;
	}

	@Override
	public SpecificEntityType getEntityType() {
		return this.entityType;
	}

	public abstract void shoot();
}
	
