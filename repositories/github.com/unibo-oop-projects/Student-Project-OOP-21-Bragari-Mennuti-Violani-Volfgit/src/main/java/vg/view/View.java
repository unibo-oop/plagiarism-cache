package vg.view;

import javafx.scene.Scene;
import vg.controller.Controller;

/**
 * View interface.
 * @param <T> Controller associated to the FXML file.
 */
public interface View<T> {
    /**
     * Return SceneController that receive key input and interact with domain of this view.
     * @return {@link SceneController}
     */
    SceneController getSceneController();

    /**
     * Set logic controller of view that manages input event and call updates method of view.
     * @param sceneController controller that controls this view.
     */
    void setSceneController(SceneController sceneController);

    T getViewController();

    /**
     * Get javaFX scene of this view.
     * @return {@link Scene}
     */
    Scene getScene();
}
