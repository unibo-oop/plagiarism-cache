package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        final int sceneWidth = 350;//(int)(Screen.getPrimary().getBounds().getWidth()*0.182291);
        final int sceneHeight = 600;//(int)(Screen.getPrimary().getBounds().getHeight()*5/9);

        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/main.fxml"));
        final Scene scene = new Scene(root, sceneWidth, sceneHeight);
        // Stage configuration
        stage.setTitle("JavaFX - Complete Example");
        stage.setScene(scene);
        stage.setResizable(false);
        /*Log.log("Inizia il gioco");
        Log.log("test");
        Log.log(Level.SEVERE, "ciao");*/
        stage.show();
    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
