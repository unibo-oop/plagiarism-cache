package model.mission;

/**
 * 
 * Interface that identifies a mission in the game.
 *
 */
public interface Mission {

    /**
     * Checks if the mission is completed.
     * @return true if the mission is completed, false otherwise.
     */
    boolean isCompleted();

    /**
     * Updates the mission counter.
     * 
     */
    void updateCounter();

    /**
     * Gets the mission counter.
     * @return the mission counter.
     */
    int getCounter();

    /**
     * Gets the mission reward.
     * @return the mission reward.
     */
    int getReward();

}
