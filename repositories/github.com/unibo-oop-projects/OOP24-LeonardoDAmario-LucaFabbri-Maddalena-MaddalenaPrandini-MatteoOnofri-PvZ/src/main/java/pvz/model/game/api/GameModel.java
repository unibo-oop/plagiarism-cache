package pvz.model.game.api;

import pvz.utilities.EntityType;
import pvz.utilities.GameEntity;
import pvz.utilities.Position;

import java.util.Set;

/**
 * Interface representing the core game model for the Plants vs. Zombies game.
 * <p>
 * Defines methods to control the game lifecycle, manage plants, update game state,
 * and retrieve key information such as sun count, kills, and game entities.
 */
public interface GameModel {


    /**
     * Checks if the game has ended.
     *
     * @return {@code true} if the game is over, {@code false} otherwise.
     */
    boolean isGameOver();

    /**
     * Checks if the player has won the game.
     *
     * @return {@code true} if the player achieved victory, {@code false} otherwise.
     */
    boolean isVictory();

    /**
     * Attempts to place a plant of the specified type at the given position on the game field.
     *
     * @param type     the type of plant to place (e.g., sunflower, peashooter).
     * @param position the position on the grid where to place the plant.
     */
    void placePlant(EntityType type, Position position);

    /**
     * Updates the entire game state based on the elapsed time since the last update.
     *
     * @param deltaTime the time passed since the last update (in milliseconds).
     */
    void update(long deltaTime);

    /**
     * Retrieves all active entities currently in the game.
     *
     * @return a set of {@link GameEntity} representing all game entities.
     */
    Set<GameEntity> getGameEntities();

    /**
     * Returns the current amount of sun resources available to the player.
     *
     * @return the current number of sun points.
     */
    int getSunCount();

    /**
     * Returns the number of zombies the player has defeated.
     *
     * @return the total kill count.
     */
    int getKillCount();
}
