package view.scenefactory;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import settings.SettingsImpl;
import utility.GameModes;
import view.View;
import view.sceneloader.SceneLoaderImpl;
import view.utilities.Screens;

/**
 * 
 * Implements a factory of the game scenes.
 *
 */
public final class SceneFactoryImpl implements SceneFactory {

    private final View view;
    private Stage stage;
    private GameModes gameMode = GameModes.STORY_MODE;

    /**
     * 
     * @param view
     *            reference to the object that contains the entire view
     */
    public SceneFactoryImpl(final View view) {
        this.view = view;
    }

    @Override
    public void setStage(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void openMenuScene() {
        this.openNewPage(Screens.MENU);
    }

    @Override
    public void openSettingsScene() {
        this.openNewPage(Screens.SETTINGS);
    }

    @Override
    public void openAchievementsScene() {
        this.openNewPage(Screens.ACHIEVEMENTS);
    }

    @Override
    public void openHighScoresScene() {
        this.openNewPage(Screens.HIGH_SCORES);
    }

    @Override
    public void openManualScene() {
        this.openNewPage(Screens.MANUAL);
    }

    @Override
    public void openPauseScene() {
        this.openNewPage(Screens.PAUSE);
    }

    @Override
    public void openGameScene() {
        this.openNewPage(Screens.GAME);
    }

    @Override
    public void openSelectModeScene() {
        this.openNewPage(Screens.SELECTMODE);
    }

    @Override
    public void openGameOverScene() {
        this.openNewPage(Screens.GAME_OVER);
    }

    @Override
    public void setGameMode(final GameModes gameMode) {
        this.gameMode = gameMode;
    }

    private void openNewPage(final Screens screen) {
        this.checkFullScreen();
        new SceneLoaderImpl(this.view).loadScene(this.stage, screen, this.gameMode);
    }

    private void checkFullScreen() {
        if (SettingsImpl.getSettings().isFullScreen() && this.stage.getStyle().equals(StageStyle.DECORATED)) {
            this.createNewStage();
            this.stage.initStyle(StageStyle.UNDECORATED);
            this.stage.setMaximized(true);
        } else if (!SettingsImpl.getSettings().isFullScreen() && this.stage.getStyle().equals(StageStyle.UNDECORATED)) {
            this.createNewStage();
            this.stage.initStyle(StageStyle.DECORATED);
        }
    }

    private void createNewStage() {
        this.stage.close();
        this.stage = new Stage();
        this.stage.setTitle("MAGNUM CHAOS");
        this.stage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
    }

}
