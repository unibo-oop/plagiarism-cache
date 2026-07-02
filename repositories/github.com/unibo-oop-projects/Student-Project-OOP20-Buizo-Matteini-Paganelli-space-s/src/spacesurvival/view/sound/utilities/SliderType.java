package spacesurvival.view.sound.utilities;

import spacesurvival.model.gui.sound.TypeUnitSound;
import javax.swing.JSlider;

/**
 * Implement a slider extending from JSlider by adding a typeSlider recognition parameter.
 */
public class SliderType extends JSlider {
    private static final long serialVersionUID = 1L;
    private TypeUnitSound typeSlider;

    /**
     * Return the type of the slider.
     *
     * @return the type of the slider
     */
    public TypeUnitSound getType() {
        return this.typeSlider;
    }

    /**
     * Set the type of the slider.
     * 
     * @param typeSlider the slider to be setted
     */
    public void setType(final TypeUnitSound typeSlider) {
        this.typeSlider = typeSlider;
    }

    /**
     * Describes SliderType from the associated typeSlider.
     */
    @Override
    public String toString() {
        return "SliderType{" 
               + "typeSlider=" + typeSlider 
               + '}';
    }
}
