package starcatraz.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import starcatraz.model.Settings;
import starcatraz.util.AppResolution;
import starcatraz.util.AppSound;
import starcatraz.util.FileReadWrite;

/**
 * Settings controller implementation.
 */
public class SettingsControllerImpl extends StarcatrazController implements Initializable, SettingsController {

    @FXML
    private AnchorPane settingsRoot;
    @FXML
    private Slider musicVolumeSlider;
    @FXML
    private Slider soundEffectsVolumeSlider;
    @FXML
    private ComboBox<String> resolutionComboBox;

    /**
     * Instance of settings.
     */
    private Settings settings;

    /**
     * List of all the possible resolutions.
     */
    private final List<AppResolution> resolutions = new LinkedList<>();

    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {

        musicVolumeSlider.setOnMouseReleased(e -> {
            final double musicVolume = musicVolumeSlider.getValue() / 100;
            SettingsController.setMusic(musicVolume);
            settings.setMusicVolume(musicVolume);
            AppSound.MENU_MUSIC.play();
            FileReadWrite.writeSettings(settings);
        });

        soundEffectsVolumeSlider.setOnMouseReleased(e -> {
            final double soundEffectsVolume = soundEffectsVolumeSlider.getValue() / 100;
            SettingsController.setSound(soundEffectsVolume);
            settings.setSoundEffectsVolume(soundEffectsVolume);
            FileReadWrite.writeSettings(settings);
        });
    }

    @Override
    public void applyResolution(final ActionEvent event) {
        final AppResolution selectedRes = resolutions.get(resolutionComboBox.getSelectionModel().getSelectedIndex());
        getStarcatrazApp().setupPrimaryStage(selectedRes.getWidth(), selectedRes.getHeight());
        this.settings.setResolutionHeight(selectedRes.getHeight());
        this.settings.setResolutionWidth(selectedRes.getWidth());
        FileReadWrite.writeSettings(settings);
    }

    @Override
    public void closeSettingsButtonClick() {
        settingsRoot.setVisible(false);
    }

    @Override
    public void setSettings(final Settings settings) {
        this.settings = settings;
        musicVolumeSlider.setValue(this.settings.getMusicVolume() * 100);
        soundEffectsVolumeSlider.setValue(this.settings.getSoundEffectsVolume() * 100);
        for (final AppResolution resolution : AppResolution.values()) {
            if (AppResolution.DEFAULT.getHeight() > resolution.getHeight() && AppResolution.DEFAULT.getWidth() > resolution.getWidth()) {
                resolutionComboBox.getItems().addAll(resolution.name() + " - " + resolution.toString());
                resolutions.add(resolution);
            }
        }
        resolutions.add(AppResolution.DEFAULT);
        resolutionComboBox.getItems().addAll(AppResolution.DEFAULT.name() + " - " + AppResolution.DEFAULT.toString());
        resolutionComboBox.getSelectionModel().selectLast();
    }

    @Override
    public Settings getSettings() {
        return this.settings;
    }
}
