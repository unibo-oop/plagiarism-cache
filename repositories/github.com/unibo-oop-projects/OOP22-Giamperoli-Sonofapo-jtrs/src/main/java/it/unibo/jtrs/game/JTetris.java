package it.unibo.jtrs.game;

import it.unibo.jtrs.controller.impl.ApplicationImpl;

/**
 * Entrance application point.
 */
public final class JTetris {

    private JTetris() { }

    /**
     * Main class.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        new ApplicationImpl();
    }
}
