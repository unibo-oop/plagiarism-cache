package game.game_model.game_level;

import java.util.List;

import game.game_model.game_armory.IGameMunition;
import game.game_model.game_entities.IGameSurvivor;
import game.game_model.game_entities.IGameZombie;
import model.level.types.Level;
import view.graphics.GraphicsLevel;

/**
 * Interface representing a game level.
 * 
 * <p>
 * This interface defines the core methods for accessing the level data,
 * game entities within the level, and updating the level's state and graphics.
 * </p>
 */
public interface IGameLevel {

    /**
     * Returns the underlying {@link Level} model object.
     *
     * @return the current level model
     */
    Level getLevel();

    /**
     * Returns the survivor controlled by the player within this level.
     *
     * @return the {@link IGameSurvivor} instance representing the player survivor
     */
    IGameSurvivor getGameSurvivor();

    /**
     * Returns the list of zombies currently present in the level.
     *
     * @return a {@link List} of {@link IGameZombie} representing enemies in the
     *         level
     */
    List<IGameZombie> getGameZombie();

    /**
     * Returns the list of munitions (ammo, bullets, etc.) available in the level.
     *
     * @return a {@link List} of {@link IGameMunition} available in the level
     */
    List<IGameMunition> getGameMunitions();

    /**
     * Updates the graphical representation of the level.
     *
     * @param graphicLvl the {@link GraphicsLevel} instance used to render the level
     */
    void updateGraphics(final GraphicsLevel graphicLvl);

    /**
     * Updates the internal state of the game level,
     * including entities and game logic.
     */
    void updateStateGameLevel();
}
