package uicontrollers;

import controller.ControllerImpl;
import javafx.fxml.FXML;

/**
 * the main menu of the application.
 * 
 */
public final class MainMenuController {

    @FXML
    private void startGame() { // NOPMD
        ControllerImpl.getInstance().loadMapNames();
    }

    @FXML
    private void openMapEditor() { // NOPMD
        ControllerImpl.getInstance().getView().showEditorScreen();
    }

    @FXML
    private void openCharacterEditor() { // NOPMD
        ControllerImpl.getInstance().getView().showCharacterEditorScreen();
    }

    @FXML
    private void exitGame() { // NOPMD
        Runtime.getRuntime().exit(0);
    }
}
