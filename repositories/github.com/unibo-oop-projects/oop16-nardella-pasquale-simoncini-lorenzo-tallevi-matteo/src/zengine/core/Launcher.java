package zengine.core;

import java.awt.EventQueue;

/**
 * This class contains the main method and it's the first class that initializes
 * Zengine.
 */
public final class Launcher {

    private Launcher() {
    }

    private static void launch() {
        final Zengine ze = Zengine.getZengine(); // creates zengine
        ze.initialize();
    }

    /**
     * The main methods that launches Zengine.
     * 
     * @param args
     *            is unused
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                launch();
            }
        });
    }
}
