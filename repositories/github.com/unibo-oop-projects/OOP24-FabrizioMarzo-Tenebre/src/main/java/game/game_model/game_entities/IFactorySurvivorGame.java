package game.game_model.game_entities;

import model.entities.survivor.Survivor;

/**
 * <p>
 * Provides methods to create different types of game survivors with
 * their associated graphics and input components.
 * </p>
 */
public interface IFactorySurvivorGame {

    /**
     * Creates a common type of {@link IGameSurvivor} using the provided
     * {@link Survivor} instance.
     * <p>
     * This method initializes the game survivor with standard graphics and input
     * components.
     * </p>
     *
     * @param common the {@link Survivor} instance to wrap in a game survivor
     * @return a new {@link IGameSurvivor} instance configured with common
     *         components
     */
    IGameSurvivor gameSurvivorCommon(final Survivor common);

}
