package application;

import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Class of the application.
 */
public final class BattleCity extends Application {

    /**
     * Main method.
     * 
     * @param args the arguments passed at the main.
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        // Create and initialize the controller.
        final Controller controller;
        controller = new ControllerImpl(primaryStage);
        controller.init();
    }

}
