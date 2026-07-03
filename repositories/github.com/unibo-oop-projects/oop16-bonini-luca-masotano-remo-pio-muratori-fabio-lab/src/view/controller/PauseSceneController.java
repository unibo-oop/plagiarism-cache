package view.controller;

import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import view.ViewManagerImpl;
import view.util.StateFactoryImpl;

/**
 * Controller of the PauseScreen FXML file.
 */
public class PauseSceneController extends AbstractControllerFXML {

    @FXML private BorderPane contents;
    @FXML private Button continueButton;
    @FXML private Button exitButton;

    @FXML
    private void continueButtonPressed() {
        super.closeAnimation(() -> ViewManagerImpl.get().popState());
    }

    @FXML
    private void exitButtonPressed() {
        super.closeAnimation(() ->  ViewManagerImpl.get().pushState(StateFactoryImpl.get().createMenuState()));
    }

    @FXML
    private void initialize() {
        super.setButtonNotification(Arrays.asList(continueButton, exitButton), "PAUSE");
    }

    @Override
    public Region getRoot() {
        return this.contents;
    }
}
