package view;

import java.util.Optional;

import view.controllers.FxController;

/**
 * Interface for loading the next scene.
 *
 */
public interface SceneLoader {

    /**
     * 
     * @param filePath the file path of the next scene.
     * @return the {@link AbstractSceneController} related to the next scene.
     */
    Optional<FxController> loadScene(String filePath);

}
