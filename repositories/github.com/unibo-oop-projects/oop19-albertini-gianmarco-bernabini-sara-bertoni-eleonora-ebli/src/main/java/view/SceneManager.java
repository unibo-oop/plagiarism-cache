package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Objects;

import controller.gui.settings.GUISettingsController;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 * Utility class that provides basic functions for every scene.
 *
 */
public final class SceneManager {

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double MAX_HEIGHT = SCREEN_SIZE.getHeight();
    private static final double MAX_WIDTH = SCREEN_SIZE.getWidth();
    private static final double FULLSCREEN = 1.0;
    private static final double DEFAULT = 0.9;
    private static double screenDimension = DEFAULT;
    private static Stage primaryStage;

    private SceneManager() { }

    /**
     * Returns the computed scene's height, which is the screen's height multiplied
     * by the actual stage's dimension chosen by the user.
     * 
     * @return the computed scene's height
     */
    public static double getSceneHeight() {
        return MAX_HEIGHT * getActualScreenDim();
    }

    /**
     * Returns the computed scene's width, which is the screen's width multiplied
     * by the actual stage's dimension chosen by the user.
     * 
     * @return the computed scene's width
     */
    public static double getSceneWidth() {
        return MAX_WIDTH * getActualScreenDim();
    }

    /**
     * Sets the current primary stage.
     * 
     * @param stage
     *      the primary stage
     */
    public static void setPrimaryStage(final Stage stage) {
        primaryStage = stage;
    }

    /**
     * Sets a new scene to the primary stage.
     * 
     * @param scene
     *      the new scene to set on the primary stage
     */
    public static void showScene(final Scene scene) {
        //risolve un bug della funzione stage.setResizable(true) che entrava in conflitto con 
        //le dimensioni assegante alla scena
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        //imposta la scena nell'angolo in alto a sinistra per creare un effetto di finto fullscreen
        if (getActualScreenDim() == FULLSCREEN) {
            primaryStage.setX(0);
            primaryStage.setY(0);
        } 
    }

    /**
     * Sets the image to dynamically resize with its stage.
     * 
     * @param backgroundImage
     *      the imageview to set as background
     */
    public static void setSceneBackground(final ImageView backgroundImage) {
        final ImageView img = Objects.requireNonNull(backgroundImage, "You didn't give me any background image");
        img.fitHeightProperty().bind(primaryStage.heightProperty());
        img.fitWidthProperty().bind(primaryStage.widthProperty());
    }

    private static double getActualScreenDim() {
        if (Scenes.SETTINGS.getController() != null) {
            final GUISettingsController settingsController = Scenes.SETTINGS.getController();
            screenDimension = settingsController.getScreenSizeDimensions();
        }
        return screenDimension;
    }
}
