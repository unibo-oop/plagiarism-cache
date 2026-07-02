package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.view.View;
import ballblast.view.imageloader.ImageLoader;
import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The SceneController for the game over scene.
 *
 */
public class GameOverSceneController extends AbstractSubSceneController {

    @FXML
    private Label score;
    @FXML
    private Label ballsDestroyed;
    @FXML
    private Label bulletsShot;
    @FXML
    private Button btnBackToMenu;
    @FXML
    private Button btnOpenLeaderboard;
    @FXML
    private Button btnNewGame;
    private GameScenes selection;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        this.selection = GameScenes.MENU;
        this.score.setText(String.valueOf(controller.getGameData().getScore()));
        this.ballsDestroyed.setText(String.valueOf(controller.getGameData().getDestroyedBalls()));
        this.bulletsShot.setText(String.valueOf(controller.getGameData().getSpawnedBullets()));
    }
    /**
    * Method is used when the user clicks the "START NEW GAME" button.
    * It is handled from JavaFX Gameover.fxml file.
    */
    @FXML
    private void openNewGame() {  //NOPMD the method is injected by fxml.
        this.selection = GameScenes.GAME_MODE;
        this.nextScene();
    }
    /**
    * Method is used when the user clicks the "LEADERBOARD" button.
    * It is handled from JavaFX Gameover.fxml file.
    */
    @FXML
    private void openLeaderboard() { //NOPMD the method is injected by fxml.
        this.selection = GameScenes.LEADERBOARD;
        this.nextScene();
    }

    @Override
    public final GameScenes getNextScene() {
        ImageLoader.getLoader().removeBall();
        return this.selection;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MENU;
    }

    @Override
    public final void onKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.selection = GameScenes.GAME_MODE;
            this.nextScene();
        }
    }
}
