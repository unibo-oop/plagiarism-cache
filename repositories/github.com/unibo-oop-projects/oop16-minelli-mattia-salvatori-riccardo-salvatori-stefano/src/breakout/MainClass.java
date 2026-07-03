package breakout;

import breakout.view.MainWindow;
import javafx.application.Application;

/**
 * Main class of the application.
 */
public final class MainClass {

    private MainClass() {
    }

    /**
     * Launch application.
     * 
     * @param args
     *            args
     */
    public static void main(final String[] args) {

        Application.launch(MainWindow.class, args);
    }

}
