package justanotherchessgame.view.utils;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Singleton class used to manage the scene swap without changing the stage.
 */
public final class ScreenController {

    private static final ScreenController INSTANCE = new ScreenController();
    private final Map<String, Pane> screenMap = new HashMap<>();;
    private Scene main;

    private ScreenController() {

    };
    /**
     * Function used to call the singleton from outside.
     * @return the only instance of the class, making all his methods available.
     */
    public static ScreenController getIstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Already created!");
        }
        return INSTANCE;
    }

    /**
     * Function used to initialize the scene.
     * @param scene is the scene.
     */
    public void setScene(final Scene scene) {
        main = scene;
        this.main.getStylesheets().add("justAnotherChessGame/view/style.css");
    }

    /**
     * Function used to resize the screen.
     * @param width is the width of the screen.
     * @param height is the height of the screen.
     */
    public void resizeScreen(final double width, final double height) {
        main.getWindow().setWidth(width);
        main.getWindow().setHeight(height);
    }

    /**
     * Function used to add a scene to the map.
     * @param name is the name of the scene that will be added.
     * @param pane is the content of the scene that will be added.
     */
    public void addScreen(final String name, final Pane pane) {
        screenMap.put(name, pane);
    }

    /**
     * Function used to remove a scene from the map.
     * @param name is the scene that will be removed.
     */
    public void removeScreen(final String name) {
        screenMap.remove(name);
    }

    /**
     * Function used to activate and show a specific scene.
     * @param name is the name of the scene that will be shown.
     */
    public void activate(final String name) {
        main.setRoot(screenMap.get(name));
    }
}
