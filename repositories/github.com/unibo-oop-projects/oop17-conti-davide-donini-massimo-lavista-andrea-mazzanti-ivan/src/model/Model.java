package model;

import java.util.List;
import java.util.Optional;

import model.entities.Entity;
import model.entities.properties.DirectionX;
import model.entities.properties.DirectionY;
import utility.GameModes;
import model.data.GameData;
import model.data.GlobalData;

/**
 * Model of the application.
 */
public interface Model {

    /**
     * 
     * @return the list of entities
     */
    List<Entity> getEntities();

    /**
     * 
     * @return the game data
     */
    GameData getGameData();

    /**
     * 
     * @return the global data
     */
    GlobalData getGlobalData();

    /**
     * Initialize state of model to start the game.
     * 
     * @param gameMode
     *            the game mode that must be loaded.
     */
    void initGame(GameModes gameMode);

    /**
     * Update the state of the application.
     * 
     * @param timeElapsed
     *            time elapsed from last update
     */
    void update(int timeElapsed);

    /**
     * 
     * @return true if game is over
     */
    boolean isGameOver();

    /**
     * 
     * @return true if the player's score is a new high scores
     */
    boolean isHighScore();

    /**
     * End the game, update global data and delete entities.
     * 
     * @param name
     *            player's name
     */
    void endGame(Optional<String> name);

    /**
     * Set the direction of the spaceship on the X axis.
     * 
     * @param directionX
     *            direction axis X
     */
    void setSpaceshipDirectionX(DirectionX directionX);

    /**
     * Set the direction of the spaceship on the Y axis.
     * 
     * @param directionY
     *            direction axis Y
     */
    void setSpaceshipDirectionY(DirectionY directionY);

    /**
     * Set spaceship of shoot.
     *
     * @param shoot
     *            indicates if the spaceship can shoot.
     */
    void setSpaceshipShoot(boolean shoot);

}
