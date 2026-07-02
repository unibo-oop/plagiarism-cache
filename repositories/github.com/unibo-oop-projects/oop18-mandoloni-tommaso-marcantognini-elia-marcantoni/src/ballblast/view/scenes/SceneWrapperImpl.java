package ballblast.view.scenes;

import ballblast.view.scenecontroller.AbstractSceneController;
import javafx.scene.Scene;

/**
 * A simple implementation of {@link SceneWrapper}.
 */
public class SceneWrapperImpl implements SceneWrapper {

    private final Scene scene;
    private final AbstractSceneController controller;

    /**
     * 
     * @param scene      the {@link Scene} to wrap to.
     * @param controller the {@link AbstractSceneController}.
     */
    public SceneWrapperImpl(final Scene scene, final AbstractSceneController controller) {
        this.scene = scene;
        this.controller = controller;
    }

    @Override
    public final Scene getScene() {
        return this.scene;
    }

    @Override
    public final AbstractSceneController getController() {
        return this.controller;
    }

}
