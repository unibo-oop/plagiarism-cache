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
 * Controller class for the ending level.
 */
public class EndLevelController extends ViewController {

    @FXML
    private GridPane endLevelGrid;

    @Override
    public final void init(final Controller controller) {
        final FadeTransition endLevel = new FadeTransition(Duration.seconds(3), this.endLevelGrid);
        endLevel.setFromValue(1.0);
        endLevel.setToValue(0.5);
        endLevel.setOnFinished(e -> {
            try {
                ViewScenes.LOADING.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                        controller);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        endLevel.play();
    }

}
