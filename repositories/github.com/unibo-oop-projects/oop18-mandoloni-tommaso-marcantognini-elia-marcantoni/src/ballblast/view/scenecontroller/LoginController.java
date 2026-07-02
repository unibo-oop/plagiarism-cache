package ballblast.view.scenecontroller;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;
import ballblast.controller.Controller;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * The SceneController for login scene.
 * 
 */
public class LoginController extends AbstractSceneController {

    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField pswTextField;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnLogin;
    private static final String DANGER = "DANGER";

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        Platform.runLater(() -> this.userTextField.requestFocus());
    }

    @Override
    public final GameScenes getNextScene() {
        return GameScenes.MENU;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MAIN;
    }

    /**
     * @throws SAXException                 SAXException
     * @throws IOException                  IOException
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws XPathExpressionException     XPathExpression exception
     */
    @FXML
    public void userLogin() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        if (checkTextField()) {
            if (this.getController().checkLoginUser(userTextField.getText(), pswTextField.getText())) {
                this.nextScene();
            } else {

                final Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(DANGER);
                alert.setHeaderText(null);
                alert.setContentText("Data not correct");
                alert.showAndWait();
            }
        } else {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(DANGER);
            alert.setHeaderText(null);
            alert.setContentText("Insert data.");
            alert.showAndWait();
        }
    }

    /**
     * @throws SAXException                 SAXException
     * @throws TransformerException         TransformerException
     * @throws IOException                  IOException
     * @throws ParserConfigurationException ParserConfigurationException
     */
    @FXML
    public void userRegister() throws ParserConfigurationException, IOException, TransformerException, SAXException {
        if (checkTextField()) {
            if (this.getController().checkRegisterUser(userTextField.getText(), pswTextField.getText())) {
                this.nextScene();
            } else {
                final Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(DANGER);
                alert.setHeaderText(null);
                alert.setContentText("User already exists.");
                alert.showAndWait();
            }
        } else {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(DANGER);
            alert.setHeaderText(null);
            alert.setContentText("Insert data.");
            alert.showAndWait();
        }
    }

    private boolean checkTextField() {
        if (this.userTextField.getText().equals("")) {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(DANGER);
            alert.setHeaderText(null);
            alert.setContentText("Insert a user name");
            alert.showAndWait();
        } else if (this.pswTextField.getText().equals("")) {
            final Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(DANGER);
            alert.setHeaderText(null);
            alert.setContentText("Insert a password");
            alert.showAndWait();
        } else {
            return true;
        }
        return true;

    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                this.userLogin();
            } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
                e.printStackTrace();
            }
        }
    }

}
