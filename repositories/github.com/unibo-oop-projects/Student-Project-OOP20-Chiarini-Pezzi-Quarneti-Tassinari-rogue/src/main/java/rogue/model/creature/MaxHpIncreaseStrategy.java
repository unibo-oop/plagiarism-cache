package rogue.model.creature;

/**
 * This interface manages the strategy with which to increase the player's max health points.
 */
public interface MaxHpIncreaseStrategy {

    /**
     * @param level
     *          the current level value
     * @return the max health point value corresponding to the given level.
     */
    int getMaxHp(int level);

}
