package controller;

/**
 * 
 * Interface to control the main functions of the application.
 *
 */
public interface MainController {

    /**
     * Start a new Game.
     */
    void newGame();

    /**
     * Switch the game state to pause.
     */
    void pause();

    /**
     * Go to the main menu.
     */
    void menu();

    /**
     * Resume the current game.
     */
    void continueGame();

    /**
     * Ends the game and allows the user to save the score.
     * @param score The final score of last game.
     */
    void gameOver(int score);

    /**
     * close the application.
     */
    void exit();

}
