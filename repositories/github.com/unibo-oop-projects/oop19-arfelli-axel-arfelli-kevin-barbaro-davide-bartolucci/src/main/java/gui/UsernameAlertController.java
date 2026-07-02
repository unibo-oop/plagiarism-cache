package gui;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.fxml.*;
import javafx.stage.*;
import resourcemanager.ResourceManagerAlpha;

public class UsernameAlertController {

    @FXML
    private Button CONTINUE = new Button();
    
    @FXML
    private Button CANCEL = new Button();
    
    private static boolean hasContinued;
    
    // shows alert window, warning the player a username was not selected
    public static void display() throws IOException {
        try {
            Parent alertRoot = FXMLLoader.load(ResourceManagerAlpha.getIstance().getFXMLFileURL("UsernameAlert.fxml").get());
            Stage alert = new Stage();
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setScene(new Scene(alertRoot));
            alert.setResizable(false);
            alert.showAndWait();
        }
        catch(IOException e) {
            System.exit(0);
        }
    }
    
    // lets the player continue without selecting a username
    public void continueHandler() {
        hasContinued = true;
        Stage stage = (Stage) CONTINUE.getScene().getWindow();
        stage.close();
    }
    
    // closes the alert window
    public void close() {
        Stage stage = (Stage) CANCEL.getScene().getWindow();
        hasContinued = false;
        stage.close();
    }
    
    // returns true if the player wants to continue without a username (scores will not be saved), false otherwise
    public static boolean getFlag() {
        return hasContinued;
    }
    
}