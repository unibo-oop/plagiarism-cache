package controllers.scenes;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class HomeMenuController {

    /**
     * Whenever the user click to newGame button, the scene switch to InsertUsername.fxml.
     * @param event
     * @throws IOException
     */
    @FXML
    private void newGame(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/InsertUsername.fxml"));
        final Scene userScene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(userScene);
        window.show();
    }

    /**
     * Whenever the user click the Scoreboard button, the scene switch to Ranking.fxml.
     * @param event
     * @throws IOException
     */
    @FXML
    private void scoreBoard(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/Ranking.fxml"));
        final Scene scoreScene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scoreScene);
        window.show();
    }

    /**
     * Whenever the user click the Contacts button, the scene switch to Contacts.fxml.
     * @param event
     * @throws IOException
     */
    @FXML
    private void contacts(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/Contacts.fxml"));
        final Scene contactsScene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(contactsScene);
        window.show();
    }
}
