package application;


import controller.Controller;
import controller.ControllerImpl;
import controller.users.InstallManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Battleships extends Application {

    private static Controller controller;

    @Override
    public void start(final Stage stage) throws Exception {
        InstallManager.setupApplication();
        controller = new ControllerImpl(stage);
    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

    /**
     * @return the application's controller from an mvc standpoint.
     */
    public static Controller getController() {
        return controller; 
    }

}
