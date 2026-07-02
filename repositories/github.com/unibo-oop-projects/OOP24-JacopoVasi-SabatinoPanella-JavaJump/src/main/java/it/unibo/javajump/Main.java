package it.unibo.javajump;

import it.unibo.javajump.controller.GameInitializer;
import it.unibo.javajump.controller.GameInitializerImpl;

/**
 * The Main class of the application, containing the entry point.
 */
public final class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        final GameInitializer newGame = new GameInitializerImpl();
        newGame.initialize();
    }

    /**
     * Private constructor for the Main class.
     *
     * @throws AssertionError the error thrown if the class is instantiated
     */
    private Main() {
        throw new AssertionError("This is the Main class, it should not be instantiated!");
    }
}
