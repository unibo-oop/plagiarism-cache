package bubbleshooter.model;

import java.util.List;
import bubbleshooter.model.bubble.Bubble;
import bubbleshooter.model.bubble.BubblesManager;
import bubbleshooter.model.game.GameStatus;
import bubbleshooter.model.game.level.Level;

/**
 * Interface which represent the Logic of the Game in order to respect the MVC
 * design pattern.
 */
public interface Model {

    /**
     * The width of the game used by Model.
     */
    double WORLD_WIDTH = 700;

    /**
     * The Height of the game used by Model.
     */
    double WORLD_HEIGHT = 700;

    /**
     * Method used to update the {@link Level}.
     * 
     * @param elapsed The time elapsed every {@link GameLoop} cycle.
     */
    void update(double elapsed);

    /** Method called in the {@link Controller} class.
     * 
     * @return the list of the currents {@link Bubble} in the game.
     */
    List<Bubble> getBubbles();

    /**
     * Method called in the {@link Controller} class to start a {@link BasicLevel}.
     */
    void startBasicGame();

    /**
     * Method called in the {@link Controller} class to start a
     * {@link SurvivalLevel}.
     */
    void startSurvivalGame();

    /**
     * @return the current level of the Game.
     */
    Level getLevel();

    /**
     * @return the current status of the Game.
     */
    GameStatus getGameStatus();

    /**
     * @return the {@link BubblesManager} of the Game.
     */
    BubblesManager getBubblesManager();

    /**
     * Sets the gameStatus.
     * @param gameStatus The status of the game.
     */
    void setGameStatus(GameStatus gameStatus);
}
