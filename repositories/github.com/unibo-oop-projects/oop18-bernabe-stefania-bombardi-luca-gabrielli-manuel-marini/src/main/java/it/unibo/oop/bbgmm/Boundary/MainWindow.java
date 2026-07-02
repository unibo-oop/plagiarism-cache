package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Manuel
 * Class that creates the stage of the application
 */

public class MainWindow extends Application {
    private final Stage primaryStage = new Stage();

    public MainWindow() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage.setTitle("CROP MAZE");
        this.primaryStage.setFullScreen(false);
        this.primaryStage.setWidth(Resolution.getSmallWidth());
        this.primaryStage.setHeight(Resolution.getSmallHeight());
        this.primaryStage.centerOnScreen();
        this.primaryStage.setResizable(false);

        this.primaryStage.setOnCloseRequest(e -> {
            this.primaryStage.close();
        });

        this.primaryStage.getIcons().add(new Image("images/mainMenu/icon.png"));
        //set the main menu as the scene
        this.primaryStage.setScene(MainMenu.getMainMenu(this.primaryStage));
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
