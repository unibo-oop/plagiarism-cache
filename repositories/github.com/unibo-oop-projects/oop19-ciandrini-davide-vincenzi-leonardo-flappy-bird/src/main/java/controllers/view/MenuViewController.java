package controllers.view;

import java.io.IOException;

import controllers.FlappyBirdControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * The Controller related to the main.fxml GUI.
 *
 */
public final class  MenuViewController {

    /**
     * When user click the Contacts button the scene switch to contacts.fxml.
     * @param event Action event of the button
     * @throws IOException IO exception
     */
    @FXML
    public void clickContacts(final ActionEvent event) throws IOException {

        final Parent contacts = FXMLLoader.load(ClassLoader.getSystemResource("layouts/contacts.fxml"));
        final Scene contactsScene = new Scene(contacts);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(contactsScene);
        window.show();

    }

    /**
     * When user click the Leaderboard button the scene switch to scoreboard.fxml.
     * @param event Action event of the button
     * @throws IOException IO exception
     */
    public void clickLeaderboard(final ActionEvent event) throws IOException {

        final Parent leaderboard = FXMLLoader.load(ClassLoader.getSystemResource("layouts/scoreboard.fxml"));
        final Scene leaderboardScene = new Scene(leaderboard);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(leaderboardScene);
        window.show();

    }

    /**
     * When user click the Play button the game start.
     * @param event Action event of the button
     */
    @FXML
    public void clickPlay(final ActionEvent event) {

        final Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new FlappyBirdControllerImpl(primaryStage);
    }
}




