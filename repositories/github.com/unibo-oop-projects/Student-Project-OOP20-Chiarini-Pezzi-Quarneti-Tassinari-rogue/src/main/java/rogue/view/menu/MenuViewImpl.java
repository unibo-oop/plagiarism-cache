package rogue.view.menu;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import rogue.controller.GameController;

/**
 * An implementation for a {@link MenuView}.
 */
public class MenuViewImpl implements MenuView {

    private static final String NO_NAME_MESSAGE = "PLEASE ENTER A NAME";
    private static final String INVALID_NAME_MESSAGE = "PLEASE ENTER A VALID NAME";
    private static final String VALID_NAME = "ENTERING DUNGEON ...";
    private static final int NAME_MAX_LENGTH = 15;

    @FXML private Label insertNameLabel;
    @FXML private TextField nameTextField;
    private GameController controller;

    /**
     * @param controller for the MenuView
     */
    public void init(final GameController controller) {
        this.controller = controller;
    }

    /**
     * Checks if the name given by the user is a valid name.
     * @param name given by the user.
     * @return true if the name is valid, false otherwise.
     */
    private boolean validName(final String name) {
        if (name.length() <= NAME_MAX_LENGTH) {
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) == ' ') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Text area event to get player's name.
     * @param event to check.
     * @throws IOException 
     * @throws InventoryIsFullException 
     */
    @FXML
    public void onNameEnter(final KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (nameTextField.getText() != null && !(nameTextField.getText().isEmpty())) {
                if (validName(nameTextField.getText())) {
                    /*
                     * Valid name entered, start game.
                     */
                    insertNameLabel.setText(VALID_NAME);
                    controller.showGame();
                    final var window = nameTextField.getScene().getWindow();
                    if (window instanceof Stage) {
                        ((Stage) window).close();
                    }
                } else {
                    insertNameLabel.setText(INVALID_NAME_MESSAGE);
                }
            } else {
                insertNameLabel.setText(NO_NAME_MESSAGE);
            }
        }
    }

}
