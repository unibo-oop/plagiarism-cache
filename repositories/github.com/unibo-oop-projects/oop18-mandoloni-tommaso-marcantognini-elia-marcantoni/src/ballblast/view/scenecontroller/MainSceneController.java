package ballblast.view.scenecontroller;

import ballblast.view.scenes.GameScenes;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * The SceneController for the first main scene.
 * 
 */
public class MainSceneController extends AbstractSceneController {

    @Override
    public final GameScenes getNextScene() {
        return GameScenes.LOGIN;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MAIN;
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.nextScene();
        }
    }
}
