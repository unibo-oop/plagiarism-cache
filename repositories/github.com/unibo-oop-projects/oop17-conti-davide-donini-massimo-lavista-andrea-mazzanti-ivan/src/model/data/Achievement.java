package model.data;

import java.util.SortedSet;
import java.util.Optional;

/**
 * Representation of achievement obtainable by player.
 */
public interface Achievement {

    /**
     * 
     * @return the current value of player in this type of achievements.
     */
    int getPlayerValue();

    /**
     * 
     * @return all the target achievable by player for this type.
     */
    SortedSet<Integer> getAllTargets();

    /**
     * 
     * @return the closest target obtainable in this type.
     */
    Optional<Integer> getNextTargets();

    /**
     * 
     * @return the current level of achievement.
     */
    int getLevelAchievement();

}
