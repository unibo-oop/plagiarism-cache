package view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.data.GameData;
import view.utilities.DataConversions;

/**
 * Implementation of the scene that appears after the end of the game.
 */
public class GameOverSceneControllerImpl extends AbstractSecondarySceneController implements GameOverSceneController {

    @FXML
    private Label time, kills, score;

    @FXML
    private void newGame() {
        this.getSceneFactory().openSelectModeScene();
    }

    @Override
    public final void setGameData(final GameData gameData) {
        this.time.setText(DataConversions.getTimeFromMilliseconds(gameData.getTimer()));
        this.kills.setText(String.valueOf(gameData.getNumDeadEemies()));
        this.score.setText(String.valueOf(gameData.getScore()));
    }

}
