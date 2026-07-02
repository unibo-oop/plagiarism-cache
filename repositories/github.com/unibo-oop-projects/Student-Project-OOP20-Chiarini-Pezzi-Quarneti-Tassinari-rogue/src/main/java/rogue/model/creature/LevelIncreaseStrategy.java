package rogue.model.creature;

/**
 * This interface manages the strategy with which to increase the player's level.
 */
public interface LevelIncreaseStrategy {

    /**
     * @param experience
     *          the current experience value
     * @return the level value corresponding to the given experience
     */
    int getLevel(int experience);

}
