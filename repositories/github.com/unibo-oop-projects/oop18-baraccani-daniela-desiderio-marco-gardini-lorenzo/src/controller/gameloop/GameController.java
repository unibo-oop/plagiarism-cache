package controller.gameloop;
 
import java.util.List;
 
import controller.score.Score;
import model.Model;
import model.entitiesutil.Entity;
import model.entitiesutil.MovementValue;
import model.levels.GameLevel;
 
/**
 * Interface of the game controller. Contains the game loop and it has methods
 * to start, check the status and stop the game.
 */
public interface GameController {
 
    /**
     * Number of cycles done by the game loop for each second.
     */
    int FPS = 40;
 
    /**
     * Start a new game loop.
     * 
     * @throws IllegalStateException if another game is running
     */
    void startNewGame();
 
    /**
     * Return the current score made by the player.
     * 
     * @return the current score
     */
    int getScore();
 
    /**
     * Return the list containing the whole bunch of {@link Entity}s not hidden.
     * 
     * @return the list of the current alive entities given from the {@link Model}
     */
    List<Entity> getLevelEntities();
 
    /**
     * Return the visibility state of the view.
     * 
     * @return true if view is visible, false otherwise
     */
    boolean isViewVisible();
 
    /**
     * Set the view visible state variable.
     * 
     * @param value to set to the view visible state variable
     */
    void setViewVisible(boolean value);
 
    /**
     * Called every time {@link Model} loads a new {@link GameLevel}.
     */
    void newLevelLoaded();
 
    /**
     * Return the state of the game loop.
     * 
     * @return true if the game loop is running, false otherwise
     */
    boolean isRunning();
 
    /**
     * Stop the timer and so the game loop.
     */
    void stop();
 
    /**
     * The error handler that must be called if an Exception is thrown or if an
     * error occurs.
     * 
     * @param message  to show to the user
     * @param critical if true the application closes after closing the alert
     *                 message
     */
    void handleError(String message, boolean critical);
 
    /**
     * Return the number of actual lives of the player.
     * 
     * @return the number of lives
     */
    int getLives();
 
    /**
     * Return the current number of the loaded game level.
     * 
     * @return the current level number
     */
    int getLevelNumber();
 
    /**
     * Return the list of executed actions since last call and clear it.
     * 
     * @return the list of {@link Action} made from last call to that moment
     */
    List<MovementValue> getExecutedActions();
 
    /**
     * Pause the game loop and open pause scene.
     */
    void pauseGame();
 
    /**
     * Stop the game loop and open the game over scene.
     */
    void gameOver();
 
    /**
     * Set the player name.
     * 
     * @param playerName to set in {@link Model}
     */
    void setPlayerName(String playerName);
 
    /**
     * Return the current player name set in {@link Model}.
     * 
     * @return the current player name
     */
    String getPlayerName();
 
    /**
     * Return the pause state of the game.
     * 
     * @return true if the game is paused, false otherwise
     */
    boolean isPaused();
 
    /**
     * Restart game timer for game loop and resume from pause.
     * 
     * @param restartTimer true if it's going back to game, false if going back to menu or other scenes
     */
    void exitPause(boolean restartTimer);
 
    /**
     * Stop the game loop and open the victory scene.
     */
    void victory();
 
    /**
     * Return the current scoreboard saved on the user's computer.
     * 
     * @return The list of {@link Score}
     */
    List<Score> getScoreboard();
 
}