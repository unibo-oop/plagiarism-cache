package view.sceneloader;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import settings.SettingsImpl;
import utility.GameModes;
import view.View;
import view.controller.AchievementsSceneController;
import view.controller.GameOverSceneController;
import view.controller.GameSceneController;
import view.controller.HighScoresSceneController;
import view.controller.SceneController;
import view.utilities.Screens;

/**
 * 
 * Implements the necessary methods to load game scenes.
 *
 */
public class SceneLoaderImpl implements SceneLoader {

    private final View view;

    /**
     * 
     * @param view
     *            reference to the object that contains the entire view.
     */
    public SceneLoaderImpl(final View view) {
        this.view = view;
    }

    @Override
    public final void loadScene(final Stage stage, final Screens screen, final GameModes gameMode) {
        final Region root;
        final FXMLLoader loader = new FXMLLoader();

        try {

            loader.setLocation(getClass().getResource(screen.getPath()));
            root = loader.load();

            // Gets controller and give view's reference.
            final SceneController controller = loader.getController();
            controller.setSceneFactory(this.view.getSceneFactory());

            root.setPrefSize(SettingsImpl.getSettings().getSelectedresolution().getKey(),
                    SettingsImpl.getSettings().getSelectedresolution().getValue());

            root.getChildrenUnmodifiable().stream().forEach(e -> {
                e.setScaleX(SettingsImpl.getSettings().getScaleFactor());
                e.setScaleY(SettingsImpl.getSettings().getScaleFactor());
            });

            stage.setScene(new Scene(root));

            /*
             * Required to make sure that no buttons event is called, when space bar is
             * pressed.
             */
            if (screen != Screens.GAME) {
                stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                    if (e.getCode() == KeyCode.SPACE) {
                        e.consume();
                    }
                });
            }

            stage.getScene().getStylesheets().add(StylePaths.DEFAULTSYLE.getName());
            stage.setResizable(false);

            if (!SettingsImpl.getSettings().isFullScreen()) {
                stage.sizeToScene();
                stage.centerOnScreen();
            }

            if (!stage.isShowing()) {
                stage.show();
            }

            switch (screen) {
            case GAME:
                this.view.startGame((GameSceneController) controller, gameMode);
                break;
            case GAME_OVER:
                final GameOverSceneController goController = (GameOverSceneController) controller;
                goController.setGameData(this.view.getGameData());
                break;
            case HIGH_SCORES:
                final HighScoresSceneController hsController = (HighScoresSceneController) controller;
                hsController.setHighScores(this.view.getHighScores());
                break;
            case ACHIEVEMENTS:
                final AchievementsSceneController aController = (AchievementsSceneController) controller;
                aController.setAchievements(this.view.getAchievements());
                break;
            case MENU:
                this.view.resetGame();
                break;
            default:
                break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private enum StylePaths {
        DEFAULTSYLE("style.css");

        private static final String STYLES_PATH = "/view/style/";
        private final String selectedStyle;

        StylePaths(final String selectedStyle) {
            this.selectedStyle = selectedStyle;
        }

        /**
         * Provides the style sheets paths.
         * 
         * @return the selected style sheet path as string.
         */
        public String getName() {
            return StylePaths.STYLES_PATH + this.selectedStyle;
        }
    }

}
