package laterunner;

import laterunner.core.GameEngine;

/**
 * Main class.
 */
public final class Main {

    private Main() { }

    /**
     * Main method.
     * 
     * @param args
     *          args
     */
    public static void main(final String[] args) {
        GameEngine engine = new GameEngine();
        engine.gameInit();
    }

}
