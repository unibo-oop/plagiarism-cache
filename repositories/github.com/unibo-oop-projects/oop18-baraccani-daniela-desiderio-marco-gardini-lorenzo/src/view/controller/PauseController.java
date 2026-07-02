package view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import view.audio.AudioManager;
import view.audio.Volume;
import view.viewmanager.ViewManager;

/**
 * PauseController to control Pause scene. It uses items from javafx.
 */
public class PauseController extends AbstractController implements Initializable {

    private final AudioManager music;

    @FXML
    private Button resume;

    @FXML
    private Button back;

    @FXML
    private CheckBox musicState;

    @FXML
    private Button low;

    @FXML
    private Button medium;

    @FXML
    private Button high;

    /**
     * Initialize PauseController.
     * 
     * @param loader       the {@link ViewManager} shared by all the controllers.
     * @param audioManager {@link AudioManager} for regulating the music and sounds.
     */
    public PauseController(final ViewManager loader, final AudioManager audioManager) {
        super(loader);
        this.music = audioManager;
    }

    /**
     * Default initializer method required by {@link Initializable} interface.
     */
    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        if (this.music.isOff()) {
            this.musicState.setSelected(true);
            this.low.setDisable(true);
            this.medium.setDisable(true);
            this.high.setDisable(true);
        } else {
            this.musicState.setSelected(false);
            this.setBouttons(this.music.getVolume());
        }
    }

    /*
     * Turn the music on and off.
     */
    @FXML
    private void turnMusicOnOff() {
        this.music.setOff(this.musicState.isSelected());
        if (this.music.isOff()) {
            this.music.stop();
            this.low.setDisable(true);
            this.medium.setDisable(true);
            this.high.setDisable(true);
        } else {
            this.music.setVolume(this.music.getVolume());
            this.setBouttons(this.music.getVolume());
            this.music.play(this.music.getAudioTrack());
        }
    }

    /*
     * Set the music's volume low.
     */
    @FXML
    private void volumeLow() {
        if (!this.music.isOff()) {
            this.music.setVolume(Volume.LOW);
            this.setBouttons(this.music.getVolume());
        }
    }

    /*
     * Set the music's volume medium.
     */
    @FXML
    private void volumeMedium() {
        if (!this.music.isOff()) {
            this.music.setVolume(Volume.MEDIUM);
            this.setBouttons(this.music.getVolume());
        }
    }

    /*
     * Set the music's volume high.
     */
    @FXML
    private void volumeHigh() {
        if (!this.music.isOff()) {
            this.music.setVolume(Volume.HIGH);
            this.setBouttons(this.music.getVolume());
        }
    }

    /*
     * Resume the game paused.
     */
    @FXML
    private void resumeGame() {
        this.getViewManager().reopenGameScene();
    }

    /*
     * Reload the menu scene in the gui.
     */
    @FXML
    private void backToMenu() {
        this.getViewManager().openMenuScene();
        this.getViewManager().getView().getController().exitPause(false);
    }

    /*
     * Disable ore enable volume's buttons.
     */
    private void setBouttons(final Volume newVolume) {
        switch (newVolume) {
            case LOW:
                this.low.setDisable(true);
                this.medium.setDisable(false);
                this.high.setDisable(false);
                break;
            case MEDIUM:
                this.low.setDisable(false);
                this.medium.setDisable(true);
                this.high.setDisable(false);
                break;
            case HIGH:
                this.low.setDisable(false);
                this.medium.setDisable(false);
                this.high.setDisable(true);
                break;
            default:
                break;
        }
    }
}
