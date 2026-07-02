package net.pokemonbt.view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import net.pokemonbt.controller.view_controller.SceneManager;
import net.pokemonbt.controller.resources.ResourceManager;

/**
 * The starting class for the View part of the MVC. Initializes the JavaFX stage
 * as the application starts.
 */
public final class ViewManager extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        try {
            Font.loadFont(
                    ResourceManager.getResourceAsInputStream("fonts/Jersey10-Regular.ttf"),
                    10);
            SceneManager.setStage(primaryStage);
            SceneManager.setScene("/layouts/menu.fxml");
        } catch (final IllegalStateException | IllegalArgumentException e) {
            Throwable cause = e.getCause();
            while (cause != null) {
                cause = cause.getCause();
            }
        }
    }

    @SuppressFBWarnings(value = "DM_EXIT", justification = "Needed to stop all threads as the window closes.")
    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }
}
