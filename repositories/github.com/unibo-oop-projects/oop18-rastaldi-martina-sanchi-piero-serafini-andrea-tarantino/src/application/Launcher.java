package application;

import controller.ControllerImpl;
import javafx.application.Application;
import view.ViewImpl;

/**
 *
 * Launch application.
 *
 */
public final class Launcher {

    /**
     * Main.
     *
     * @param args
     *            args
     */
    public static void main(final String[] args) {

        Application.launch(ViewImpl.class, args);
        ControllerImpl.getLog();
    }

    private Launcher() {
    }

}
