package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Backgrounds extends Application {

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 300;
    private static Stage primaryStage = new Stage();

    public static void endGame() {
        primaryStage.close();
    }

    public static void main() throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/gameBackgroundsView.fxml"));
        final Scene gameBackgroundsScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setTitle("Game backgrounds");
        primaryStage.setScene(gameBackgroundsScene);
        primaryStage.show();
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

    }

}
