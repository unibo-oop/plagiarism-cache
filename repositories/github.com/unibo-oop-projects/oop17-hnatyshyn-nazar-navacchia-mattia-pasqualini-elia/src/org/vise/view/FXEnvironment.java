package org.vise.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.vise.view.screens.FXMLScreens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The {@link Environment}; this class if the core of the the framework for charging new screens. All the javafx
 * {@link Application} relie to this class, this is a only instace classs(singleton).
 * This environment has all the {@link Node} of the application: {@link Stage}, {@link Scene} and {@link StackPane}.
 *
 */
public class FXEnvironment extends Application {
    /**
     * 
     */
    public enum PlayerMode { ONLINE, OFFLINE }

    private Stage mainStage;
    private final Scene mainScene;
    private final Map<FXMLScreens, UI> uis = new HashMap<>();

    private PlayerMode playerMode;

    /**
     * 
     */
    public FXEnvironment() {
        super();
        final Pane pane = new StackPane();
        pane.setStyle("-fx-background: #2A2A2A;");
        this.mainScene = new Scene(pane);
    }

    /** (non-Javadoc).
     * @see javafx.application.Application#start(javafx.stage.Stage).
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.mainStage = primaryStage;
        this.mainStage.setScene(this.mainScene);
        this.mainStage.setOnCloseRequest(e -> System.exit(0));
    }

    /**
     * Show the actual view.
     */
    public void show() {
        this.mainStage.show();
    }

    /**
     * Display the {@link FXMLScreens} into this scene.
     *
     * @param screens
     *            the {@link FXMLScreens} to display
     * @throws IOException
     *          Exception.
     */
    public void displayScreen(final FXMLScreens screens) throws IOException {
        if (this.uis.containsKey(screens)) {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource(screens.getPath()));
            loader.setController(this.uis.get(screens));
            this.mainStage.setScene(new Scene(loader.load()));
            this.uis.get(screens).preShowOperation();
            this.mainStage.show();
        } else {
            System.out.println("Impossible to load this screen.");
        }
    }

    /**
     * Loads a {@link FXMLScreens} ad sets its controller.
     *
     * @param screen
     *            the {@link FXMLScreens}
     * @param controller
     *            the controller
     */
    public void loadScreen(final FXMLScreens screen, final UI controller) {
        if (!this.uis.containsKey(screen)) {
            this.uis.put(screen, controller);
        } else {
            System.out.println("Screen already loaded.");
        }
    }

    /**
     * Set player mode status (Online or Offline).
     * @param playerMode
     *          Enum of a player mode
     */
    public void setPlayerMode(final PlayerMode playerMode) {
        this.playerMode = playerMode;
    }

    /**
     * 
     * @return
     *          A current PlayerMode.
     */
    public PlayerMode getPlayerMode() {
        return this.playerMode;
    }

    /**
     * 
     * @return
     *          Main stage.
     */
    public Stage getMainStage() {
        return this.mainStage;
    }
}
