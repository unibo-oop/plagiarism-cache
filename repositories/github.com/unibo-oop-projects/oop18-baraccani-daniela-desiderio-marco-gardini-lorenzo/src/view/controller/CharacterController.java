package view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.viewmanager.ViewManager;

/**
 * CharacterController to control Character scene and allow player to insert
 * Character's name. It uses items from javafx.
 */
public class CharacterController extends AbstractController implements Initializable {

    private static final int MAX_CHARS = 15;
    @FXML
    private TextArea nameArea;
    @FXML
    private Button go;

    /**
     * Initialize the CharacterController.
     * 
     * @param loader the {@link ViewManager} shared by all the controllers.
     */
    public CharacterController(final ViewManager loader) {
        super(loader);
    }

    /**
     * Set max characters for player's name.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.nameArea.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getText().contains("\n")) {
                change.setText(change.getText().replace("\n", ""));
            }
            return change.getControlNewText().length() <= MAX_CHARS ? change : null;
        }));

        this.nameArea.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                this.startGame();
            }
        });
    }

    /*
     * Start a new Game opening the GameView.
     */
    @FXML
    private void startGame() {
        final String playerName = this.nameArea.getText().trim();
        if (playerName.equals("")) {
            this.getViewManager().getView().getController().handleError("Please, insert your name!", false);
        } else {
            this.getViewManager().openGameScene(playerName);
        }
    }

}
