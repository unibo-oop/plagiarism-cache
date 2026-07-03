package view.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import view.ViewManagerImpl;

/**
 * Controller of the HelpScreen FXML file.
 */
public class HelpSceneController extends AbstractControllerFXML {

    @FXML private BorderPane contents;

    @FXML
    private void backButtonPressed() {
        super.closeAnimation(() -> ViewManagerImpl.get().popState());
    }

    @Override
    public Region getRoot() {
        return this.contents;
    }

}
