package view;

import controller.Controller;
import javafx.stage.Stage;
import view.renderer.Renderer;
import view.scene.SceneMainController;

/**
 *
 * @author Oleg
 *
 */
public final class ViewImpl implements View {

    private final SceneMainController sceneMainController;
    private final Controller controller;

    /**
     * Constructor.
     *
     * @param controller
     *            controller
     */
    public ViewImpl(final Controller controller) {
        this.controller = controller;
        sceneMainController = new SceneMainController();
    }

    @Override
    public void startSceneMainController(final Stage primaryStage) {
        try {
            sceneMainController.setController(controller);
            sceneMainController.start(primaryStage);
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Renderer getRenderer() {
        return sceneMainController.getRenderer();
    }

    @Override
    public SceneMainController getSceneMainController() {
        return sceneMainController;
    }
}
