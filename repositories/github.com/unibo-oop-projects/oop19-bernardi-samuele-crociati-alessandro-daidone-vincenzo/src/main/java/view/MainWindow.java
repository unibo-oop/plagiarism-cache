package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The main window of the application.
 * 
 */
public final class MainWindow extends Application {
    private static final int PROPORTION = 2;
    private static Stage pStage;
    private static Scene pScene;

    @Override
    /**
     * Starts the application.
     */
    public void start(final Stage stage) throws Exception {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/main.fxml"));
            final Parent root = loader.load();
            final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            final int sw = (int) screen.getWidth();
            final int sh = (int) screen.getHeight();
            final Scene scene = new Scene(root, sw / PROPORTION, sh / PROPORTION);
            // Stage configuration
            setPrimaryStage(stage);
            stage.getIcons().add(new Image("images/icon.png"));
            stage.setTitle("Battle Tactics");
            stage.setScene(scene);
            scene.getStylesheets().add("/style/style.css");
            stage.show();
            stage.setResizable(false);
            pStage = stage;
            pScene = scene;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return the primaryStage
     */
    public static Stage getStage() {
        return pStage;
    }

    /**
     * Sets the primary stage for the application.
     *
     * @param pStage
     *            the primary stage used in the application
     */
    private void setPrimaryStage(final Stage pStage) {
        MainWindow.pStage = pStage;
    }

    /**
     * 
     * @return Scene
     *
     */
    public static Scene getScene() {
        return pScene;
    }
}
