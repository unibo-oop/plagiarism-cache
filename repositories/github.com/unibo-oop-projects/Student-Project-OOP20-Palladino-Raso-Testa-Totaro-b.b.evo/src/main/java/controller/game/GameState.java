package controller.game;

import model.entities.GameBoard;
import model.leaderboard.Player;
import model.mapeditor.Level;
import model.utilities.Difficulty;

/**
 * Part of pattern gameloop implements all the methods for the objects in the game.
 *
 */
public interface GameState {

    /**
     * 
     * Set initial value of entities.
     */
    void init();

    /**
     * 
     * @return the current game phase
     */
    GameStatus getStatus();

    /**
     * 
     * @param phase set the game phase
     */
    void setStatus(GameStatus phase);

    /**
     * 
     * @return the board
     */
    GameBoard getBoard();

    /**
     * 
     * @return the level where the game is taking place
     */
    Level getLevel();

    /**
     * 
     * @return the current player
     */
    Player getPlayer();

    /**
     * 
     * @return score values
     */
    int getPlayerScore();

    /**
     * 
     * @return current number of lives
     */
    int getLives();

    /**
     * 
     * @return the difficulty of the level returns.
     */
    Difficulty getDifficulty();

    /**
     * 
     * @return if the music is active
     */
    boolean isMusicActive();

    /**
     * 
     * @return if the effects is active
     */
    boolean isEffectActive();
}
