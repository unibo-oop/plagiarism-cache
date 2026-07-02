package it.unibo.scat.model.api;

import it.unibo.scat.common.Direction;
import it.unibo.scat.common.GameState;

/**
 * Interface for the Model class, seen from the Controller.
 */
public interface ModelInterface {

    /**
     * Adds player's shot in the game.
     */
    void addPlayerShot();

    /**
     * Initilizes entities and leaderboard files.
     * 
     * @param entitiesFile    the files that contains all the informations to
     *                        properly initialize every entity.
     * @param leaderboardFile the files that contains the leaderboard.
     */
    void initEverything(String entitiesFile, String leaderboardFile);

    /**
     * Performs a single game loop update.
     * This method moves all entities, checks and handles collisions, updates the
     * score accordingly, removes dead shots, updates invaders' direction when
     * needed, handles the bonusInvader and verifies whether the game has reached an
     * end condition.
     * If the game is no longer in the "PLAYING" state, the game is
     * ended.
     */
    void update();

    /**
     * Reset all entities throught the gameLogic and restores score and difficulty.
     */
    void resetGame();

    /**
     * Moves the plater in the given direction.
     * Gets the player from the gameWorld and updates its position.
     * 
     * @param direction the movement direction
     */
    void movePlayer(Direction direction);

    /**
     * Sets the game state.
     *
     * @param state the state the game needs to be setted.
     */
    void setGameState(GameState state);

    /**
     * Game state getter.
     *
     * @return the current game state.
     */
    GameState getGameState();

    /**
     * Sets player's username.
     * 
     * @param username the username that the player chose.
     */
    void setUsername(String username);
}
