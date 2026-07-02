package application;

import controller.gameSwitcher.SceneController;
import controller.gameSwitcher.SceneControllerImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utilities.EnumInt;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
/**
*
*/
public class Main extends Application {

    /**
     * Main.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage windowManager) throws Exception {
        windowManager.setHeight(Double.valueOf(EnumInt.HEIGHT.getValue()));
        windowManager.setWidth(Double.valueOf(EnumInt.WIDTH.getValue()));
        windowManager.setTitle("Alone in the space");
        windowManager.setResizable(false);
        windowManager.getIcons().add(new Image("file:icon16.png"));
        windowManager.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        final SceneController sc = new SceneControllerImpl(windowManager);
        sc.switchToMainMenu();
    }
}
