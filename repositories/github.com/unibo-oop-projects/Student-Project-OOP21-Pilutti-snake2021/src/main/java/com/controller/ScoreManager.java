package main.java.com.controller;

/**
 * Interface that models a manager for the game's score.
 */
public interface ScoreManager {

    /**
     * Updates the current score and shows it on the screen.
     */
    void updateScore();

    /**
     * Saves and stores the final score on a file. Sets the file as non writable so
     * it can not be modified by the user.
     */
    void saveScore();

    /**
     * Shows the highscore on screen.
     */
    void showHiScore();
}
