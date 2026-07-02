package it.unibo;

import it.unibo.controller.app.impl.MainControllerImpl;
import it.unibo.view.app.api.MainView;
import it.unibo.view.app.impl.MainViewImpl;

/**
 * Main class of the application.
 */
public final class JavaClimber {

    /**
     * private constructor.
     */
    private JavaClimber() {
    }

    /**
     * Main method that starts the application.
     * 
     * @param args ignore
     */
    public static void main(final String... args) {
        final MainView mainView = new MainViewImpl();
        new MainControllerImpl(mainView);
    }

}
