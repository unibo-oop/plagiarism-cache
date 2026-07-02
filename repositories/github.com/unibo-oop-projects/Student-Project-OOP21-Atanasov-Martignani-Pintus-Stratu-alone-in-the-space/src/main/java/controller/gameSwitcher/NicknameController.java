package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * 
 */
public class NicknameController extends BasicFXMLController {

    private static final int MAX_SIZE = 15;

    private Integer maxNameLength;
    @FXML
    private TextField nickname;

    /**
     * Constructor.
     *
     * @param sceneController
     * @throws IOException
     */
    public NicknameController(SceneController sceneController) throws IOException {
        super(sceneController);
        this.maxNameLength = MAX_SIZE;
    }

    /**
     * Checks the nickname entry and save it.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void nameEvent(final ActionEvent event) throws IOException {
        final String name = this.nickname.getText();
        if (!name.isBlank() && name.length() <= this.maxNameLength) {
            // super.buttonPressedSound();
            super.getSceneController().switchToGame(name);
        }
    }

    /**
     * Returns to the main menu.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void showMainMenu(final ActionEvent event) throws IOException {
        // super.buttonPressedSound();
        super.getSceneController().switchToMainMenu();
    }

}
