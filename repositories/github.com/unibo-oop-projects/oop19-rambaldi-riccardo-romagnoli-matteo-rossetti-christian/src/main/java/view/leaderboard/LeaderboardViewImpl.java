package view.leaderboard;

import controller.base.BaseController;
import controller.leaderboard.LeaderboardController;
import controller.leaderboard.LeaderboardControllerImpl;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This is the implementation of {@link LeaderboardView} interface.
 */
public class LeaderboardViewImpl implements LeaderboardView {
    private final LeaderboardController leaderboardController;
    private final Stage stage;
    private final Pane pane;
    private final Button goBackButton;
    private final TextArea textarea;

    /**
     * LeaderboardView's constructor.
     * 
     * @param baseController
     *                           The instance of BaseController.
     * @param stage
     *                           The main stage.
     */
    public LeaderboardViewImpl(final BaseController baseController, final Stage stage) {
        this.leaderboardController = new LeaderboardControllerImpl(baseController);
        this.stage = stage;
        this.pane = new Pane();
        this.stage.getScene().setRoot(this.pane);
        this.goBackButton = new Button("Back");
        this.textarea = new TextArea();

    }

    @Override
    public final void show() {
        this.pane.getChildren().addAll(this.textarea, this.goBackButton);
        this.goBackButton.setOnMouseClicked(e -> this.leaderboardController.goBackHit());
        this.showLeaderboard();
        this.setupElementsPosition();

    }

    /**
     * Puts the elements in a fixed position.
     */
    private void setupElementsPosition() {
        this.textarea.setTranslateX((this.stage.getWidth()) / 4);
        this.textarea.setPrefHeight(this.stage.getHeight() - this.stage.getHeight() * 10 / 100);
        this.textarea.setPrefWidth(this.stage.getWidth() / 2);
        this.textarea.positionCaret(0);
        this.textarea.setEditable(false);
    }

    /**
     * Populates the TextArea with the scores. Shows a default message if the file
     * does not exist.
     */
    private void showLeaderboard() {
        if (this.leaderboardController.isFileThere()) {
            this.leaderboardController.loadScore().forEach(s -> this.textarea.appendText(s + "\n"));

        } else {
            this.textarea.appendText("no scores yet");
        }
    }

}
