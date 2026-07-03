package utilities;

import view.View;
import view.ViewImpl;

/**
 * This is the main class.
 *
 */
public final class Multiplex {

    private Multiplex() {
    }

    /**
     * Main method.
     *
     * @param args
     *            main arguments.
     */
    public static void main(final String[] args) {
        CreateCinema.setCinema();
        final View view = new ViewImpl();
        view.startingView();
    }

}
