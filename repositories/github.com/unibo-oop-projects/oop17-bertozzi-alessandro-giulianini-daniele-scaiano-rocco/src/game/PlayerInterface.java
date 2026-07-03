package game;

/**
 * creating the player interface.
 */
public interface PlayerInterface {

    /**
     * getter for the heath.
     * @return health of this player.
     */
    int getHealth();

    /**
     * resets the position, used between each level.
     */
    void resetPosition();

    /**
     * setter for health.
     * @param hps of this player.
     */
    void setHealth(int hps);

    /**
     * setter for the shot cd.
     * @param cd of this 
     */
    void setShotCD(int cd);

    /**
     * returns the actual shot cd.
     * @return shot cd.
     */
    int getShotCD();

    /**
     * setter for the shield.
     * @param shieldAmount of the player.
     */
    void setShield(int shieldAmount);

    /**
     * getter for the shield.
     * @return shield of the player.
     */
    int getShield();

    /**
     * setter for the cd till next shot.
     * @param time of the player.
     */
    void setShotReadyIn(int time);

    /**
     * getter for the next shot cd.
     * @return shotReady of the player.
     */
    int getShotReadyIn();

}
