package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.View;
import view.ViewImpl;

/**
 * App launcher.
 */
public class ApplicationLauncher extends Application {

    /**
     * Starts the application.
     * @param args additional command-line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * JavaFX application starting point.
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final View view = new ViewImpl(primaryStage);
        view.startMenu();
    }
}
