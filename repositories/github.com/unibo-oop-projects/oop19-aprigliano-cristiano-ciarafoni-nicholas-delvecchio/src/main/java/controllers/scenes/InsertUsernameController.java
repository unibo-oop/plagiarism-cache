package controllers.scenes;

import controllers.SuperMarioRunControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public final class InsertUsernameController {

    private String username;

    @FXML
    private TextField name;

    /**
     * Whenever the user click the startGame button, the scene switch to a new game.
     * @param event
     */
    @FXML
    private void startGame(final ActionEvent event) throws IOException {
        username = name.getText();
        final Stage firstStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SuperMarioRunControllerImpl(firstStage, username);
    }

    /**
     * Whenever the user click the backHome button, the scene switch to Home.fxml.
     * @param event
     * @throws IOException
     */
    @FXML
    private void backHome(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/Home.fxml"));
        final Scene menuScene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.show();
    }
}
