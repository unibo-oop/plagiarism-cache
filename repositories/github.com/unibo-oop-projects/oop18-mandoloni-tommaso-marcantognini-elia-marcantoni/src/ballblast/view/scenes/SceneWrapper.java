package ballblast.view.scenes;

import ballblast.view.scenecontroller.AbstractSceneController;
import javafx.scene.Scene;

/**
 * A simple wrapper for {@link Scene}.
 */
public interface SceneWrapper {
    /**
     * 
     * @return the {@link Scene}.
     */
    Scene getScene();

    /**
     * 
     * @return the SceneController.
     */
    AbstractSceneController getController();
}
