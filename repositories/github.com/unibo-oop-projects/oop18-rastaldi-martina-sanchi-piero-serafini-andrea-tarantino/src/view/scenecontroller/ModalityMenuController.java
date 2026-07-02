package view.scenecontroller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.utilities.Scenes;

/**
 *
 * Chiara Tarantino. 
 * Controller for modality menu scene.
 *
 */
public class ModalityMenuController extends SceneControllerImpl {
    @FXML
    private Button hedgeJumping;

    @FXML
    private void openPlayersMenu() throws IOException {
        this.getController().setEdgeJumpingMode(this.hedgeJumping.isArmed());
        this.getSceneLoader().load(Scenes.PLAYERSMENU);
    }

    @FXML
    private void openSettingsMenu() throws IOException {
        this.getSceneLoader().load(Scenes.SETTINGSMENU);
    }

    @FXML
    private void returnStartMenu() throws IOException {
        this.getSceneLoader().load(Scenes.STARTMENU);
    }

}
