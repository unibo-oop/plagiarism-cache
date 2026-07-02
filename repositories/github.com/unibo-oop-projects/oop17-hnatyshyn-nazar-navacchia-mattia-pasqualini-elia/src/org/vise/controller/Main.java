package org.vise.controller;

import org.vise.view.FXEnvironment;
import org.vise.view.screens.PlayerScreenController;
import org.vise.view.screens.RLFormScreenController;

import com.sun.javafx.application.PlatformImpl;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 */
public final class Main {

    private Main() {
    }

    /**
     * Entry point of the application.
     * @param args additional arguments.
     */
    public static void main(final String[] args) {
        PlatformImpl.startup(() -> {
        });
        final FXEnvironment environment = new FXEnvironment();
        final RemoteController player = new RemoteControllerImpl();
        final PlayerScreenController playerScreen = new PlayerScreenController(environment, player);
        player.bindPlayerUI(playerScreen);
        player.bindUI(playerScreen);
        final RLFormController rlfController = new RLFormControllerImpl();
        final RLFormScreenController rlformScreen = new RLFormScreenController(environment, rlfController);
        rlfController.bindUI(rlformScreen);
        rlfController.bindRLForm(rlformScreen);

        Platform.runLater(() -> {
            try {
                final Stage primaryStage = new Stage();
                primaryStage.setTitle("Vise Music");
                primaryStage.getIcons().add(new Image(Main.class.getResource("/icons/logo.png").toExternalForm()));
                environment.start(primaryStage);
            } catch (Exception e) {
                System.out.println("Unable to load graphic environment.");
                e.printStackTrace();
            }
            rlformScreen.show();
        });
    }
}
