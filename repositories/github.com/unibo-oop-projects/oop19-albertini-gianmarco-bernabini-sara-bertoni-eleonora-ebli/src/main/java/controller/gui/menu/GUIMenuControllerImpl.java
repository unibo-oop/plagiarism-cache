package controller.gui.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.gui.game.GUIGameControllerImpl;
import controller.gui.settings.GUISettingsController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import view.SceneManager;
import view.Scenes;

/**
 * The controller related to the menu.fxml GUI.
 *
 */
public final class GUIMenuControllerImpl implements GUIMenuController, Initializable {

    @FXML
    private ImageView img;

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void startBtnOnClickHandler() throws IOException {
        SceneManager.showScene(Scenes.GAME.getNewScene());
        final GUIGameControllerImpl gameController = Scenes.GAME.getController();
        final GUISettingsController settingsController = Scenes.SETTINGS.getController();
        if (settingsController != null) {
            gameController.getEngine().getWorld().setSettings(settingsController.isSoundSettings());
        }
        gameController.firstTry();
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void leaderboardBtnOnClickHandler() throws IOException {
        SceneManager.showScene(Scenes.LEADERBOARD.getNewScene());
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void tutorialBtnOnClickHandler() {
        SceneManager.showScene(Scenes.TUTORIAL.getNewScene());
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void settingsBtnOnClickHandler() throws IOException {
        //HINT FOR THE FUTURE: provare a vedere se loader.setController potrebbe funzionare
        final GUISettingsController oldController = Scenes.SETTINGS.getController();
        SceneManager.showScene(Scenes.SETTINGS.getNewScene());
        if (oldController != null) {
            final GUISettingsController newController = Scenes.SETTINGS.getController();
            newController.initializeSettings(oldController.isMusicSettings(),
                    oldController.isSoundSettings(), oldController.getScreenSize());
        }
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void exitBtnOnClickHandler() throws IOException {
        SceneManager.showScene(Scenes.EXIT.getNewScene());
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        SceneManager.setSceneBackground(img);
    }
}
