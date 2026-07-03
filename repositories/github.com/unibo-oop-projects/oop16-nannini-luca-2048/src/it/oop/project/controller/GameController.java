package it.oop.project.controller;

import it.oop.project.util.Direction;

/**
 * This is the 2048 game controller.
 *
 */
public interface GameController {

    /**
     * Shifts the board to the specified direction.
     * 
     * @param direction
     *            direction to shift.
     */
    void shift(Direction direction);

    /**
     * Loads a new game.
     * 
     */
    void newGame();

    /**
     * Does default operation if button is pressed.
     */
    void buttonPressed();

    /**
     * Sets volume on or off.
     * 
     * @param volumeOn
     *            specifies if volume is on or not.
     */
    void setVolume();

}
