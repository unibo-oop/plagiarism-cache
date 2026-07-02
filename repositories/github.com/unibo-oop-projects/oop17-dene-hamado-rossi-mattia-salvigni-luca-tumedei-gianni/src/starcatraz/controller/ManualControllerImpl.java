package starcatraz.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import starcatraz.util.AppFXML;

/**
 * Manual controller implementation.
 */
public class ManualControllerImpl extends StarcatrazController implements Initializable,  ManualController {

    @FXML
    private AnchorPane manualRoot;
    @FXML
    private Button itButton;
    @FXML
    private Button enButton;
    @FXML
    private ScrollPane manualScrollPane;

    private AnchorPane manual;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.manual = loadManual(AppFXML.MANUAL_EN);
        this.manualScrollPane.setContent(manual);
        enButton.setDisable(true);
    }

    @Override
    public void buttonClickEvent(final ActionEvent event) {
        if (event.getTarget() == itButton) {
            manual = this.loadManual(AppFXML.MANUAL_IT);
            itButton.setDisable(true);
            enButton.setDisable(false);
        } else if (event.getTarget() == enButton) {
            manual = this.loadManual(AppFXML.MANUAL_EN);
            itButton.setDisable(false);
            enButton.setDisable(true);
        }
        manualScrollPane.setContent(manual);
        manualScrollPane.vvalueProperty().set(0);
        manualScrollPane.setVisible(true);
    }

    /**
     * @param path
     * @return AnchorPane of the manual
     */
    private AnchorPane loadManual(final String path) {
        AnchorPane pane = new AnchorPane();
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource(path)));
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    @Override
    public void closeManualButtonClick() {
        manualRoot.setVisible(false);
    }
}
