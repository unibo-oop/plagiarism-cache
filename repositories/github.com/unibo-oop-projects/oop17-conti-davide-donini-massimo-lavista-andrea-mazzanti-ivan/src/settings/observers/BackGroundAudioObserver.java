package settings.observers;

import javafx.scene.control.CheckBox;
import settings.SettingsImpl;
import settings.utilities.CheckBoxText;

/**
 * 
 * This Class implements the audio settings changes observer.
 *
 */
public class BackGroundAudioObserver implements Observer {

    private final CheckBox bgAudioCheckBox;

    /**
     * 
     * @param bgAudioCheckBox
     *            the graphic CheckBox where user can activate or deactivate the
     *            audio of the game.
     */
    public BackGroundAudioObserver(final CheckBox bgAudioCheckBox) {
        this.bgAudioCheckBox = bgAudioCheckBox;
        this.bgAudioCheckBox.setSelected(SettingsImpl.getSettings().isBackGroundAudioActivated());
        this.bgAudioCheckBox.selectedProperty().addListener(e -> this.setText());
        this.setText();
    }

    @Override
    public final void updateSetting() {
        SettingsImpl.getSettings().setBackGroundAudio(this.bgAudioCheckBox.isSelected());
    }

    private void setText() {
        this.bgAudioCheckBox.setText(
                this.bgAudioCheckBox.isSelected() ? CheckBoxText.CHECKED.getText() : CheckBoxText.UNCHECKED.getText());
    }

}
