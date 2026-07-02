package view;

import java.io.IOException;

import javafx.stage.Stage;
import view.sceneloader.SceneLoader;

/**
 *
 * Chiara Tarantino.
 *
 */
public interface View {
    /**
     *
     * @return the primary stage
     */
    Stage getPrimaryStage();

    /**
     *
     * @return the scene loader
     */
    SceneLoader getSceneLoader();

    /**
     *
     * @return the secondary stage used for the rulebook
     */
    Stage getSecondaryStage();

    /**
     *
     * @param primaryStage
     *            primary stage
     * @throws IOException
     *             if an I/O error occurs
     */
    void start(Stage primaryStage) throws IOException;

}
