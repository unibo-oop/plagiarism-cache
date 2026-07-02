package magazzino.entratamerci;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import magazzino.entratamerci.controller.StorageController;

public class MainMenuClass extends Application {
	private Stage primaryStage;
    private Pane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestione Magazzino");

        initRootLayout();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	public void initRootLayout(){
		 try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            		
	            //loader.setLocation(new File(StorageController.getFileMenu()).toURI().toURL());
		    loader.setLocation(MainMenuClass.class.getResource(StorageController.getFileMenu()));
	            rootLayout =  loader.load();
	            
	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
