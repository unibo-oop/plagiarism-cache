package view.controllers.minigames;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import view.controllers.AbstractSceneController;

/**
 * This abstract class implements the common aspect of
 * {@link FxMiniGameController}.
 *
 */
public abstract class AbstractMiniGameControllerFx extends AbstractSceneController implements FxMiniGameController {

    @FXML
    private Label timerLbl;

    @FXML
    private ProgressBar progressBar;

    private int initialSeconds;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInitialSeconds(final int initialSeconds) {
        this.initialSeconds = initialSeconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showTimerSeconds(final int second) {
        Platform.runLater(() -> {
            this.timerLbl.setText(Integer.toString(second));
            this.progressBar.setProgress((double) second / this.initialSeconds);
        });
    }

    protected final ProgressBar getProgressBar() {
        return this.progressBar;
    }

}
