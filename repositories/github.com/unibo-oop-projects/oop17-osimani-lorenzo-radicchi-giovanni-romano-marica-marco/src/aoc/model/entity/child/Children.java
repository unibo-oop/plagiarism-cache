package aoc.model.entity.child;

import aoc.model.WorldConstants;

/**
 * This enumeration lists every type of child in the game, and associates 
 * to each one an initial speed, an initial life and an IsHit strategy. 
 *
 */
public enum Children {

	/**
	 * The dumb child, all his stats are normal.
	 */
	DUMB_CHILD (WorldConstants.NORMAL_SPEED*(-1), 0, WorldConstants.NORMAL_LIFE, ChildFactory.IsHitStrategy.NOTHING_STRATEGY),
	
	/**
	 * The fat child, which has a large amount of life, but less speed.
	 */
	FAT_CHILD (WorldConstants.SLOW_SPEED*(-1), 0, WorldConstants.BIG_LIFE, ChildFactory.IsHitStrategy.NOTHING_STRATEGY),
	
	/**
	 * The nerd child, which has studied a strategy to avoid projectiles
	 * after he had been hit.
	 */
	NERD_CHILD (WorldConstants.NORMAL_SPEED*(-1), 0, WorldConstants.NORMAL_LIFE, ChildFactory.IsHitStrategy.STUDIED_STRATEGY),
	
	/**
	 * The athletic child, a fast enemy with low life.
	 */
	ATHLETIC_CHILD (WorldConstants.GREAT_SPEED*(-1), 0, WorldConstants.LOW_LIFE, ChildFactory.IsHitStrategy.NOTHING_STRATEGY),

	/**
	 * The rich child. He went to good schools 
	 * and always played sports, so he has good qualities.
	 * 
	 */
	RICH_CHILD(WorldConstants.FAST_SPEED*(-1), 0, WorldConstants.LARGE_LIFE, ChildFactory.IsHitStrategy.STUDIED_STRATEGY);
	
	/**
	 * The initial life of the child.
	 */
	private int life;
	
	/**
	 * The initial X speed of the child.
	 */
	private double xSpeed;
	
	/**
	 * The initial Y speed of the child.
	 */
	private double ySpeed;
	
	/**
	 * The IsHit strategy of the child.
	 */
	private ChildFactory.IsHitStrategy isHit;
	
	private Children (final double xSpeed, final double ySpeed, final int life, final ChildFactory.IsHitStrategy isHit) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.life = life;
		this.isHit = isHit;
	}
	
	/**
	 * A getter for the X speed.
	 * 
	 * @return a double containing the speed.
	 */
	public double getXSpeed() {
		return this.xSpeed;
	}
	
	/**
	 * A getter for the Y speed.
	 * 
	 * @return a double containing the speed.
	 */
	public double getYSpeed() {
		return this.ySpeed;
	}

	/**
	 * A getter for the life.
	 * 
	 * @return an Integer representing the amount of life.
	 */
	public Integer getLife() {
		return this.life;
	}
	
	/**
	 * A getter for the IsHit strategy. It returns directly the strategy to be executed.
	 * 
	 * @return a BiConsumer, which executes the IsHit strategy of the child.
	 */
	public ChildFactory.IsHitStrategy getIsHit () {
		return this.isHit;
	}
	
}
