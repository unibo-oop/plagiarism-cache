package launcher;

import java.io.IOException;

/**
 * Start the application.
 *
 */
public final class Launcher {

    /**
     * Empty constructor.
     */
    private Launcher() {

    }

    /**
     * Method used to launch application.
     * @param args - args to pass for the main program.
     * @throws IOException - common input output exception.
     */
    public static void main(final String[] args) throws IOException {
        BrickBreakerEvo.main(args);
    }
}
