package rogue.model.creature;

/**
 * An interface modeling a specific {@link Life} for the {@link Player}.
 */
public interface PlayerLife extends Life {

    /**
     * Increase the player experience.
     * @param amount
     *          the quantity to add to the player experience
     */
    void increaseExperience(int amount);

    /**
     * Increase the player health points.
     * @param amount
     *          the quantity to add to the player health points
     */
    void powerUp(int amount);

    /**
     * Increase the player strength.
     * @param amount
     *          the quantity to add to the player strength
     */
    void addStrength(int amount);

    /**
     * @return the player strength
     */
    int getStrength();

    /**
     * Increase the leftover player food.
     * @param amount
     *          the quantity to add/subtract to the leftover food.
     */
    void increaseFood(int amount);

    /**
     * Decrease the leftover player food.
     * @param amount
     *          the amount to subtract to the leftover food.
     */
    void decreaseFood(int amount);

    /**
     * @return the quantity of food left
     */
    int getFood();

    /**
     * @return the player coins
     */
    int getCoins();

    /**
     * Adds the given amount of coins to the player.
     * @param amount
     *          the amount of coins to add
     */
    void addCoins(int amount);

    /**
     * Subtracts the given amount of coins to the player.
     * @param amount
     *          the amount of coins to subtract
     */
    void subCoins(int amount);

    /**
     * @return the player level
     */
    int getLevel();

    /**
     * @return the player maximum health points
     */
    int getMaxHealthPoints();

    /**
     * @return the maximum food
     */
    int getMaxFood();

}
