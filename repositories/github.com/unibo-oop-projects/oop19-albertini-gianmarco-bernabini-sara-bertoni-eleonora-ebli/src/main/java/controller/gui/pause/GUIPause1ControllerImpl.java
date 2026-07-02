package controller.gui.pause;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.gui.game.GUIGameControllerImpl;
import controller.gui.settings.GUISettingsController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import view.SceneManager;
import view.Scenes;

public class GUIPause1ControllerImpl implements Initializable, GUIPause1Controller {

    @FXML
    private VBox vertBox;

    @FXML
    private Button continueBtn;

    @FXML
    private Button tutorialBtn;

    @FXML
    private Button menuBtn;

    @FXML
    private Button retryBtn;

    @FXML
    private ImageView sadUni;

    @FXML
    private Label pauseLbl;

    @FXML
    private TextArea tutorialText;

    @FXML
    private Button closeBtn;

    @FXML
    private Pane retryPane;

    @FXML
    private ImageView lazyImg;

    @FXML
    private  Button noBtn;

    @FXML
    private ImageView strongImg;

    @FXML
    private  Button yesBtn;

    @FXML
    private Label retryLbl;

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void continueBtnOnClickHandler() throws IOException {
         SceneManager.showScene(Scenes.GAME.getOldScene());
         final GUIGameControllerImpl controller = Scenes.GAME.getController();
         System.out.println(controller.toString());
         controller.getEngine().restart();
         System.out.println("dopo");
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void tutorialBtnOnClickHandler() throws IOException {
        tutorialText.setVisible(true);
        closeBtn.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void menuBtnOnClickHandler() throws IOException {
       SceneManager.showScene(Scenes.MENU.getNewScene());
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void closeBtnOnClickHandler() throws IOException {
        tutorialText.setVisible(false);
        closeBtn.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void retryBtnOnClickHandler() throws IOException {
        retryPane.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void noBtnOnClickHandler() throws IOException {
        retryPane.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void yesBtnOnClickHandler() throws IOException {
        SceneManager.showScene(Scenes.GAME.getNewScene());
        final GUIGameControllerImpl controller = Scenes.GAME.getController();
        final GUISettingsController controllerSettings = Scenes.SETTINGS.getController();
        if (controllerSettings != null) {
            controller.getEngine().getWorld().setSettings(controllerSettings.isSoundSettings());
        }
        controller.firstTry();
        retryPane.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        tutorialText.setVisible(false);
        closeBtn.setVisible(false);
        retryPane.setVisible(false);
    }
}
