package view;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class that implements the splash screen.
 * 
 * Author: Linda Farneti.
 *
 */
public class SplashScreenLoader extends Preloader {

    private Stage splashScreen;

    /**
     * Method that initializes the stage.
     * 
     * @param stage primaryStage
     * @throws Exception
     */
    public void start(final Stage stage) throws Exception {
        stage.setTitle("CheckMate!");
        stage.initStyle(StageStyle.UNDECORATED);
        splashScreen = stage;
        splashScreen.setScene(createScene());
        splashScreen.show();
    }

    /**
     * Method that load the style of splash screen.
     * 
     * @return scene
     */
    public Scene createScene() {

        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("LoadingStyle.fxml"));
            Scene scene = new Scene(root);
            return scene;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void handleStateChangeNotification(final StateChangeNotification event) {
        if (event.getType() == StateChangeNotification.Type.BEFORE_START) {
            splashScreen.hide();
        }
    }
}
