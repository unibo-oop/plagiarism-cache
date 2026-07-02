package fargoal.model.entity.commons.api;

/**
 * An interface to modify, set or get the health of a specified FloorElement.
 */
public interface Health {
    /**
     * Getter for the current health.
     * 
     * @return current health.
     */
    Integer getCurrentHealth();

    /**
     * Getter for the max health value.
     * 
     * @return the max health
     */
    Integer getMaxHealth();

    /**
     * Decreases the current health value.
     * 
     * @param amount to subtract to the current health value.
     */
    void decreaseHealth(Integer amount);

    /**
     * Increases the current health value.
     * 
     * @param amount to add to the current health value.
     */
    void increaseHealth(Integer amount);

    /**
     * Set the current health value to the given amount value.
     * 
     * @param amount to set as entity's health
     */
    void setHealth(Integer amount);

    /**
     * Sets the maximum health value to the given amount value.
     * 
     * @param amount to set as entity's max health.
     */
    void setMaxHealth(Integer amount);

    /**
     * Set the current health to the max health value possible
     * for the current entity.
     */
    void setToMaxHealth();

    /**
     * Method to check if the Entity current Health value
     * is equals to the max Health value. 
     * 
     * @return if the Entity has all hp
     */
    boolean isHealthy();
}
