package vg.view.transition;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import vg.view.ViewController;

public class TransitionViewController extends ViewController {
    @FXML
    private Text level;

    @FXML
    private Text score;

    @FXML
    private Text timeout;

    public void setLevel(final int level) {
        this.level.setText(String.valueOf(level));
    }

    public void setScore(final int currentScore) {
        this.score.setText(String.valueOf(currentScore));
    }

    public void setCountdown(final int time) {
        this.timeout.setText(String.valueOf(time));
    }
}
