package viewFX;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Group root = new Group();
	private String language = "en_US";
	
    @Override
    public void start(Stage primaryStage) {
    		primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }
    
    public Parent createContent() {
        gotoLanguage();
        return root;
    }
    

    private void gotoLanguage() {
        try {
            SelectLanguageController ctrlSL =
                (SelectLanguageController)replaceSceneContent("./SelectLanguage.fxml");
            ctrlSL.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void gotoLocalRemote() {
        try {
            LocalRemoteController ctrlLR =
                (LocalRemoteController)replaceSceneContent("./LocalRemote.fxml");
            ctrlLR.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        //InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane page;

        try {
            page = (AnchorPane) loader.load();//(in);
        } finally {
            //in.close();
        }
        root.getChildren().removeAll();
        root.getChildren().addAll(page);

        return (Initializable)loader.getController();
    }

    
    public void setLanguage(String lang) {
    		language = lang;
    		gotoLocalRemote();
    }

	public static void main(String[] args) {
		Application.launch(args);
	}
}
