package view.scenecontroller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.utilities.Scenes;

/**
 *
 * Chiara Tarantino 
 * Controller for start menu scene.
 *
 */
public class StartMenuController extends SceneControllerImpl {
    @FXML
    private Button resumeGame;

    @FXML
    private void initialize() {
        this.resumeGame.setDisable(!this.getController().savedGamePresent());
    }

    @FXML
    private void openModalityMenu() throws IOException {
        this.getSceneLoader().load(Scenes.MODALITYMENU);
    }

    @FXML
    private void openRanking() throws IOException {
        this.getSceneLoader().load(Scenes.RANKING);
    }

    @FXML
    private void openRulebook() throws IOException {
        this.getSceneLoader().load(Scenes.RULEBOOK);
    }

    @FXML
    private void quitGame() {
        Runtime.getRuntime().exit(0);
    }

    @FXML
    private void resumeGameView() throws IOException {
        this.getSceneLoader().getStage().close();
        this.getController().reopenBoardView();
    }

}
