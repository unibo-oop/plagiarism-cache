package it.unibo.oop.manpac.view.screens.menu;

/**
 * Interface for screen setting management.
 */
public interface ScreenObserver {

    /**
     * Set the main menu screen.
     */
    void setMainMenuScreen();

    /**
     * Set the settings screen.
     */
    void setSettingsScreen();

    /**
     * Set the game over screen.
     * 
     * @param win True: set the victory screen False: set the game over screen
     * @param currentScore The current score at the end of the game
     * @param currentHighScore The current high score at the end of the game
     */
    void setGameOverScreen(boolean win, int currentScore, int currentHighScore);

    /**
     * Set the game view screen.
     */
    void setGameScreen();

    /**
     * Close the game.
     */
    void closeGame();

    /**
     * Release all resource.
     */
    void dispose();
}
