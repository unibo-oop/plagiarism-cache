package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.model.game.impl.ScoreRecord;
import it.unibo.roguekong.view.RogueKongView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * ScoreView manages the Score page rendering.
 */

public class ScoreView implements RogueKongView {
    private final Scene scene;
    private Runnable onReturn;
    private Runnable onClearScores;

    private Label score1;
    private Label score2;
    private Label score3;

    /**
     * Creates the ScoreView layout and renders score labels and control buttons
     */
    public ScoreView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        this.score1 = new Label("-");
        this.score2 = new Label("-");
        this.score3 = new Label("-");

        score1.getStyleClass().add("box1");
        score2.getStyleClass().add("box2");
        score3.getStyleClass().add("box3");

        score1.setMinSize(150, 50);
        score2.setMinSize(150, 50);
        score3.setMinSize(150, 50);

        Button clearScoreButton = new Button("Clear score");
        clearScoreButton.setStyle("""
                -fx-background-color: red;
                """
        );
        Button returnButton = new Button("Return");

        clearScoreButton.setOnAction(e -> runIfNotNull(onClearScores));
        returnButton.setOnAction(e -> runIfNotNull(onReturn));

        root.getChildren().addAll(score1, score2, score3, clearScoreButton, returnButton);
        this.scene = new Scene(root, 800, 600);
        this.scene.getStylesheets().add(
                getClass().getResource("/css/menu.css").toExternalForm()
        );
    }

    /**
     * Formats how the score is viewed in the Score page
     * @param scores are the points the player obtained during the completion of the level
     * @param index represents its position on the scoreboard
     * @return if the score is empty on the line, returns '-', if not returns the following string:
     * 'Player - 1000'
     */
    private String format(List<ScoreRecord> scores, int index){
        if(scores.size() <= index){
            return "-";
        }
        ScoreRecord s = scores.get(index);
        return s.name() + " - " + s.score();
    }

    /**
     * Loads the scores at the index position given
     */
    public void setScores(List<ScoreRecord> scores){
        score1.setText(format(scores, 0));
        score2.setText(format(scores, 1));
        score3.setText(format(scores, 2));
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    public void setOnClearScores(Runnable r){
        this.onClearScores = r;
    }

    public void setOnReturn(Runnable r) {
        this.onReturn = r;
    }

    private void runIfNotNull(Runnable r) {
        if (r != null) r.run();
    }
}