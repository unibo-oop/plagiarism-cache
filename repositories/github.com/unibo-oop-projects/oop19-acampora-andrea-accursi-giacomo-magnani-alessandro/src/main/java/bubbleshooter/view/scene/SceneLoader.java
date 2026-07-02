package bubbleshooter.view.scene;

import java.io.IOException;
import bubbleshooter.view.scene.controller.AbstractController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Scene loader.
 */

public final class SceneLoader {

    private Scene scene;
    private AbstractController controller;

    /**
     * Load the specified scene.
     * 
     * @param scene to load
     * @throws IOException The FXMLLoader can fail the load of the scene.
     */
    public void loadScene(final FXMLPath scene) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        this.scene = new Scene(loader.load(getClass().getResourceAsStream(scene.getPath())));
        this.controller = loader.getController();
    }

    /**
     * Gets the scene.
     * 
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets the controller of the scene.
     * 
     * @return scene controller.
     */
    public AbstractController getController() {
        return controller;
    }

}
