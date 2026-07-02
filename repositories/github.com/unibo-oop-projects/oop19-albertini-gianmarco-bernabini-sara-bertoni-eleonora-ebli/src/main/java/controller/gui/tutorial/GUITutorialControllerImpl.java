package controller.gui.tutorial;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.SceneManager;
import view.Scenes;

public class GUITutorialControllerImpl implements GUITutorialController {

    @FXML
    private Button backBtn;

    @FXML
    private Label labelBtn;

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void backBtnOnClickHandler() {
        SceneManager.showScene(Scenes.MENU.getNewScene());
    }

}
