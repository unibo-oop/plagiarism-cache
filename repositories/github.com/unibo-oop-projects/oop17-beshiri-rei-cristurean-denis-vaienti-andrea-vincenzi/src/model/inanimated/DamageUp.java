package model.inanimated;

/**
 * Power up item for damage.
 */
public interface DamageUp extends Inanimated {

    /**
     * Getter for cost of item in points.
     * 
     * @return the cost of the DamageUp.
     */
    int getCost();

    /**
     * Getter for damage bonus obtained by item.
     * 
     * @return the amount of damage to increase.
     */
    int getDamage();
}
