package controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * Application performer.
 *
 */
public class Main extends Application {

    /**
     * Entry point of Application.
     * 
     * @param args Command-line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     */
    @Override
    public void start(final Stage primaryStage) {
        new MainControllerImpl();
    }
}
