package controller;

import model.Entity;
import model.EntityImpl;
import model.ID;
import utility.Pair;

/**
 * An entity with a time-limited life..
 */
public abstract class ChronometerEntity extends EntityImpl implements Chronometer {


	/** The chronometer. */
	private final Chronometer chronometer;
	
	/**
	 * Instantiates a new chronometer entity.
	 *
	 * @param position the position of this entity
	 * @param veloX the velo X of  entity
	 * @param veloY the velo Y of entity
	 * @param id the id of entity
	 * @param chronometer the chronometer of entity
	 */
	public ChronometerEntity(final Pair<Integer, Integer>position,final int veloX,final int veloY,final ID id, final int chronometer ) {
		super(position,veloX,veloY,id);
		this.chronometer = new ChronometerImpl(chronometer);
	}

	/**
	 * Update.
	 */
	@Override
	public abstract void update();
	
	/**
	 * Collide.
	 *
	 * @param entity the entity
	 */
	@Override
	public abstract void collide(Entity entity);
	
	/**
	 * Tick.
	 */
	@Override
	public void tick() {
		this.chronometer.tick();
	}


	
	/**
	 * Gets the time left.
	 *
	 * @return the time left
	 */
	@Override
	public int getTimeLeft() {
		return chronometer.getTimeLeft();
	}


	/**
	 * Checks if is ended.
	 *
	 * @return true, if is ended
	 */
	@Override
	public boolean isEnded() {
		return chronometer.isEnded();
	}

	
	

}
