package it.unibo.aknightstale.views;

import io.github.palexdev.materialfx.controls.MFXTextField;
import it.unibo.aknightstale.controllers.interfaces.GameFinishedController;
import it.unibo.aknightstale.views.factories.Alert;
import it.unibo.aknightstale.views.interfaces.GameFinishedView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameFinishedViewImpl extends BaseView<GameFinishedController> implements GameFinishedView {
    @FXML
    private Label scoreLabel;

    @FXML
    private MFXTextField nameTextField;

    public GameFinishedViewImpl() {
        super("Game finished");
    }

    /**
     * Action when save button is clicked. Saves the score.
     */
    @FXML
    public void onSaveButtonClicked() {
        if (nameTextField.getText().isEmpty()) {
            Alert.showAlert(AlertType.ERROR, "Please enter a name to save the score.");
        } else {
            this.getController().saveScore(this.nameTextField.getText());
        }
    }

    /**
     * Action when main menu button is clicked. Shows the main menu view.
     */
    @FXML
    public void onMainMenuButtonClicked() {
        this.getController().showMainMenu();
    }

    /**
     * Action when scoreboard button is clicked. Shows the scoreboard view.
     */
    @FXML
    public void onScoreboardButtonClicked() {
        this.getController().showScoreboard();
    }

    /**
     * Action when the new game button is clicked. Starts a new game.
     */
    public void onNewGameButtonClicked() {
        this.getController().startNewGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScoreLabel(final int score) {
        this.scoreLabel.setText(String.valueOf(score));
    }
}
