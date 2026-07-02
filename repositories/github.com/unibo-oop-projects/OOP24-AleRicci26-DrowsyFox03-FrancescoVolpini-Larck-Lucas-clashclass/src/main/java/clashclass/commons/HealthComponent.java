package clashclass.commons;

import clashclass.ecs.Component;

/**
 * Represents the health of an in-game entity.
 */
public interface HealthComponent extends Component {
    /**
     * Decreases the current health value by a given amount.
     *
     * @param amount the amount of health to decrease
     */
    void decrease(int amount);

    /**
     * Increases the current health value by a given amount.
     *
     * @param amount the amount of health to increase
     */
    void increase(int amount);

    /**
     * Returns whether the health has reached zero.
     *
     * @return true if the health has reached zero, false otherwise
     */
    boolean isDead();

    /**
     * Getter method for current health value.
     *
     * @return the current value of health
     */
    int getHealth();
}
