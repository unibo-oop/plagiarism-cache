package controller.matchmanager;

/**
 *
 * @author Oleg
 *
 */
public interface TurnManager {

    /**
     * starts the turn loop.
     */
    void start();

    /**
     * stops the turn loop.
     */
    void stop();

    /**
     *
     * @return true if the turn is started, false otherwise.
     */
    boolean isTurnStarted();

    /**
     *
     * @param gameManager
     *            game manager
     */
    void setGameManger(GameManager gameManager);
}
