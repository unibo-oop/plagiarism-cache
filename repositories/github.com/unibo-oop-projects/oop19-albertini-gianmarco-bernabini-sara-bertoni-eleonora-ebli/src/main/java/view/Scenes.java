package view;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 
 * For each gui it saves the file's name, the associated loader and the corresponding last displayed scene.
 *
 */
public enum Scenes { //se resta del tempo valuta la scelta di usare il pattern Observer

    /**
     * 
     */
    EXIT("exit"),
    /**
     * 
     */
    GAME("game"),
    /**
     * 
     */
    GAMEOVER("gameOver1"),
    /**
     * 
     */
    LEADERBOARD("leaderboard"),
    /**
     * 
     */
    MENU("menu"),
    /**
     * 
     */
    PAUSE("pause"),
    /**
     * 
     */
    SETTINGS("settings"),
    /**
     * 
     */
    TUTORIAL("tutorial");

    private final String fileName;
    private FXMLLoader loader;
    private Scene scene;

    Scenes(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns the last dislayed scene of the specified type.
     * 
     * @return the old scene
     */
    public Scene getOldScene() {
        return this.scene;
    }

    /**
     * Returns a new scene, created by a new loader.
     * 
     * @return a new scene
     */
    public Scene getNewScene() {
        final FXMLLoader loader = getLoader(this.fileName);
        this.loader = loader;
        final Scene scene = loadScene(loader);
        this.scene = scene;
        return scene;
    }

    /**
     * Returns the controller associated with a scene.
     *
     * @param <T>
     *      the type of the controller
     * @return the controller associated with the root object
     */
    public <T> T getController() {
        if (this.loader != null) {
            return this.loader.getController();
        } else {
            this.loader = getLoader(this.fileName);
            return this.loader.getController();
        }
    }

    private Scene loadScene(final FXMLLoader loader) {
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scene(root, SceneManager.getSceneWidth(), SceneManager.getSceneHeight());
    }

    private FXMLLoader getLoader(final String fileName) {
        final String name = Objects.requireNonNull(fileName, "I can't load any scene if you don't give me the right name");
        return new FXMLLoader(ClassLoader.getSystemResource("layouts/" + name + ".fxml"));
    }
}
