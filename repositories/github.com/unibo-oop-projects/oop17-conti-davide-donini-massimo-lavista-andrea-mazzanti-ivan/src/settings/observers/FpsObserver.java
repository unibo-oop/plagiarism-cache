package settings.observers;

import javafx.scene.control.ComboBox;
import settings.SettingsImpl;

/**
 * 
 * This Class implements the frames per second setting changes observer.
 *
 */
public class FpsObserver implements Observer {

    private final ComboBox<Integer> fpsComboBox;

    /**
     * 
     * @param fpsComboBox
     *            the graphic CheckBox where user can change the game frame rate.
     */
    public FpsObserver(final ComboBox<Integer> fpsComboBox) {
        this.fpsComboBox = fpsComboBox;
        this.fpsComboBox.getItems().addAll(SettingsImpl.getSettings().getSupportedFps());
        this.fpsComboBox.setValue(SettingsImpl.getSettings().getSelectedFPS());
    }

    @Override
    public final void updateSetting() {
        SettingsImpl.getSettings().setSelectedFPS(this.fpsComboBox.getValue());
    }
}
