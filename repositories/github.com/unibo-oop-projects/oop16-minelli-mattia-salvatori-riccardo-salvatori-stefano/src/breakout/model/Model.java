package breakout.model;

import java.util.List;

import breakout.model.entities.Ball;
import breakout.model.entities.Brick;
import breakout.model.entities.Paddle;
import breakout.model.entities.Wall;
import breakout.model.levels.BasicLevel;
import breakout.model.physics.GameObject;

/**
 * The interface for a generic model. It defines the main methods to build the
 * game.
 *
 */
public interface Model {

    /**
     * Getter for the current Level in game.
     * 
     * @return the current level.
     */
    BasicLevel getCurrentLevel();

    /**
     * Getter for the Score.
     * 
     * @return the player score.
     */
    int getScore();

    /**
     * Getter for the remaining lives.
     * 
     * @return the number of balls that can still fall before losing.
     */
    int getLife();

    /**
     * Getter for the paddle.
     * 
     * @return the paddle controlled by the player.
     */
    Paddle getPaddle();

    /**
     * Getter for the Balls.
     * 
     * @return the list of all the active balls.
     */
    List<Ball> getBalls();

    /**
     * Getter for the Bricks.
     * 
     * @return the list of all undestroyed bricks.
     */
    List<Brick> getBricks();

    /**
     * Getter for the Walls.
     * 
     * @return the list of all Walls.
     */
    List<Wall> getWalls();

    /**
     * Getter for the Game Status.
     * 
     * @return a game status defined in the enum {@link GameStatus}.
     */
    GameStatus getGameStatus();

    /**
     * The main function that update evry objects in the game and the game
     * itself.
     * 
     * @param time
     *            the elapsed time from a previous update
     */
    void updateAll(final double time);

    /**
     * A function that check the collisions between the objects during the game.
     * 
     * @return the list of collisions
     * 
     */
    List<GameObject> checkCollisions();

    /**
     * @param level
     *            the level to be played
     * @return a classic model
     */
    static ClassicMode createClassicGame(final BasicLevel level) {
        return new ClassicMode(level);
    }

    /**
     * 
     * @param levelList
     *            the levels list to be played
     * @return an advanced model
     */
    static AdvancedMode createAdvancedGame(final List<BasicLevel> levelList) {
        return new AdvancedMode(levelList);
    }

}
