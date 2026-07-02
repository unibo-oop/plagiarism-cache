package gui;

import java.awt.Dimension;
import java.io.IOException;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import resourcemanager.ResourceManager;
import resourcemanager.ResourceManagerAlpha;

public class Main extends Application{
    
    private ResourceManager mgr = ResourceManagerAlpha.getIstance();
    final Dimension tmpScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    public static void main(String[] args) {
        launch(args);
    }
    
    // loads main menu scene onto the primary stage
    @Override
    public void start(Stage primaryStage) {
      if(this.tmpScreenSize.getHeight()<1080 && mgr.getSettingsAsObject().getHeight()<720) {
        mgr.getSettingsAsObject().setWindowSize(860, 540);
        mgr.saveSettings(mgr.getSettingsAsObject());
      }
        try {
            Parent root = FXMLLoader.load(mgr.getFXMLFileURL("MainMenu.fxml").get());
            primaryStage.setTitle("Stardust Invaders");
            primaryStage.setScene(new Scene(root, mgr.getSettingsAsObject().getWidth(), mgr.getSettingsAsObject().getHeight()));
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    System.exit(0);
                }
           });
            primaryStage.setResizable(true);
            primaryStage.show();
        }
        catch(IOException e) {
            System.exit(0);
        }
    }

    
}