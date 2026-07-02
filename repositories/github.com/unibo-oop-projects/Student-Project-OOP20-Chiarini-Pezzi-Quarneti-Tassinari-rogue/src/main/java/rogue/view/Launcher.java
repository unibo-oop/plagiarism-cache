package rogue.view;

import java.awt.Dimension;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import rogue.controller.GameController;
import rogue.controller.GameControllerImpl;
import rogue.view.menu.MenuViewImpl;

/**
 * Class that creates the Menu.
 *
 */
public final class Launcher extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        /*
         * Calculate scene size
         */
        final Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        final double width = screenSize.getWidth() / 3;
        final double height = screenSize.getHeight() / 2.5;
        /*
         * Load Menu fxml.
         */
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("layout/MainMenu.fxml"));
        final Parent root = loader.load();
        /*
         * Pass the MenuController to The view.
         */
        final GameController gameController = new GameControllerImpl();
        final MenuViewImpl menuView = loader.getController();
        menuView.init(gameController);
        /*
         * Create MenuView
         */
        final Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("images/rogueIcon.png").toExternalForm()));
        primaryStage.setTitle("Rogue");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    /**
     * 
     * @param args unused.
     */
    public static void main(final String[] args) {
        launch();
    }

}
