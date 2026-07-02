package view.controller;

import java.io.IOException;
import java.util.Locale;

import controller.Controller;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

/**
 * Controller class for the stage between two levels.
 */
public class LoadController extends ViewController {

    @FXML
    private Label playLabel;

    @FXML
    private GridPane loaderGrid;

    @Override
    public final void init(final Controller controller) {
        String nameLevel = " ";
        final String name = controller.getLevel().getCurrentLevel().getName();
        for (int i = 0; i < name.length(); i++) {
            nameLevel += name.substring(i, i + 1) + " ";
        }
        nameLevel += "!";
        this.playLabel.setText(nameLevel.toUpperCase(Locale.ENGLISH));
        final FadeTransition play = new FadeTransition(Duration.seconds(3), this.loaderGrid);
        play.setFromValue(1.0);
        play.setToValue(0.5);
        play.setOnFinished(e -> {
            try {
                ViewScenes.GAME_WORLD.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                        controller);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        play.play();
    }

}
