package zombietsunami.api;

/**
 * This interface represents an entity in the Zombie Tsunami game.
 */
public interface Entity {
    /**
     * @return The x-coordinate of the zombie.
     */
    int getX();

    /**
     * @return The y-coordinate of the zombie.
     */
    int getY();

    /**
     * @return The speed of the zombie.
     */
    int getSpeed();

    /**
     * @return The strength of the zombie.
     */
    int getStrength();

    /**
     * Increase the strength of the zombie with the speed value.
     */
    void increaseStrength();

    /**
     * Sets the strength of the zombie.
     * 
     * @param strength The new strength value for the zombie.
     */
    void setStrength(int strength);

    /**
     * Sets the X-coordinate of zombie.
     * 
     * @param x The new X-coordinate value for the zombie.
     */
    void setX(int x);

    /**
     * Sets the Y-coordinate of zombie.
     * 
     * @param y The new Y-coordinate value for the zombie.
     */
    void setY(int y);

    /**
     * Decrease the strength of the zombie with the speed value.
     */
    void decreaseStrength();
}
