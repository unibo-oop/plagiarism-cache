package view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.score.Score;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.audio.AudioManager;
import view.audio.AudioTrack;
import view.viewmanager.ViewManager;

/**
 * GameOverController is the controller of game over scene. It writes the
 * player's informations in the scoreboard file. It uses items from javafx.
 *
 */
public class GameOverController extends AbstractController implements Initializable {

    private final Score player;
    private final AudioManager music;

    @FXML
    private Label score;
    @FXML
    private Button menu;
    @FXML
    private Button newGame;
    @FXML
    private Button esc;

    /**
     * Creates a new GameOverController writing the player's information scoreboard
     * file.
     * 
     * @param loader       the {@link ViewManager} shared by all the controllers.
     * @param player       the informations about the players.
     * @param audioManager the {@link AudioManager} for regulating the music and
     *                     sounds.
     * @throws IOException if file is missing
     */
    public GameOverController(final ViewManager loader, final Score player, final AudioManager audioManager) {
        super(loader);
        this.player = player;
        this.music = audioManager;
    }

    /**
     * Default initializer method required by {@link Initializable} interface.
     */
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.score.setText(this.player.getName() + '\n' + '\n' + this.score.getText() + this.player.getPoints());
        if (this.music.isFirstPlay() || (!this.music.isOff() && this.music.getAudioTrack() != AudioTrack.GAME_OVER)) {
            this.music.stop();
            this.music.play(AudioTrack.GAME_OVER);
        }
    }

    /*
     * It opens menu scene
     */
    @FXML
    private void backToMenu() {
        this.getViewManager().openMenuScene();
    }

    /*
     * It stops the game
     */
    @FXML
    private void stop() {
        this.getViewManager().getView().getController().stop();
        this.getViewManager().getView().close();
    }

    /*
     * It starts a new game
     */
    @FXML
    private void newGame() {
        this.getViewManager().openCharacterScene();
    }

}