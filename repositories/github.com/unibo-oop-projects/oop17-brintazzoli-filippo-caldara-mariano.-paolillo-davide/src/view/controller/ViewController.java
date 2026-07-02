package view.controller;

import controller.Controller;

/**
 * Abstract class for the controllers of the stages.
 */
public abstract class ViewController {

    /**
     * Initialize the different controller of the view.
     * 
     * @param controller
     *            the game {@link Controller}.
     */
    public abstract void init(Controller controller);

}
