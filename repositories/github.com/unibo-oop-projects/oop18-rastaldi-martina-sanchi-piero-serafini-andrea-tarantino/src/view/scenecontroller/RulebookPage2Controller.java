package view.scenecontroller;

import java.io.IOException;

import javafx.fxml.FXML;
import view.utilities.Scenes;

/**
 *
 * Chiara Tarantino.
 * Controller for page 2 of rulebook scene.
 *
 */
public class RulebookPage2Controller extends SceneControllerImpl {

    @FXML
    private void openGuideScene() throws IOException {
        this.getSceneLoader().load(Scenes.RULEBOOK);
    }

}
