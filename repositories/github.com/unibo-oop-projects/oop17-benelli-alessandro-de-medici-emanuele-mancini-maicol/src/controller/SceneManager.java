package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class that manages application's stage and scenes.
 *
 */
public class SceneManager {

    private static final SceneManager SINGLETON = new SceneManager();
    private Stage applicationStage;

    /**
     * Sets a scene in the stage.
     * 
     * @param scene
     *            to be set in the stage
     */
    public void sceneSetter(final Scene scene) {
        this.applicationStage.setScene(scene);
    }

    /**
     * Setter of applicationStage.
     * 
     * @param stage
     *            to be set in the application
     */
    public void setApplicationStage(final Stage stage) {
        this.applicationStage = stage;
    }

    /**
     * Returns an instance of SceneManager.
     * 
     * @return an instance of SceneManager
     */
    public static SceneManager get() {
        return SINGLETON;
    }
}
