package game.game_model.game_level;

import model.level.types.LevelType;

/**
 * <p>
 * Provides methods to create LevelGame.
 * </p>
 */
public interface IFactoryLevelGame {

    /**
     * Creates a new game level instance for the specified level type.
     * 
     * @param lvlType the {@link LevelType} that identifies the desired level to
     *                create
     * @return a new {@link IGameLevel} instance representing the specified level
     */
    IGameLevel createLevelGame(final LevelType lvlType);
}
