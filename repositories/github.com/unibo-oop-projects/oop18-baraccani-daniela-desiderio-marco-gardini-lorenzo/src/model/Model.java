package model;

import controller.gameloop.GameController;
import model.levels.GameLevel;

/**
 * Interface for the game's model. Manage every logic part of the game.
 */
public interface Model {

    /**
     * Set the starting value to all variables.
     */
    void reset();

    /**
     * Set the internal controller to the one passed. Can be called just once.
     *
     * @throws IllegalStateException if called twice
     * @param controller to use
     */
    void setController(GameController controller);

    /**
     * Return the current controller which is managing the game.
     * 
     * @return the {@link GameController}
     */
    GameController getController();

    /**
     * Set the level number to the first level and start new game.
     */
    void newGame();

    /**
     * Import the current level from {@link LevelUtils} and, if the view is visible,
     * tell the {@link Controller} that the level changed.
     */
    void startLevel();

    /**
     * Increment the level number and check if it's available. If it is, start it,
     * otherwise process victory.
     */
    void nextLevel();

    /**
     * Decrement the lives variable and restart the level if there is any more live
     * left, go to game over screen and write score in scoreboard if there are no
     * more lives.
     */
    void processDeath();

    /**
     * Add or remove points (allowed negative amount to remove points).
     * 
     * @param amount of points to add
     */
    void addPoints(int amount);

    /**
     * Return the current score made by the player.
     * 
     * @return the score
     */
    int getScore();

    /**
     * Return the current lives amount.
     * 
     * @return the lives left
     */
    int getLives();

    /**
     * Set a new player name to show during the game and save in scoreboard.
     * 
     * @param playerName to save
     */
    void setPlayerName(String playerName);

    /**
     * Return the last player name set.
     * 
     * @return the player name if present, empty string otherwise
     */
    String getPlayerName();

    /**
     * Return the level is being played.
     * 
     * @return the current level
     */
    GameLevel getLevel();

    /**
     * Manage {@link Entity}s movements, check collisions and show {@link PowerUp}s.
     */
    void executeLoopCycle();

    /**
     * Return the game over state.
     * 
     * @return true if the player lost, false otherwise
     */
    boolean isGameOver();

    /**
     * Set the {@link GameLevel}, used in {@link GameLevelTest}.
     * 
     * @param gameLevel is the {@link GameLevel} to set
     */
    void setLevel(GameLevel gameLevel);

    /**
     * Write current score in scoreboard and tell the {@link GameController} to do
     * victory stuff.
     */
    void processVictory();

    /**
     * Check if all enemies are dead and call next level if no enemies are left.
     */
    void enemyDied();

}
