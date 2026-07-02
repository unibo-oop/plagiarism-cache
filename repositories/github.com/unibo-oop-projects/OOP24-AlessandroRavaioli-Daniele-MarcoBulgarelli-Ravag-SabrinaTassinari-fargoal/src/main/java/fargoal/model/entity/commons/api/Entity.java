package fargoal.model.entity.commons.api;

import fargoal.model.commons.FloorElement;

/**
 * An Entity represent a FloorElement that can move, attack and be damaged.
 */
public interface Entity extends FloorElement {

    /**
     * Increases the current health value for this {@link Entity}.
     * 
     * @param amount to subtract to the current health value.
     */
    void increaseHealth(Integer amount);

    /**
     * Decreases the current health value for this {@link Entity}.
     * 
     * @param amount to subtract to the current health value.
     */
    void decreaseHealth(Integer amount);

    /**
     * Getter for the current health of this {@link Entity}.
     * 
     * @return current health.
     */
    Integer getCurrentHealth();

    /**
     * Getter for the max health value of this {@link Entity}.
     * 
     * @return the max health
     */
    Integer getMaxHealth();

    /**
     * Set the current health value to the given amount value for this {@link Entity}.
     * 
     * @param amount to set as entity's health
     */
    void setHealth(Integer amount);

    /**
     * Method to check if the {@link Entity} current Health value
     * is equals to the max Health value. 
     * 
     * @return if the Entity has all hp
     */
    boolean isHealthy();

    /**
     * Return the skill of the FloorElement selected.
     * 
     * @return the skill
     */
    Integer getSkill();

    /**
     * This method is used to check wether the Entity is dead or not.
     * 
     * @return true if player is dead, false otherwise.
     */
    boolean isDead();
}
