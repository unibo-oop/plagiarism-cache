package view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.IOScores;

import java.io.IOException;

public class EndGameImpl implements EndGame {

    private static final double FX_OBJECT_WIDTH = 250;
    private static final double FX_OBJECT_HEIGHT = 50;
    private static final int HALF = 2;
    private final FlappyBirdViewImpl view;
    private final Button quitBtn = new Button("QUIT");
    private final int userScore;
    private final IOScores ioScores = new IOScores();

    /**
     * This is the method constructor, which initiates the view, that is used for set the size of Quit button, and the score that has to be saved.
     * @param view FlappyBirdViewImpl, the principal view
     * @param userScore The score of the user
     */
    public EndGameImpl(final FlappyBirdViewImpl view, final int userScore) {
        this.view = view;
        this.userScore = userScore;
    }

    @Override
    public final void quitBtn() {
        quitBtn.setPrefSize(FX_OBJECT_WIDTH, FX_OBJECT_HEIGHT);
        quitBtn.setLayoutX(view.getWidth() / HALF - FX_OBJECT_WIDTH  / HALF);
        quitBtn.setLayoutY(view.getHeight() / HALF);
        quitBtn.setAlignment(Pos.CENTER);

        quitBtn.setOnAction(actionEvent -> {
            final Parent menu;
            try {
                menu = FXMLLoader.load(ClassLoader.getSystemResource("layouts/endgamemenu.fxml"));
                final Scene menuScene = new Scene(menu);

                final Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(menuScene);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        sendScore();
    }

    @Override
    public final Button getButton() {
        return quitBtn;
    }

    private void sendScore() {
        ioScores.writeScore(this.userScore);
    }

}
