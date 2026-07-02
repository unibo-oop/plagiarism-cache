package model;

import java.awt.Rectangle;

import utility.Pair;

/**
 * A class describing the basic behavior of every entity in the game.
 */
public abstract class EntityImpl implements Entity {
	
	/** The id. */
	private final ID id;
	
	/** The hitbox. */
	private Rectangle hitbox; 
	
	/** The position. */
	private Pair<Integer, Integer> position;
	
	/** The dead. */
	private boolean dead;
	
	/** The speed. */
	private Pair<Integer, Integer> speed;
	
	
	/**
	 * Instantiates a new entity impl.
	 *
	 * @param position the position
	 * @param speedX the speed X
	 * @param speedY the speed Y
	 * @param id the id
	 */
	public EntityImpl(final Pair<Integer,Integer> position, final int speedX, final int speedY, final ID id) {
		this.position = position;
		this.id = id;
		this.hitbox = null;
		this.speed = new Pair<>(speedX, speedY);
	}
	
	/**
	 * Instantiates a new entity impl.
	 *
	 * @param id the id
	 */
	public EntityImpl(final ID id) {
		this(null, 0, 0, id);
	}

	/**
	 * Update.
	 */
	@Override
	public abstract void update(); 
		// TODO Auto-generated method stub
		

	/**
		 * Collide.
		 *
		 * @param entity the entity
		 */
		@Override
	public abstract void collide(Entity entity);
		// TODO Auto-generated method stub
		
	

	/**
		 * Gets the hitbox.
		 *
		 * @return the hitbox
		 */
		@Override
	public Rectangle getHitbox() {
		// TODO Auto-generated method stub
		return this.hitbox;
	}

	/**
	 * Checks if is dead.
	 *
	 * @return true, if is dead
	 */
	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return this.dead;
	}

	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	@Override
	public Pair<Integer, Integer> getSpeed() {
		// TODO Auto-generated method stub
		return this.speed;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	@Override
	public Pair<Integer, Integer> getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
	public final ID getID() {
		// TODO Auto-generated method stub
		return this.id;
	}

	/**
	 * Sets the hitbox.
	 *
	 * @param hitbox the new hitbox
	 */
	@Override
	public void setHitbox(final Rectangle hitbox) {
		this.hitbox = hitbox;
		// TODO Auto-generated method stub
		
	}

	/**
	 * Sets the dead.
	 */
	@Override
	public void setDead() {
		this.dead = true;
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Sets the alive.
	 */
	public void setAlive() {
		this.dead = false;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the position
	 */
	@Override
	public final void setPosition(final Pair<Integer, Integer> position) {
		this.position = position;
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Sets the speed.
	 *
	 * @param speedX the speed X
	 * @param speedY the speed Y
	 */
	public void setSpeed(final int speedX, final int speedY) {
		this.speed = new Pair<Integer, Integer>(speedX, speedY);
	}
	
}
