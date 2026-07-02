package game.game_state;

import java.util.Optional;

import game.game_model.game_level.IFactoryLevelGame;
import game.game_model.game_level.FactoryLevelGame;

import game.game_model.game_level.IGameLevel;
import model.level.types.LevelType;

/**
 * Manages the loading and progression of game levels during play.
 * <p>
 * Keeps track of the current level and provides methods to load
 * the current level or advance to the next one.
 * </p>
 */
public class PlayStateManager {
    private LevelType currentLevel;
    private final IFactoryLevelGame lvlFactoryGame;

    /**
     * Constructs a new PlayStateManager with the initial level set to
     * LEVEL_TUTORIAL.
     */
    public PlayStateManager() {
        this.lvlFactoryGame = new FactoryLevelGame();
        this.currentLevel = LevelType.LEVEL_TUTORIAL;
    }

    /**
     * Loads the current level instance.
     *
     * @return the {@link IGameLevel} instance corresponding to the current level
     */
    public IGameLevel loadCurrentLevel() {
        return lvlFactoryGame.createLevelGame(currentLevel);
    }

    /**
     * Loads the next level if available.
     * <p>
     * Advances the internal currentLevel to the next one if present,
     * and returns the new level instance wrapped in an {@link Optional}.
     * If no further levels exist, returns {@link Optional#empty()}.
     * </p>
     *
     * @return an {@link Optional} containing the next {@link IGameLevel} if
     *         present, or empty otherwise
     */
    public Optional<IGameLevel> loadNextLevel() {
        Optional<LevelType> next = currentLevel.next();
        if (next.isPresent()) {
            currentLevel = next.get();
            return Optional.of(lvlFactoryGame.createLevelGame(currentLevel));
        }
        return Optional.empty();
    }

    /**
     * Returns the current level type.
     *
     * @return the current {@link LevelType}
     */
    public LevelType getCurrentLevelType() {
        return currentLevel;
    }
}
