package starcatraz.controller.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import starcatraz.util.AppSound;

/**
 * Implementation of GameEndController.
 */
public class GameEndControllerImpl extends GamePopupController implements Initializable, GameEndController {

    @Override
    public void initialize(final URL location, final ResourceBundle resources) { }

    @Override
    public void backButtonClick(final ActionEvent event) {
        AppSound.VICTORY_MUSIC.stop();
        AppSound.DEFEAT_MUSIC.stop();
        getGameController().backButtonClick(event);
        getGameController().hidePopupStage();
    }

    @Override
    public void restartButtonClick(final ActionEvent event) {
        AppSound.VICTORY_MUSIC.stop();
        AppSound.DEFEAT_MUSIC.stop();
        AppSound.GAME_MUSIC.play();
        getGameController().restartButtonClick(event);
        getGameController().hidePopupStage();
    }
}
