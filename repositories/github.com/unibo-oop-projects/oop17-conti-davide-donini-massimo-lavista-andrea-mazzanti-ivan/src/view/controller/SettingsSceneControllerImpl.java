package view.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import settings.SettingsImpl;
import settings.observers.BackGroundAudioObserver;
import settings.observers.FpsObserver;
import settings.observers.TrainingModeObserver;
import settings.observers.Observer;
import settings.observers.ResolutionObserver;
import settings.utilities.SettingsToSave;
import utility.StaticUtilities;

/**
 * 
 * Implements the settings page of the menu.
 *
 */
public class SettingsSceneControllerImpl extends AbstractSecondarySceneController {

    @FXML
    private Button applyButton;
    @FXML
    private ComboBox<String> resolutionComboBox;

    @FXML
    private ComboBox<Integer> fpsComboBox;

    @FXML
    private CheckBox bgAudioCheckBox;

    @FXML
    private CheckBox trainingModeCheckBox;

    private final List<Observer> observers = new ArrayList<>();

    @FXML
    private void initialize() {
        this.observers.addAll(Arrays.asList(new ResolutionObserver(this.resolutionComboBox),
                new FpsObserver(this.fpsComboBox), new BackGroundAudioObserver(this.bgAudioCheckBox),
                new TrainingModeObserver(this.trainingModeCheckBox)));

        this.applyButton.setDisable(true);

    }

    @FXML
    private void enableApplyButton() {
        this.applyButton.setDisable(false);
    }

    @FXML
    private void applyChanges() {
        this.observers.forEach(observer -> observer.updateSetting());
        this.saveSettingsToFile();
        this.getSceneFactory().openSettingsScene();
    }

    private void saveSettingsToFile() {

        final Map<SettingsToSave, Object> settingsToSave = new HashMap<>();

        settingsToSave.put(SettingsToSave.RESOLUTION_WIDTH,
                SettingsImpl.getSettings().getSelectedresolution().getKey());
        settingsToSave.put(SettingsToSave.RESOLUTION_HEIGHT,
                SettingsImpl.getSettings().getSelectedresolution().getValue());
        settingsToSave.put(SettingsToSave.FPS, SettingsImpl.getSettings().getSelectedFPS());
        settingsToSave.put(SettingsToSave.FULLSCREEN, SettingsImpl.getSettings().isFullScreen());
        settingsToSave.put(SettingsToSave.BACKGROUND_AUDIO, SettingsImpl.getSettings().isBackGroundAudioActivated());
        settingsToSave.put(SettingsToSave.TRAININGMODE, SettingsImpl.getSettings().isTrainingMode());

        StaticUtilities.writeSettingsToFile(settingsToSave);
    }

}
