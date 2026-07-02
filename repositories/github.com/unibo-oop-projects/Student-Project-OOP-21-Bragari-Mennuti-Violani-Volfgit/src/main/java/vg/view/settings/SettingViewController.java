package vg.view.settings;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import vg.view.ViewController;
import vg.view.utils.Colors;

import java.util.List;

import static vg.view.utils.Colors.SELECTED_COLOR;
import static vg.view.utils.Colors.UNSELECTED_COLOR;

public class SettingViewController extends ViewController {

    @FXML
    private Text musicToggle;

    @FXML
    private Text effectsToggle;

    @FXML
    private Text musicLabel;

    @FXML
    private Text effectsLabel;

    @FXML
    private Text quitBtn;

    /**
     * Color with accent color selected button setting.
     * @param idx button selected index
     */
    public void highlightSelectedButton(final int idx) {
        List<Text> buttons = List.of(musicLabel, effectsLabel, quitBtn);
        buttons.forEach(button -> button.setFill(UNSELECTED_COLOR));
        buttons.get(idx).setFill(SELECTED_COLOR);
    }

    /**
     * toggle from ON to OFF background label.
     * @param isOn state of background sound
     */
    public void changeMusicStateON(final boolean isOn) {
        changeStateON(this.musicToggle, isOn);
    }

    /**
     * toggle from ON to OFF effects label.
     * @param isOn state of effects sound
     */
    public void changeEffectStateON(final boolean isOn) {
        //actually is not implemented the disabling effects
        changeStateON(this.effectsToggle, true);
    }

    /**
     * Change color and label of setting sound button.
     * @param labelText label to be changed
     * @param isOn state of sound setting
     */
    private void changeStateON(final Text labelText,final boolean isOn) {
        labelText.setText(isOn ? "ON" : "OFF");
        labelText.setFill(isOn ? SELECTED_COLOR : UNSELECTED_COLOR);
    }
}
