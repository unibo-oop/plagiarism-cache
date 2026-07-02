package view.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import view.audio.AudioManager;
import view.audio.AudioTrack;
import view.viewmanager.ViewManager;

/**
 * MenuController to control main Menu scene. It permits to use severals buttons
 * to open the different scenes. It uses items from javafx.
 */
public class MenuController extends AbstractController implements Initializable {

    private final AudioManager music;

    @FXML
    private Button newgame;

    @FXML
    private Button scoreboard;

    @FXML
    private Button options;

    @FXML
    private Button credits;

    @FXML
    private Button exit;

    /**
     * Initialize the MenuController.
     * 
     * @param loader       the {@link ViewManager} shared by all the controllers.
     * @param audioManager the {@link AudioManager} for regulating the music and
     *                     sounds.
     */
    public MenuController(final ViewManager loader, final AudioManager audioManager) {
        super(loader);
        this.music = audioManager;
    }

    /**
     * Default initializer method required by {@link Initializable} interface.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        if (this.music.isFirstPlay() || (!this.music.isOff() && this.music.getAudioTrack() != AudioTrack.SOUNDTRACK)) {
            this.music.stop();
            this.music.play(AudioTrack.SOUNDTRACK);
        }
    }

    /*
     * Open the Story scene.
     */
    @FXML
    private void newGame() {
        this.getViewManager().openStoryScene();
    }

    /*
     * Open the Settings scene.
     */
    @FXML
    private void settings() {
        this.getViewManager().openSettingsScene();
    }

    /*
     * Open the Scoreboard scene.
     */
    @FXML
    private void scoreboard() {
        this.getViewManager().openScoreboardScene();
    }

    /*
     * Open the Credits scene.
     */
    @FXML
    private void credits() {
        this.getViewManager().openCreditsScene();
    }

    /*
     * Calls View's method exit.
     */
    @FXML
    private void exit() {
        this.getViewManager().getView().close();
    }
    
    /*
     * Easter egg.
     */
    @FXML
    private void titleClicked() {
        this.getViewManager().getView().getController().handleError("This game is inspired by Bubble Bobble by Taito", false);
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/embed/O49OgQ_kogw?autoplay=1"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
    
    /*
     * Easter egg.
     */
    @FXML
    private void raccoonClicked() {
        this.getViewManager().getView().getController().handleError("Bwaaaarghhh, don't touch me!", false);
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/embed/h3o5pkOSgwc?autoplay=1"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
