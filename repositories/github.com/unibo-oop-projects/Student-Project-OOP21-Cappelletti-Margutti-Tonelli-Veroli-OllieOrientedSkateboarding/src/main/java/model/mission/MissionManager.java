package model.mission;

import java.util.Optional;

/**
 * 
 * Interface of a manager of {@link Mission}.
 *
 */
public interface MissionManager {

    /**
     * Gets the Optional of {@link Mission}.
     * @return the Optional of {@link Mission}.
     */
    Optional<Mission> getMission();

    /**
     * Updates the {@link Mission} counter.
     * 
     */
    void update();

    /**
     * Checks if the {@link Mission} is completed.
     * @return true if the mission is completed, false otherwise.
     */
    boolean isCompleted();

    /**
     * Gets the {@link Mission} mission reward.
     * @return the {@link Mission} mission reward.
     */
    int getReward();

}
