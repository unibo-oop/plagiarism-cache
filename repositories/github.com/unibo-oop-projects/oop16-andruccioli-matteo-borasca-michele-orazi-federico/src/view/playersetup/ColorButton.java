package view.playersetup;

import com.jfoenix.controls.JFXButton;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import utils.enumerations.Color;

/**
 * 
 * It's a rounded button that make user choose a color. It's used in {@link ColorPicker} popup.
 *
 */
public class ColorButton extends JFXButton {

    private Color value;

    /**
     * 
     * Class constructor.
     * 
     * @param color
     *              Button color value.
     * 
     */
    public ColorButton(final Color color) {
        this.setBackground(new Background(new BackgroundFill(color.getPaint(), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setValue(color);
    }

    private void setValue(final Color value) {
        this.value = value;
    }

    /**
     * 
     * Getter of value.
     * 
     * @return
     *          Return button's color value.
     * 
     */
    public Color getValue() {
        return value;
    }

}
