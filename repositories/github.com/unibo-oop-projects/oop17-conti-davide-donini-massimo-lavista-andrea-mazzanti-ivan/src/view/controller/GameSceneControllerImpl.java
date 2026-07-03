package view.controller;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import model.data.GameData;
import view.utilities.DataConversions;

/**
 * 
 * Implements the game scene.
 *
 */
public final class GameSceneControllerImpl extends AbstractSceneController implements GameSceneController {

    private static final int SECONDS_IN_A_MINUTE = 60;

    @FXML
    private Canvas canvas;

    @FXML
    private Label life, damage, rateOfFire, shield, kills, score, time;

    @Override
    public Canvas getCanvas() {
        return this.canvas;
    }

    @Override
    public void setGameData(final GameData gameData) {
        this.life.setText(String.valueOf(gameData.getLife()));
        this.damage.setText(String.format("%.2f", gameData.getDamage()));
        this.rateOfFire.setText(String.format("%.2f", gameData.getRateOfFire() / SECONDS_IN_A_MINUTE) + " RPS");
        this.shield.setText(gameData.isDefensePowerUpActive() ? "ON" : "OFF");
        this.kills.setText(String.valueOf(gameData.getNumDeadEemies()));
        this.score.setText(String.valueOf(gameData.getScore()));
        this.time.setText(DataConversions.getTimeFromMilliseconds(gameData.getTimer()));
    }

    @Override
    public Optional<String> askPlayerName() {
        final TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Game Over");
        dialog.setHeaderText("Congratulations, that's a new high score");
        dialog.setContentText("Please enter your name:");
        return dialog.showAndWait();
    }

}
