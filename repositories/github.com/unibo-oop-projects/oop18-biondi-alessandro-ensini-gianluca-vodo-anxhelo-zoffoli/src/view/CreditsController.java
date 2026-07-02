package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.CreditsHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * View controller of Credits window .
 */
public class CreditsController implements Initializable {

    private CreditsHandler creditsHandler;

    @FXML
    private Button buttonClose;

    /**
     * Close the credits window .
     */
    public void cancel() {
        creditsHandler.closeCredits();
    }

    /**
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     *      java.util.ResourceBundle)
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        creditsHandler = new CreditsHandler();
    }
}
