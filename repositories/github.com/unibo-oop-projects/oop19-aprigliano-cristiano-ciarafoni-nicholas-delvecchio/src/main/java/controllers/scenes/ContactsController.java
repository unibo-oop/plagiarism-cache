package controllers.scenes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class ContactsController {

    /**
     * Whenever the user click the Home button, the scene switch to Home.fxml.
     * @param event the event
     * @throws IOException
     */
    @FXML
    private void returnMainMenu(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/Home.fxml"));
        final Scene menuScene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.show();
    }
}
