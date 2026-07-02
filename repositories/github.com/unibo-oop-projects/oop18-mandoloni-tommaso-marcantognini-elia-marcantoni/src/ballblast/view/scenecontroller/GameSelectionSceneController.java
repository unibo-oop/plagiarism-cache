package ballblast.view.scenecontroller;

import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The SceneController for the game mode selection scene.
 * 
 */
public class GameSelectionSceneController extends AbstractSubSceneController {

    @FXML
    private Button btnSurvivalMode;
    @FXML
    private Button btnBackToMenu;
    /**
    * Method is used when the user clicks the "SURVIVAL" button.
    * It is handled from JavaFX GameSelection.fxml file.
    */
    @FXML
    private void startSurvivalMode() { //NOPMD the method is injected by fxml.
        this.getController().startSurvivalMode();
        this.nextScene();
    }

    @Override
    public final GameScenes getNextScene() {
        return GameScenes.GAME;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MENU;
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.startSurvivalMode();
        }
    }

}
