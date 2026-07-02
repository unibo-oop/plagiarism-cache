package it.unibo.the100dayswar.application;

import it.unibo.the100dayswar.controller.maincontroller.api.MainController;
import it.unibo.the100dayswar.controller.maincontroller.impl.MainControllerImpl;

/**
 * The main class of The100DaysWar game.
 */
public final class The100DaysWar {
    /**
     * A static reference to the main controller of the game.
     */
    public static final MainController CONTROLLER = new MainControllerImpl();

    /** 
     * A private constructor to hide the implicit public one.
     */
    private The100DaysWar() {
    }

    /**
     * The main method to start the game.
     *
     * @param args the arguments of the application
     */
    public static void main(final String[] args) {
        CONTROLLER.startGame();
    }
}
