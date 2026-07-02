package controller.game;

import javafx.scene.Group;

/**
 * This is the controller associated with GameView. It allows to create and update the world with its
 * components, it also manages the initialization and the end of the game.
 */
public interface GameController {

    /**
     * Initializes the {@link GameWorld} with its components.
     */
    void initialize();

    /**
     * Updates the {@link GameWorld} and its components.
     * @param delta
     *      The time passed after the previous update.
     */
    void update(double delta);

    /**
     * Returns to the {@link MainMenuView} after the player wins or loses the game.
     * @param playerName 
     *      The name of the player.
     */
    void endGame(String playerName);

    /**
     * Renders all the objects in the {@link GameWorld}.
     * @param gameContainer
     *      The group where all the objects are rendered.
     */
    void render(Group gameContainer);

    /**
     * Checks if the game is over or not.
     * @return
     *      True if the game is over, false otherwise.
     */
    boolean isGameOver();

    /**
     * Gets the player's remaining balls.
     * @return
     *      The player's remaining balls.
     */
    int getBallsRemaining();

    /**
     * Gets the player's actual score.
     * @return
     *      The player' score.
     */
    int getScore();

    /**
     * Gets the player's current multiplier.
     * @return
     *      The player's multiplier.
     */
    int getMultiplier();

    /**
     * Sets the current mouse position.
     * @param posX
     *      The X coordinate of the mouse current position.
     * @param posY
     *      The Y coordinate of the mouse current position.
     */
    void setMousePosition(Double posX, Double posY);

    /**
     * Launches the ball.
     */
    void launchBall();

    /**
     * Checks whether the game is won or not.
     * @return
     *      True if the game is won, false otherwise.
     */
    boolean isGameWon();
}
