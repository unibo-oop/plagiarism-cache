package ballblast.view.scenecontroller;

import javafx.fxml.FXML;

/**
 * 
 * Abstract class that add method for secondary menus.
 */
public abstract class AbstractSubSceneController extends AbstractSceneController {

    /**
     * Return to main menu scene.
     */
    @FXML
    protected void backToMenu() {
        this.backScene();
    }

}
