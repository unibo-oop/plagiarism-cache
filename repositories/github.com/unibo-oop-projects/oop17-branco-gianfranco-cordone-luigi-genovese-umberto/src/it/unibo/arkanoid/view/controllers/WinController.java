package it.unibo.arkanoid.view.controllers;

import javafx.fxml.FXML;

/**
 * 
 * The controller for the scene shown after the player wins a level.
 *
 */
public class WinController extends SubViewController {

    @FXML
    private void continueClicked() { //NOPMD
        this.getController().next();
    }

}
