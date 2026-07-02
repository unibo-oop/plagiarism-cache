package it.unibo.arkanoid.view.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * 
 * The controller for the win or game over scenes.
 *
 */
public class EndGameController extends SubViewController {

    @FXML
    private Label endGameMessage;
    @FXML
    private TextField nameField;

        @FXML
    private void continueClicked() { //NOPMD
        this.getController().saveScore(nameField.getText().trim().isEmpty() ? "Guest" : nameField.getText());
        this.getController().next();
    }

    /**
     * 
     * @param message
     *            message to be shown.
     */
    public void setMessage(final String message) {
        this.endGameMessage.setText(message);
    }
}
