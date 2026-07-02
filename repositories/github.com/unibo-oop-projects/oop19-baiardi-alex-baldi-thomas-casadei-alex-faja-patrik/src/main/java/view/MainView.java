package view;


import controller.SimulationController;
import controller.SimulationControllerImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Provides method to initialize simulation.
 */
public class MainView extends Application {

    /**
     * The main of the application.
     * @param args
     *              arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * 
     */
    @Override
    public void start(final Stage stage) {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("scene/Gui.fxml"));
        Parent root;
        try {
            root = loader.load();
            final View view = loader.getController();
            final SimulationController controller = new SimulationControllerImpl(view);
            view.setController(controller);
            view.getVirusSetup().setController(controller.getVirusManager());
            view.getSimulationView().setController(controller);
            final Scene scene = new Scene(root, 1000, 700);
            stage.setTitle("VSS - Virus Spread Simulator");
            stage.setScene(scene);
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });
            stage.show();
        } catch (Exception e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("LOADING ERROR");
            alert.show();
        }
    }


}
