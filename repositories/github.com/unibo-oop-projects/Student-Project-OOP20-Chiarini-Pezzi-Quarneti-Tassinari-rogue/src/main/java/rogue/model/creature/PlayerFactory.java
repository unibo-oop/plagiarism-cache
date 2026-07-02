package rogue.model.creature;

/**
 * An interface modeling a factory for the {@link Player}.
 */
public interface PlayerFactory {

    /**
     * Creates a new {@link Player} with defaults life settings.
     * @return a Player
     */
    Player create();

    /**
     * Creates a new {@link Player} with the given life.
     * @param life
     *          the player life
     * @return a Player
     */
    Player createByLife(PlayerLife life);

}
