package vg.view.gameOver;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import vg.view.ViewController;
import java.util.Optional;

public class GameOverViewController extends ViewController {

    @FXML
    private Text score;

    @FXML
    private Text round;

    @FXML
    private TextField nameField;

    @FXML
    private Text title;

    /**
     * Set score value to be showed.
     * @param score Player score.
     */
    public void setScore(final int score) {
        this.score.setText(String.valueOf(score));
    }

    /**
     * Set round value to be showed.
     * @param round round when is gameover
     */
    public void setRound(final int round) {
        this.round.setText(String.valueOf(round));
    }

    /**
     * Get name typed by user.
     * @return String of name typed by user
     */
    public Optional<String> getTypedName() {
        if (this.nameField.getText().isBlank()) {
            return Optional.empty();
        } else {
            return Optional.of(this.nameField.getText());
        }
    }

    /**
     * Set Screen title.
     * @param title title to be shown instead GameOver phrase.
     */
    public void setTitle(String title) {
        this.title.setText(title);
    }
}
