package view.controller;

import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.data.HighScore;

/**
 * Implements the scene for high scores.
 */
public class HighScoresSceneControllerImpl extends AbstractSecondarySceneController
        implements HighScoresSceneController {

    @FXML
    private GridPane highScoresGridPane;

    @Override
    public final void setHighScores(final List<HighScore> highScores) {
        // sort high scores
        final List<HighScore> sortedHighScores = highScores.stream()
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore())).collect(Collectors.toList());
        // create label for each high score
        for (int i = 0; i < sortedHighScores.size(); i++) {
            final HighScore hs = sortedHighScores.get(i);
            final Label name = new Label(i + 1 + ") " + hs.getName());
            final Label score = new Label(String.valueOf(hs.getScore()));
            // (node, columnIndex, rowIndex)
            this.highScoresGridPane.add(name, 2, i + 1);
            this.highScoresGridPane.add(score, 3, i + 1);
        }
    }
}
