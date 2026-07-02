package controllers.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import util.IOName;

/**
 * Class that control endgamemenu.fxml.
 */
public class EndGameViewController {

    @FXML
    private TextArea nameArea;

    private final IOName ioName;

    public EndGameViewController() {
        ioName = new IOName();
    }



    /**
     * When user click the Submit button, the name insert by the user is saved and the scene switch to main.fxml.
     * @param event Action event of the button
     * @throws IOException IO exception
     */
    @FXML
    public final void saveName(final ActionEvent event) throws IOException {
        final String name = nameArea.getText();
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/main.fxml"));
        final Scene scene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        printUserName(name);

    }

    private void printUserName(final String name) throws IOException {
        ioName.writeName(name);
    }

}
