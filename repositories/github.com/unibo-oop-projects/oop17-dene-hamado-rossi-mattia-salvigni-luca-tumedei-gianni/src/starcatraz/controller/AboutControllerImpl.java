package starcatraz.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import starcatraz.util.AppFXML;

/**
 * Credits controller implementation.
 */
public class AboutControllerImpl extends StarcatrazController implements Initializable, AboutController {

    @FXML
    private AnchorPane aboutRoot;
    @FXML
    private ScrollPane creditsScrollPane;
    @FXML
    private AnchorPane creditsAnchorPane;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.creditsAnchorPane = loadCredits();
        this.creditsScrollPane.setContent(this.creditsAnchorPane);
    }

    /**
     * @return AnchorPane to show credits of Starcatraz
     */
    private AnchorPane loadCredits() {
        AnchorPane pane = new AnchorPane();
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(AppFXML.CREDITS));
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    @Override
    public void closeCreditsButtonClick() {
        aboutRoot.setVisible(false);
    }
}
