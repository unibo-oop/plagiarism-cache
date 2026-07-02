package unibo.exiled;

import unibo.exiled.view.NewGameView;

/**
 * The main launcher of the Java application, made to build the JAR.
 */
public final class GameLauncher {

    private GameLauncher() {
    }

    /**
     * The main method.
     *
     * @param args No arguments should be passed to this program.
     */
    public static void main(final String[] args) {
        new NewGameView();
    }
}
