package ballblast.view.scenecontroller;

import java.util.Locale;

import ballblast.controller.Controller;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * The SceneController for the main menu scene.
 * 
 */
public class MenuSceneController extends AbstractSceneController {

    @FXML
    private Button startNewGameBtn;

    @FXML
    private Button leaderboardBtn;

    @FXML
    private Button manualBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button quitBtn;

    @FXML
    private Label time;

    @FXML
    private Label globalscore;

    @FXML
    private Label matches;

    @FXML
    private Label bullets;

    @FXML
    private Label balls;

    @FXML
    private Label user;
    private GameScenes selection;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.selection = GameScenes.MENU;
        this.user.setText(this.getController().getCurrentUser().getName().toUpperCase(Locale.ENGLISH));
        this.balls.setText(String.valueOf(this.getController().getCurrentUser().getDestroyedBalls()));
        this.bullets.setText(String.valueOf(this.getController().getCurrentUser().getSpawnedBullets()));
        this.globalscore.setText(String.valueOf(this.getController().getCurrentUser().getGlobalScore()));
        this.matches.setText(String.valueOf(this.getController().getCurrentUser().getMatchesPlayed()));
        this.time.setText(String.valueOf((int) this.getController().getCurrentUser().getGameTime()));
    }
    /**
     * Open the game mode selection scene.
     * Method is used when the user clicks the "START NEW GAME" button.
     * It is handled from JavaFX GameSelection.fxml file.
     */
    @FXML
    private void openGameMode() { //NOPMD the method is injected by fxml.
        this.selection = GameScenes.GAME_MODE;
        this.nextScene();
    }

    /**
     * Open leader board scene.
     * Method is used when the user clicks the "LEADERBOARD" button.
     * It is handled from JavaFX Menu.fxml file.
     */
    @FXML
    private void openLeaderboard() { //NOPMD the method is injected by fxml.
        this.selection = GameScenes.LEADERBOARD;
        this.nextScene();
    }

    /**
     * Open settings scene.
     * Method is used when the user clicks the "SETTINGS" button.
     * It is handled from JavaFX Menu.fxml file.
     */
    @FXML
    private void openSettings() { //NOPMD the method is injected by fxml.
        // TODO
        this.selection = GameScenes.SETTINGS;
        this.nextScene();
    }

    /**
     * Open manual scene.
     * Method is used when the user clicks the "MANUAL" button.
     * It is handled from JavaFX Menu.fxml file.
     */
    @FXML
    private void openManual() { //NOPMD the method is injected by fxml.
        this.selection = GameScenes.MANUAL;
        this.nextScene();
    }

    /**
     * Quit game.
     * Method is used when the user clicks the "QUIT" button.
     * It is handled from JavaFX Menu.fxml file.
     */
    @FXML
    private void quitGame() { //NOPMD the method is injected by fxml.
        Runtime.getRuntime().exit(0);
    }

    @Override
    public final GameScenes getNextScene() {
        return selection;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.LOGIN;
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.selection = GameScenes.GAME_MODE;
            this.nextScene();
        }
    }
}
