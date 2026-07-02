package view.scenecontroller;

import java.io.IOException;

import javafx.fxml.FXML;
import view.utilities.Scenes;

/**
 * Chiara Tarantino. 
 * Controller for rulebook scene.
 *
 */
public class RulebookController extends SceneControllerImpl {

    @FXML
    private void openPage2() throws IOException {
        this.getSceneLoader().load(Scenes.RULEBOOKPAGE2);
    }

}
