package it.lttply.view;

import java.io.IOException;

import it.lttply.view.fxmlscreens.FXMLScreens;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Class for loads the screens of the view.
 */
public class FXEnvironmentConcrete extends Application implements FXEnvironment {

    private final ScreenLoader loader;
    private final Pane mainPane;
    private final Scene mainScene;
    private Stage primaryStage;

    /**
     * Sets the main pane.
     */
    public FXEnvironmentConcrete() {
        mainPane = new StackPane();
        mainScene = new Scene(mainPane);
        loader = new ScreenLoaderConcrete();
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.getIcons().add(new Image("view/logo.png"));
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(mainScene);
        this.primaryStage.setOnCloseRequest(e -> System.exit(0));

    }

    @Override
    public Stage getMainStage() {
        return primaryStage;
    }

    @Override
    public Node getNode(final FXMLScreens screen) {
        return loader.getLoadedNode(screen);
    }

    @Override
    public void show() {
        primaryStage.show();
    }

    @Override
    public void displayScreen(final FXMLScreens screen) {
        try {
            loader.loadScreen(screen, mainPane);
            show();
        } catch (final IOException e) {
            System.out.println("Unable to display screen " + screen);
            e.printStackTrace();
        }
    }

    @Override
    public void loadScreen(final FXMLScreens screen, final Object controller) {
        try {
            loader.loadFXMLInCache(screen, controller);
        } catch (final IOException e) {
            System.out.println("Unable to load screen " + screen);
            e.printStackTrace();
        }
    }

}
