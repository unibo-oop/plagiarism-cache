package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import resourcemanager.ResourceManagerAlpha;

public class Utilities {
    
    private static void alert() {
          Alert al = new Alert(Alert.AlertType.ERROR);
          al.setTitle("Missing files");
          al.setContentText("Some necessary files are missing");
          al.setHeaderText(null);
          al.showAndWait();
    }
    
    public static void load(String filename, Stage mainStage) {
        try {
            Parent root = FXMLLoader.load(ResourceManagerAlpha.getIstance().getFXMLFileURL(filename).get());
            mainStage.setScene(new Scene(root, ResourceManagerAlpha.getIstance().getSettingsAsObject().getWidth(), ResourceManagerAlpha.getIstance().getSettingsAsObject().getHeight()));
            mainStage.centerOnScreen();
        }
        catch(IOException e) {
            alert();
            System.exit(0);
        }
    }
    
}
