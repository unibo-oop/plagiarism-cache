package aoc.model.entity.slipper;

import aoc.utilities.Vector;
import aoc.model.entity.Entity;

/**
 * This class is the basic implementation for a slipper.
 */
public class Slipper extends Entity implements SlipperInterface {
	
	/**
	 * The damage dealt by the slipper.
	 */
	private int damage;
	
	public Slipper(final Vector position, final Projectile projectile) {
		super(position, new Vector(projectile.getXSpeed(), projectile.getYSpeed()), projectile.name());
		this.damage = projectile.getDamage();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hit() {
		this.kill();
		return this.damage;
	}

}
