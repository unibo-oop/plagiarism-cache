package ballblast.model;

import java.util.List;

import ballblast.commons.events.EventType;
import ballblast.model.data.GameData;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputType;
import ballblast.model.inputs.InputManager.PlayerTag;
import ballblast.model.levels.GameStatus;

/**
 * It represents a macro class, containing the current Level and his
 * {@link GameObject}s. It can be seen as an entry point because delegates its
 * tasks to the Level class.
 */
public interface Model {
    /**
     * Default World's width.
     */
    double WORLD_WIDTH = 200;
    /**
     * Default World's height.
     */
    double WORLD_HEIGHT = 100;

    /**
     * Starts new survival level.
     */
    void startSurvival();

    /**
     * Gets the {@link List} containing all {@link GameObject}s.
     * 
     * @return the gameObject list.
     */
    List<GameObject> getGameObjects();

    /**
     * Resolves the received inputs inside the InputManager.
     * 
     * @param tag    the {@link PlayerTag}.
     * @param inputs the {@link List} of inputs to be resolved.
     */
    void resolveInputs(PlayerTag tag, List<InputType> inputs);

    /**
     * Updates the game.
     * 
     * @param elapsed the time elapsed since last update.
     */
    void update(double elapsed);

    /**
     * Gets the {@link GameStatus}.
     * 
     * @return the status of the game.
     */
    GameStatus getGameStatus();

    /**
     * Gets the game data (score, time, destroyed balls ecc..).
     * 
     * @return the {@link GameData}.
     */
    GameData getGameData();

    /**
     * Gets the game's events.
     * 
     * @return the game's events.
     */
    List<EventType> getGameEvents();
}
