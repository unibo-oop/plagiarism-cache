package view.controller;

import java.io.IOException;

import controller.Controller;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

/**
 * Controller class for the ending game.
 */
public class EndGameController extends ViewController {

    @FXML
    private GridPane endGameGrid;

    @Override
    public final void init(final Controller controller) {
        final FadeTransition endGame = new FadeTransition(Duration.seconds(3), this.endGameGrid);
        endGame.setFromValue(1.0);
        endGame.setToValue(0.5);
        endGame.setOnFinished(e -> {
            try {
                ViewScenes.MENU.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                        controller);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        endGame.play();
    }

}
