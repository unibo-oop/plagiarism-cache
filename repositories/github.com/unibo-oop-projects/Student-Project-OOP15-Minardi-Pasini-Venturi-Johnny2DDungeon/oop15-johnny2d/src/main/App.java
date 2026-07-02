package main;

import it.unibo.oop.controller.ControllerImpl;

/**
 * Entry for the application.
 */
public final class App {

    private App() {
    }

    /**
     * @param args
     *            ignored.
     */
    public static void main(final String... args) {
        ControllerImpl.getInstance();
    }
}
