package settings.observers;

import java.util.stream.Collectors;

import javafx.scene.control.ComboBox;
import settings.SettingsImpl;

/**
 * 
 * This Class implements the resolution changes observer.
 *
 */
public class ResolutionObserver implements Observer {

    private final ComboBox<String> resolutionComboBox;

    /**
     * 
     * @param resolutionComboBox
     *            the graphic ComboBox where user can change the game resolution.
     */
    public ResolutionObserver(final ComboBox<String> resolutionComboBox) {
        this.resolutionComboBox = resolutionComboBox;
        this.initialize();
    }

    @Override
    public final void updateSetting() {
        SettingsImpl.getSettings().setSelectedResolution(SettingsImpl.getSettings().getSupportedResolutions()
                .get(this.resolutionComboBox.getSelectionModel().getSelectedIndex()));
    }

    /* Initializes the graphic node. */
    private void initialize() {
        this.resolutionComboBox.getItems().addAll(SettingsImpl.getSettings().getSupportedResolutions().stream()
                .map(resolution -> resolution.getKey() + "x" + resolution.getValue()).collect(Collectors.toList()));
        this.resolutionComboBox.setValue(SettingsImpl.getSettings().getSelectedresolution().getKey() + "x"
                + SettingsImpl.getSettings().getSelectedresolution().getValue());

        this.resolutionComboBox.getSelectionModel()
                .select(this.resolutionComboBox.getSelectionModel().getSelectedItem());

        // Required if for some reason the selected index still equals to -1.
        if (this.resolutionComboBox.getSelectionModel().getSelectedIndex() == -1) {
            this.resolutionComboBox.getSelectionModel()
                    .select(SettingsImpl.getSettings().getSupportedResolutions().size() / 2);
            this.updateSetting();
        }
    }

}
