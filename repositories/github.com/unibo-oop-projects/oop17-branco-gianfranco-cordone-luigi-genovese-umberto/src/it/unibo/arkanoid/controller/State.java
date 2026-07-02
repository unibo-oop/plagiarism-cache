package it.unibo.arkanoid.controller;

/**
 * 
 * Interface used for state pattern.
 *
 */
public interface State {

    /**
     * Method that allows to manage the controller.
     * 
     * @param controller
     *            controller object.
     */
    void updateState(Controller controller);

}
