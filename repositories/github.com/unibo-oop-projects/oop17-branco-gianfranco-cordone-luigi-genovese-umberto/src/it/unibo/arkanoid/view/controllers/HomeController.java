package it.unibo.arkanoid.view.controllers;

import it.unibo.arkanoid.view.SubView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The Controller class of Home Scene.
 *
 */
public class HomeController extends SubViewController {

    @FXML
    private Button audioButton;

    @FXML
    private void playClicked() { //NOPMD
        this.getController().next();
    }

    @FXML
    private void scoresClicked() { //NOPMD
        this.getView().setSubView(SubView.SCORES);
    }

    @FXML
    private void exitClicked() { //NOPMD
        System.exit(0);
    }

    @FXML
    private void audioButtonClicked() { //NOPMD
        if (this.getView().getSongLoop().isPaused()) {
            audioButton.setText("Audio OFF");
            this.getView().getSongLoop().resume();
        } else {
            audioButton.setText("Audio ON");
            this.getView().getSongLoop().pause();
        }
    }

}
