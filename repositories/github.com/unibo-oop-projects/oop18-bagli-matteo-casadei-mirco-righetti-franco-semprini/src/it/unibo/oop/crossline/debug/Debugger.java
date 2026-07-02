package it.unibo.oop.crossline.debug;

/**
 * This Singleton pattern's class let write messages in output console for simplify debug.
 */
public final class Debugger {
    /**
     * The boolean used to choose if show debug or not.
     */
    private static final boolean DEBUG = true;
    /**
     * The instance of class.
     */
    private static final Debugger INSTANCE = new Debugger();

    private Debugger() {
    }

    /**
     * @return the instance of debugger
     */
    public static Debugger getInstance() {
        return INSTANCE;
    }

    /**
     * Prints in output standard a string message.
     * @param message the message to print
     */
    public void printMessage(final String message) {
        if (DEBUG) {
            System.out.println(message);
        }
    }

}
