package it.unibo.oop.manpac.controller;

/**
 * Interface for high score recovery from files.
 */

public interface FileResources {

    /**
     * Read the High Score from the file, creates it if it doesn't exist.
     * 
     * @param defaultHighScore The default value taken from the model, sets it if
     *                         the file does not exist
     * @return The value from the file or the defaultHighScore if the file doesn't exits
     */
    int startResources(int defaultHighScore);

    /**
     * Set the High Score in the file, create the file if it doesn't exist.
     * 
     * @param highScore The value to be set in the file
     */
    void setHighScore(int highScore);
}
