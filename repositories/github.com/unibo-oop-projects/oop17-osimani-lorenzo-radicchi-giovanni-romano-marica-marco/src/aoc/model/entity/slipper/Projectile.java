package aoc.model.entity.slipper;

import aoc.model.WorldConstants;

/**
 * This enumeration collects all the slipper's types.
 * Is needed in preparation for future additions of new slipper's types.
 */
public enum Projectile {
	
	/**
	 * The basic projectile thrown by the mother, available from the beginning of the game.
	 */
	BASIC_SLIPPER (WorldConstants.SONIC_SPEED, 0, WorldConstants.NORMAL_DAMAGE);
	
	/**
	 * The initial X speed of the child.
	 */
	private double xSpeed;
	
	/**
	 * The initial Y speed of the child.
	 */
	private double ySpeed;
	
	/**
	 * The damage dealt by the slipper.
	 */
	private int damage;
	
	Projectile (final double xSpeed, final double ySpeed, final int damage) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.damage = damage;
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
	 * A simple getter of slipper's damage.
	 * 
	 * @return an int which represents the amount of damage dealt.
	 */
	public int getDamage() {
		return this.damage;
	}

}
