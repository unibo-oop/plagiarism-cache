package spacesurvival.view.sound.utilities;


import spacesurvival.model.gui.sound.TypeUnitSound;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.view.utilities.FactoryGUIs;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Implements a sound unit for controlling the user's view part, associating a typerSlider to the components. 
 */
public class UnitSound extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JLabel lbTitle;
    private final SliderType sliderSound;
    private final ButtonSliderType btnSwitch;

    /**
     * Builds a sound unit by initializing its components and graphics's components.
     */
    public UnitSound() {
        super(new BorderLayout());
        super.setOpaque(false);
        this.lbTitle = new JLabel();
        this.sliderSound = new SliderType();
        this.btnSwitch = new ButtonSliderType();
        this.graphics();
    }

    /**
     * Create graphics's component of the unit sound.
     */
    private void graphics() {
        FactoryGUIs.setTransparentJButton(this.btnSwitch);
        FactoryGUIs.setDefaultJSlider(this.sliderSound);
        super.add(FactoryGUIs.encapsulatesInPanelFlow(this.lbTitle), BorderLayout.NORTH);
        super.add(FactoryGUIs.createPanelFlowUnionComponents(java.util.List.of(this.btnSwitch, this.sliderSound)), BorderLayout.CENTER);
    }

    /**
     * Return the sound slider.
     * 
     * @return a slider type for the sound
     */
    public SliderType getSliderSound() {
        return this.sliderSound;
    }

    /**
     * Set the color of the foreground unit.
     * 
     * @param color color to set
     */
    public void setForegroundUnit(final Color color) {
        this.lbTitle.setForeground(color);
        this.sliderSound.setForeground(color);
    }

    /**
     * Set the font of the title unit.
     * 
     * @param font font to set
     */
    public void setFontTitleUnit(final Font font) {
        this.lbTitle.setFont(font);
    }

    /**
     * Set the font of the slider.
     * 
     * @param font font to set
     */
    public void setFontSlider(final Font font) {
        this.sliderSound.setFont(font);
    }

    /**
     * Set the text in the title label.
     * 
     * @param lbTitle text to set
     */
    public void setLbTitle(final String lbTitle) {
        this.lbTitle.setText(lbTitle);
    }

    /**
     * Return the type of the slider.
     *
     * @return the type of the slider
     */
    public TypeUnitSound getType() {
        return this.sliderSound.getType();
    }

    /**
     * Set the type in the slider sound.
     * 
     * @param typeUnitSound type to set
     */
    public void setType(final TypeUnitSound typeUnitSound) {
        this.sliderSound.setType(typeUnitSound);
        this.btnSwitch.setTypeSlider(typeUnitSound);
    }

    /**
     * Set the value in the slider.
     * 
     * @param value value to set
     */
    public void setValueSliderSound(final int value) {
        this.sliderSound.setValue(value);
    }

    /**
     * 
     * @return the switch button.
     */
    public ButtonSliderType getBtnSwitch() {
        return this.btnSwitch;
    }

    /**
     * Set icon to the switch button.
     * 
     * @param path path of the icon to set
     * @param widthScreen width of the screen
     */
    public void setIconBtnSwitch(final String path, final int widthScreen) {
        FactoryGUIs.setIconJButtonFromRate(this.btnSwitch, path, ScaleOf.ICON_MEDIUM, widthScreen);
    }
}
