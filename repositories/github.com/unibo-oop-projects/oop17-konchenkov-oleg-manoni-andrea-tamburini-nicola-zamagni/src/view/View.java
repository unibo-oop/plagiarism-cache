package view;

import javafx.stage.Stage;
import view.renderer.Renderer;
import view.scene.SceneMainController;

/**
 *
 * @author Oleg, Nicola Tamburini
 *
 */
public interface View {

    /**
     *
     * @param primaryStage
     *            primary stage
     */
    void startSceneMainController(Stage primaryStage);

    /**
     *
     * @return game renderer
     */
    Renderer getRenderer();

    /**
     *
     * @return scene main controller
     */
    SceneMainController getSceneMainController();

}
