package rogue;

import java.io.IOException;

import rogue.view.Launcher;

/**
 * Class that invokes {@link Launcher}.
 *
 */
public final class App {

    private App() { }

    /**
     * 
     * @param args unused.
     */
    public static void main(final String[] args) throws IOException {
        Launcher.main(args);
    }

}
