package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public final class Main extends Application  {

    private static final int SCENE_WIDTH = 600;
    private static final int SCENE_HEIGHT = 335;

    @Override
    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/main.fxml"));
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        // Stage configuration
        stage.setTitle("Flappy Bird");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }


    public static void main(final String[] args) {
        launch();
    }

}
