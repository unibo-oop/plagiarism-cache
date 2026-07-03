package view.controller;

import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import view.ViewManagerImpl;
import view.util.StateFactoryImpl;

/**
 * Controller of the MenuScreen FXML file.
 */
public class MenuSceneControllerImpl extends AbstractControllerFXML implements MenuSceneController {

    @FXML private BorderPane container;
    @FXML private Button newGame;
    @FXML private Button loadGame;
    @FXML private Button optionsButton;
    @FXML private Button creditsButton;
    @FXML private Button helpButton;
    @FXML private Button exitGame;

    @FXML
    private void creditsButtonPressed() {
        ViewManagerImpl.get().pushState(StateFactoryImpl.get().createCreditsState());
    }

    @FXML
    private void helpButtonPressed() {
        ViewManagerImpl.get().pushState(StateFactoryImpl.get().createHelpState());
    }

    @FXML
    private void loadGamePressed() {
        ViewManagerImpl.get().pushState(StateFactoryImpl.get().createGameState());
    }

    @FXML
    private void newGamePressed() {
        ViewManagerImpl.get().pushState(StateFactoryImpl.get().createGameState());
    }

    @FXML
    private void optionsButtonPressed() {
        ViewManagerImpl.get().pushState(StateFactoryImpl.get().createOptionsState());
    }

    @FXML
    private void initialize() {
        super.setButtonNotification(
                Arrays.asList(newGame, loadGame, optionsButton, creditsButton, helpButton, exitGame), "MENU");
    }

    @Override
    public Region getRoot() {
        return container;
    }

    @Override
    public void checkLoadButton() {
        this.loadGame.setDisable(!ViewManagerImpl.get().isGameRunning());
    }
}
