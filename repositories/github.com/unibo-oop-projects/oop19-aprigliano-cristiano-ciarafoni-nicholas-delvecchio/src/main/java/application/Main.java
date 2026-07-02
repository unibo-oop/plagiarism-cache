package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    private static final int SCENE_WIDTH = 600;
    private static final int SCENE_HEIGHT = 400;

    private final Stage firstStage = new Stage();

    @Override
    public void start(final Stage stage) throws IOException {

        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/Home.fxml"));
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        /* Stage configuration */
        firstStage.setTitle("Super Mario Run");
        firstStage.setScene(scene);
        firstStage.setResizable(false);
        firstStage.show();
    }

    /**
     * Main method.
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
