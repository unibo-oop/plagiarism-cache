package controller.timer;

import java.util.TimerTask;

import controller.TamagotchiController;

/**
 *
 */
public abstract class MyTimerRule extends TimerTask {

    private TamagotchiController controller;

    /**
     * Constructor of this class.
     * 
     * @param newController
     *            setup the controller.
     */
    protected MyTimerRule(final TamagotchiController newController) {
        this.controller = newController;
    }

    /**
     * method run to execute the rules.
     */
    public abstract void run();

    /**
     * 
     * @return the controller
     */
    public TamagotchiController getController() {
        return this.controller;
    }
}