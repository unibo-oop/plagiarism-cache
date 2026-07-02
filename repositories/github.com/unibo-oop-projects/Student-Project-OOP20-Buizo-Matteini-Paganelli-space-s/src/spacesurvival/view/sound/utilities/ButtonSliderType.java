package spacesurvival.view.sound.utilities;

import spacesurvival.model.gui.sound.TypeUnitSound;

import javax.swing.JButton;

/**
 * Implement a button extending from JButton by adding a typeSlider recognition parameter.
 */
public class ButtonSliderType extends JButton {
    private static final long serialVersionUID = 1L;
    private TypeUnitSound typeSlider;

    /**
     * Get typeUnitSound's button.
     * @return TypeUnitSound of button.
     */
    public TypeUnitSound getTypeSlider() {
        return typeSlider;
    }

    /**
     * Set TypeUnitSound a button.
     * @param typeSlider
     */
    public void setTypeSlider(final TypeUnitSound typeSlider) {
        this.typeSlider = typeSlider;
    }

    /**
     * Describes the type of the button.
     */
    @Override
    public String toString() {
        return "ButtonSliderType{" 
                + "typeSlider=" + typeSlider + '}';
    }
}
