 package fargoal;

import fargoal.model.core.GameEngine;

/**
 * this class is the entry point of the application fargoal.
 */
public final class Fargoal {

    private Fargoal() { }

    /**
     * starts the application.
     * 
     * @param args - this argument is not used
     */
    public static void main(final String[] args) {
        new GameEngine().start();
    }
}
