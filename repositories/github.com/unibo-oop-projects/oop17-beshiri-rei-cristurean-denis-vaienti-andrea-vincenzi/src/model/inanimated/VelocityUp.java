package model.inanimated;

/**
 * Interface that represent a power up item for Velocity.
 */
public interface VelocityUp extends Inanimated {

    /**
     * @return the cost of the item.
     */
    int getCost();

    /**
     * @return the amount of velocity to increase.
     */
    double getVelocity();
}
