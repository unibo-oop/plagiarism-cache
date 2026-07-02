package application;

import java.io.IOException;

import controller.ControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.World;
import view.View;
import view.ViewImpl;

/**
 * The class containing the main method.
 */
public class OOPTankwar extends Application {

    @Override
    public final void start(final Stage primaryStage) throws IOException {
        final Model world = new World();
        final View view = new ViewImpl(primaryStage);
        ControllerImpl.getController().initializeController(world, view);
        view.launchView(ControllerImpl.getController());
    }

    /**
     * The main entry point of the application.
     * 
     * @param args
     *            CLI arguments.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
