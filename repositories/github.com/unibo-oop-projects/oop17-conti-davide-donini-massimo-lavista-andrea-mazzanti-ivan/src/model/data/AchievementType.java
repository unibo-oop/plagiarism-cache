package model.data;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Classification of the different type of obtainable achievements.
 */
public enum AchievementType {

    /**
     * All types of obtainable achievements.
     */
    GAMES_PLAYED(1, 3, 10, 20, 30),
    /**
     * time played in minute.
     */
    TIME_PLAYED(1, 4, 12, 25, 36),
    /***/
    NUM_DEAD_ENEMIES(30, 100, 300, 1000, 3000), NUM_POWER_UP(5, 20, 50, 200, 500);

    private final SortedSet<Integer> achievementsTargets;

    /**
     * Initialize targets of achievements.
     * 
     * @param targets
     *            value of the target
     */
    AchievementType(final Integer... targets) {
        achievementsTargets = new TreeSet<>(Arrays.asList(targets));
    }

    /**
     * 
     * @return all target for this type of achievement
     */
    public SortedSet<Integer> getAchievementsTargets() {
        return achievementsTargets;
    }

    @Override
    public String toString() {
        switch (this) {
        case GAMES_PLAYED:
            return "Games played";
        case TIME_PLAYED:
            return "Time played";
        case NUM_DEAD_ENEMIES:
            return "Enemies killed";
        case NUM_POWER_UP:
            return "Power ups taken";
        default:
            throw new IllegalArgumentException();
        }
    }

}
