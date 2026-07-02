package spacesurvival.view.sound;

import spacesurvival.model.gui.sound.TypeUnitSound;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.sound.utilities.ButtonSliderType;
import spacesurvival.view.sound.utilities.SliderType;
import spacesurvival.view.utilities.GraphicsText;

import java.awt.Font;
import java.util.List;
import java.util.Optional;

/**
 * Interface that implements the sound functionalities.
 */
public interface GUISound extends GUI, GraphicsText {

    /**
     * Set text a back button.
     * @param textBtnBack for button.
     */
    void setTextButtonBack(String textBtnBack);

    /**
     * Set text a list of TypeUnitSound.
     * @param listText for list of TypeUnitSound.
     */
    void setTypeUnitSound(List<TypeUnitSound> listText);

    /**
     * Set title a all TypeUnitSound.
     * @param listTitle for all TypeUnitSound.
     */
    void setTitleUnitSound(List<String> listTitle);

    /**
     * Get a list of TypeUnitSound.
     * @return SliderType is unit sound.
     */
    List<SliderType> getSlidersSound();

    /**
     * Get slider from the mixer from the unit type if present.
     * @param typeUnitSound is type of unit sound.
     * @return SliderType is unit sound.
     */
    Optional<SliderType> getSliderTypeofMixer(TypeUnitSound typeUnitSound);

    /**
     * Get button from the mixer from the unit type if present.
     * @param typeUnitSound is type of unit sound.
     * @return ButtonSliderType is button switch for unit sound.
     */
    Optional<ButtonSliderType> getBtnSwitch(TypeUnitSound typeUnitSound);

    /**
     * Set value a mixer sound, set value all unit sound.
     * @param value for set.
     */
    void setValueMixerSound(int value);

    /**
     * Set linkAction a back buttonLink.
     * @param mainAction is linkAction a current GUI.
     * @param linkAction for link previous GUI.
     */
    void setActionBtnBack(LinkActionGUI mainAction, LinkActionGUI linkAction);

    /**
     * Get a list of switches button.
     * @return List of ButtonSliderType
     */
    List<ButtonSliderType> getBtnSwitches();

    /**
     * Set iconImage for buttons switches sound.
     * @param path for iconImage.
     */
    void setIconBtnSwitches(List<String> path);

    /**
     * Set font spacing slider.
     * @param font for spacing slider.
     */
    void setFontSpacingSlider(Font font);

}
