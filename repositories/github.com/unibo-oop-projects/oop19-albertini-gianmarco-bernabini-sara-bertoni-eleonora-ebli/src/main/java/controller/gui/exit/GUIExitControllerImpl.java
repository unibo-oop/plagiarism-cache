package controller.gui.exit;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.SceneManager;
import view.Scenes;

public class GUIExitControllerImpl implements GUIExitController {

    @FXML
    private Text sureTxt;

    @FXML
    private Button noBtn;

    @FXML
    private Button yesBtn;

    @FXML
    private ImageView backgroundImage;

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void noBtnOnClickHandler() throws IOException {
        SceneManager.showScene(Scenes.MENU.getNewScene());
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void yesBtnOnClickHandler() throws IOException {
        final Stage stage = (Stage) yesBtn.getScene().getWindow();
        stage.close();
    }
}
