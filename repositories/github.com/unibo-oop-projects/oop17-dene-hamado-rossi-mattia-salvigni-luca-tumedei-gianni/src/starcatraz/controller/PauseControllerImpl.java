package starcatraz.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import starcatraz.controller.game.GamePopupController;
import starcatraz.util.AppSound;

/**
 * Pause controller implementation.
 */
public class PauseControllerImpl extends GamePopupController implements Initializable, PauseController {

    private long pauseTime; 

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.pauseTime = System.currentTimeMillis();
    }

    @Override
    public void resumeButtonClick(final ActionEvent event) {
        AppSound.GAME_MUSIC.play();
        this.pauseTime = System.currentTimeMillis() - this.pauseTime;
        getGameController().setPauseTime(getGameController().getPauseTime() + this.pauseTime);
        getGameController().hidePopupStage();
    }

    @Override
    public void backButtonClick(final ActionEvent event) {
        getGameController().backButtonClick(null);
        getGameController().hidePopupStage();
    }
}
