package main;

import controller.menu.MainControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class JumpManiaApp extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        System.setProperty("sun.java2d.opengl", "true");
        MainControllerImpl startController = new MainControllerImpl(primaryStage);
        startController.start();
    }

}
