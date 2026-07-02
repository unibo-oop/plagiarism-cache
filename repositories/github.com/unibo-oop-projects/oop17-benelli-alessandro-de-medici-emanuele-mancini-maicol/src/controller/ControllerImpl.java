package controller;

import javafx.application.Application;
import javafx.application.Platform;
import view.MainWindow;

/**
 * Class that manages the application's launch and quit.
 *
 */
public final class ControllerImpl implements Controller {

    private static boolean unix;

    /**
     * Main method.
     * 
     * @param args
     *            the command line arguments
     */
    public static void main(final String[] args) {
        unix = System.getProperty("os.name").contains("nux");
        Application.launch(MainWindow.class, args);
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    @Override
    public boolean isUnix() {
        return unix;
    }
}
