package application;

import it.lttply.controller.HomeControllerConcrete;

/**
 * Class which has only one mission: starting the application.
 */
public final class Main {

    private Main() {
    }

    /**
     * The main method which starts the application.
     *
     * @param args
     *            the arguments to be passed (ignored)
     */
    public static void main(final String[] args) {
        new HomeControllerConcrete();
        System.err.close();
    }

}
