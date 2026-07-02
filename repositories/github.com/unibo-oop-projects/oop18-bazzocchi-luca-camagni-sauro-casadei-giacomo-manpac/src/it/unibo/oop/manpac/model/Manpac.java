package it.unibo.oop.manpac.model;

/**
 * Interface for the model in the MVC pattern. Represents the internal part of
 * the model.
 * 
 *
 */
public interface Manpac {

    /**
     * Stops the pacman.
     * 
     * @return what happened after pacman was stopped
     */
    Action stopPacman();

    /**
     * Pacman eats a pill.
     * 
     * @return what happened after pacman eat a pill
     */
    Action pillEaten();

    /**
     * Pacman eats a power pill.
     * 
     * @return what happened after pacman eat a power pill
     */
    Action powerPillEaten();

    /**
     * Pacman eats a phantom.
     * 
     * @return what happened after pacman eat a phantom
     */
    Action phantomEaten();

    /**
     * Pacman has just lost a life.
     * 
     * @return what happened after the losing of a life
     */
    Action decreaseLives();

    /**
     * A phantom need to change its direction.
     * 
     * @param name the name of the phantom which needs to change its direction
     * 
     * @return what happened after the direction change
     */
    Action changePhantomDirection(Entity name);

    /**
     * Getter for the fear status of phantoms.
     * 
     * @return true if phantoms are feared
     */
    boolean arePhantomsFeared();
}
