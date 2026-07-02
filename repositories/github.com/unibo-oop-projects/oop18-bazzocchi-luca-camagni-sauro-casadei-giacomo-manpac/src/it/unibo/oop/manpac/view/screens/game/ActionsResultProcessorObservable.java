package it.unibo.oop.manpac.view.screens.game;

import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;
/**
 * Useful methods for updating the view and manage collision and input results 
 * (via Observer Pattern, this is the observable class).
 */
public interface ActionsResultProcessorObservable {

    /**
     * Update the phantom textures whenever is needed.
     * 
     * @param phantomName the name of the phantom which need a texture update
     * @param direction   the current direction of the phantom
     */
    void updatePhantomTexture(Entity phantomName, Directions direction);

    /**
     * Updates the score (current and HighScore).
     */
    void readyToDestroy();

    /**
     * Pacman just ate a power pill, phantoms are now on fear.
     */
    void enablePower();

    /**
     * Pacman just hit a portal, it will be teleported in the next update cycle.
     */
    void pacmanEffect();

    /**
     * Set the GameOverScreen.
     * 
     * @param win true, if all pills had been eaten. False if pacman died
     */
    void setGameOver(boolean win);

    /**
     * Set if Pacman has just lost a life.
     * 
     * @param lost whether or not the life has just been lost
     */
    void lifeJustLost(boolean lost);

    /**
     * Set if a phantom has just been eaten.
     * 
     * @param eaten whether or not a phantom has just been eaten
     */
    void phantomJustEaten(boolean eaten);

    /**
     * Change the current direction of pacman.
     * 
     * @param direction the new direction of pacman
     */
    void setPacmanDirection(Directions direction);

}
