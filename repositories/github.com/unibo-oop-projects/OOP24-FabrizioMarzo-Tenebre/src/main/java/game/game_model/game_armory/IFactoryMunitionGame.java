package game.game_model.game_armory;

import model.armory.munition.Munition;

/**
 * Factory class responsible for creating {@link IGameMunition} instances.
 * <p>
 * This factory produces game munition objects configured with appropriate
 * graphics components.
 * </p>
 */
public interface IFactoryMunitionGame {

    /**
     * Creates a new {@link IGameMunition} instance for the given munition.
     *
     * @param munition the underlying {@link Munition} model to wrap
     * @return a new {@link IGameMunition} configured with the appropriate graphics
     *         component
     */
    IGameMunition gameMunition(final Munition munition);
}
