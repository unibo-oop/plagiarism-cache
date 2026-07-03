package settings.observers;

import javafx.scene.control.CheckBox;
import settings.SettingsImpl;
import settings.utilities.CheckBoxText;

/**
 * 
 * This Class implements the Training Mode changes observer.
 *
 */
public class TrainingModeObserver implements Observer {

    private final CheckBox trainingModeCheckBox;

    /**
     * 
     * @param trainingModeCheckBox
     *            the graphic CheckBox where user can activate or deactivate the
     *            Training Mode.
     */
    public TrainingModeObserver(final CheckBox trainingModeCheckBox) {
        this.trainingModeCheckBox = trainingModeCheckBox;
        this.trainingModeCheckBox.setSelected(SettingsImpl.getSettings().isTrainingMode());
        this.trainingModeCheckBox.selectedProperty().addListener(e -> this.setText());
        this.setText();
    }

    @Override
    public final void updateSetting() {
        SettingsImpl.getSettings().setTrainingMode(this.trainingModeCheckBox.isSelected());
    }

    private void setText() {
        this.trainingModeCheckBox
                .setText(this.trainingModeCheckBox.isSelected() ? CheckBoxText.TRAININGMODE_CHECKED.getText()
                        : CheckBoxText.UNCHECKED.getText());
    }

}
