package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * The SceneController for manual scene.
 * 
 */

public class ManualSceneController extends AbstractSubSceneController {

    private static final String FIRST_SENTENCE = "You must survive. Don't let balls catch you or you will die.";
    private static final String SECOND_SENTENCE = "Shot powerful bullets to destroy balls. Move left or right to dodge balls.";
    private static final String THIRD_SENTENCE = "Pick up powerups to become the strongest player ever. ;)";
    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.label1.setText(FIRST_SENTENCE);
        this.label2.setText(SECOND_SENTENCE);
        this.label3.setText(THIRD_SENTENCE);
    }

    @Override
    public final GameScenes getNextScene() {
        return GameScenes.MENU;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MENU;
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.nextScene();
        }
    }
}
