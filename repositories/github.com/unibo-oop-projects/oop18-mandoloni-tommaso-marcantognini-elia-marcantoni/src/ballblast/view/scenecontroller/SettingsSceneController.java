package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.settings.Framerates;
import ballblast.settings.KeyCodeSet;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * 
 * A simple controller for the settings scene.
 */
public class SettingsSceneController extends AbstractSubSceneController {

    @FXML
    private Button btnBackToMenu;
    @FXML
    private ToggleButton btnADC;

    @FXML
    private ToggleButton btnLRS;

    @FXML
    private ComboBox<Integer> cbFPS;

    @FXML
    private CheckBox chkMusic;

    @FXML
    private CheckBox chkSound;

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        // Buttons for key set settings.
        final ToggleGroup group = new ToggleGroup();
        this.btnLRS.setToggleGroup(group);
        this.btnADC.setToggleGroup(group);
        this.initializeBoxs();
        btnLRS.setOnMouseClicked(b -> this.setKeySetOne());
        btnADC.setOnMouseClicked(b -> this.setKeySetTwo());
        // Combo box for framerate selection.
        cbFPS.getSelectionModel().selectedItemProperty().addListener(c -> this.setFramerate());
        // Check box for music settings.
        chkMusic.selectedProperty().addListener(c -> this.setMusic());
        chkSound.selectedProperty().addListener(c -> this.setSoundEffects());

    }

    @Override
    public final GameScenes getNextScene() {
        return GameScenes.MENU;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MENU;
    }

    private void initializeBoxs() {
        if (this.checkKeySetInUse().equals(KeyCodeSet.SET_ONE)) {
            btnLRS.setSelected(true);
        } else if (this.checkKeySetInUse().equals(KeyCodeSet.SET_TWO)) {
            btnADC.setSelected(true);
        }

        this.cbFPS.getItems().clear();
        this.cbFPS.getItems().addAll(Framerates.FPS_30.getFPS(), Framerates.FPS_60.getFPS(),
                Framerates.FPS_120.getFPS());
        if (this.checkFramerateInUse().equals(Framerates.FPS_30)) {
            this.cbFPS.getSelectionModel().select(0);
        } else if (this.checkFramerateInUse().equals(Framerates.FPS_60)) {
            this.cbFPS.getSelectionModel().select(1);
        } else if (this.checkFramerateInUse().equals(Framerates.FPS_120)) {
            this.cbFPS.getSelectionModel().select(2);
        }

        if (this.getController().isMusicOn()) {
            chkMusic.setSelected(false);
        } else {
            chkMusic.setSelected(true);
        }
        if (this.getController().isSoundOn()) {
            chkSound.setSelected(false);
        } else {
            chkSound.setSelected(true);
        }

    }

    private void setKeySetOne() {
        this.getController().getCurrentUser().setKeySetting(KeyCodeSet.SET_ONE.toString());
        this.getController().updateStats();
    }

    private void setKeySetTwo() {
        this.getController().getCurrentUser().setKeySetting(KeyCodeSet.SET_TWO.toString());
        this.getController().updateStats();
    }

    private KeyCodeSet checkKeySetInUse() {
        KeyCodeSet currentKeySet;
        if (this.getController().getCurrentUser().getKeySetting().equals(KeyCodeSet.SET_ONE.toString())) {
            currentKeySet = KeyCodeSet.SET_ONE;
        } else {
            currentKeySet = KeyCodeSet.SET_TWO;
        }
        return currentKeySet;
    }

    private Framerates checkFramerateInUse() {
        Framerates currentFramerate;
        if (this.getController().getCurrentUser().getFramesPerSecond() == Framerates.FPS_30.getFPS()) {
            currentFramerate = Framerates.FPS_30;
        } else if (this.getController().getCurrentUser().getFramesPerSecond() == Framerates.FPS_60.getFPS()) {
            currentFramerate = Framerates.FPS_60;
        } else {
            currentFramerate = Framerates.FPS_120;
        }
        return currentFramerate;
    }

    private void setFramerate() {
        this.getController().getCurrentUser()
                .setFramesPerSecond(this.cbFPS.getSelectionModel().getSelectedItem().intValue());
        this.getController().updateStats();
    }

    private void setMusic() {
        if (this.getController().isMusicOn()) {
            this.getController().setMusic(false);
        } else {
            this.getController().setMusic(true);
        }
    }

    private void setSoundEffects() {
        if (this.getController().isSoundOn()) {
            this.getController().setSoundEffects(false);
        } else {
            this.getController().setSoundEffects(true);
        }
    }
}
