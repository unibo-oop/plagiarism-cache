package it.unibo.monoopoly.application;

import it.unibo.monoopoly.controller.menu.impl.MenuControllerImpl;

/**
 * Main class that starts the application.
 */
public final class Monoopoly {

    /**
     * Unused constructor, must be private.
     */
    private Monoopoly() {

    }

    /**
     * Start of the application.
     * @param args parameters not used
     */
    public static void main(final String[] args) {
        new MenuControllerImpl();
    }

}
