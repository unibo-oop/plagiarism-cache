package view.controller;

/**
 * Controller of the MenuScreen FXML file.
 */
public interface MenuSceneController extends ControllerFXML {

    /**
     * If a loadable game is found, the relative {@link javafx.scene.control.Button}
     * is enabled, otherwise is disabled.
     */
    void checkLoadButton();
}
