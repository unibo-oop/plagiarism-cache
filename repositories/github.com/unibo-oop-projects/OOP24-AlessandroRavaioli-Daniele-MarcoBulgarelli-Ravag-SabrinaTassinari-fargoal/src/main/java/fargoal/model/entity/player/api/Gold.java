package fargoal.model.entity.player.api;

/**
 * Interface to manage gold.
 */
public interface Gold {
    /**
     * Gets the current amount of gold saved in the {@link Gold} object.
     * 
     * @return the {@link Integer} that represents the current gold amount.
     */
    Integer getCurrentGold();

    /**
     * Gets the maximum amount of gold carriable.
     * 
     * @return the {@link Integer} that represents the maximum amount of gold transportable.
     */
    Integer getMaxCapacity();

    /**
     * Gets the total amount of gold donated.
     * 
     * @return the gold donated to the temple.
     */
    Integer getGoldDonated();

    /**
     * Sets the maximum capacity of gold that can be carried.
     * 
     * @param amount - the new maximum capacity of gold.
     */
    void setMaxCapacity(Integer amount);

    /**
     * Sets the amount of gold donated.
     * 
     * @param amount - the new amount of gold donated. Must be non-null and non-negative.
     * 
     * @throws IllegalArgumentException if the amount is null or negative.
     */
    void setGoldDonated(Integer amount);

    /**
     * Sets current gold amount to zero.
     */
    void resetGold();

    /**
     * Adds gold to the player's current amount, up to the maximum capacity.
     * 
     * @param amount - the amount of gold to add. Must be non-null and non-negative.
     * 
     * @return the leftover gold amount that could not be added due to capacity limits.
     * 
     * @throws IllegalArgumentException if the amount is null or negative.
     */
    Integer addGold(Integer amount);

    /**
     * Removes a specified amount of gold from the current gold.
     * If the amount to be removed exceeds the current gold, the gold is set to zero instead.
     * 
     * @param amount - the amount of gold to remove.
     */
    void removeGold(Integer amount);
}
