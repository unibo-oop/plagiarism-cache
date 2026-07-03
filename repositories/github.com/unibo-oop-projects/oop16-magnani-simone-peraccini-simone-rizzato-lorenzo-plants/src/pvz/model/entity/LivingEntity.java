package pvz.model.entity;

import pvz.utility.Pair;

/**
 * Defines the base living entity.<br>
 * Such an entity has an health value, and therefore can be damaged.
 */
public abstract class LivingEntity extends AbstractEntity {

	protected int health;
	protected int maxHealth;

    /**
     * Instantiates a living entity at the given position.
     * 
     * @param position
     *            entity position
     */
	public LivingEntity(Pair<Double, Double> position, double width, double height) {
		super(position, width, height);
	}

	/**
	 * Damages this entity by the given damage amount.
	 * 
	 * @param damage
	 *            damage value
	 */
	public void hurt(int damage) {
		// The removal logic should be handled in the update routine
		this.health -= damage;
	}

	/**
	 * Returns the remaining health percentage as a floating point value ranging
	 * from 0.0 to 1.0.
	 * 
	 * @return health percentage
	 */
	public double getHealthPercentage() {
		return (float) this.health / this.maxHealth;
	}

}
