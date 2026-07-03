package view.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import view.ViewManagerImpl;
import view.util.StateFactoryImpl;

/**
 * Controller of the GameOverScreen FXML file.
 */
public class GameOverSceneController extends AbstractControllerFXML {

    @FXML private BorderPane contents;

    @FXML
    private void exitButtonPressed() {
        super.closeAnimation(() -> ViewManagerImpl.get().pushState(StateFactoryImpl.get().createMenuState()));
    }

    @Override
    public Region getRoot() {
        return this.contents;
    }
}
