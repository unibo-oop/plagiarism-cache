package view.sceneloader;

import java.io.IOException;

import javafx.stage.Stage;
import view.utilities.Scenes;

/**
 *
 * Chiara Tarantino.
 * Interface that represents a scene loader.
 *
 */
public interface SceneLoader {
    /**
     *
     * @return the primary stage of the view
     */
    Stage getStage();

    /**
     *
     * @param scene
     *            the scene to load on the stage
     * @throws IOException
     *             if an I/O error occurs
     */
    void load(Scenes scene) throws IOException;
}
