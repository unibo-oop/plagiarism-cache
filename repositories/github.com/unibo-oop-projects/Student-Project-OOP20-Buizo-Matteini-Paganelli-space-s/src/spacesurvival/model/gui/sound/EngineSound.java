package spacesurvival.model.gui.sound;

import spacesurvival.model.gui.EngineGUI;
import spacesurvival.model.gui.Visibility;
import spacesurvival.model.EngineImage;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.utilities.LinkActionGUI;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

/**
 * Implements the model for the sound GUI.
 */
public class EngineSound implements EngineGUI {

    /**
     * Dimension of the rectangle for the GUI.
     */
    public static final Rectangle RECTANGLE = Screen.RECTANGLE_MEDIUM;

    /**
     * Title of the GUI.
     */
    public static final String TITLE = "SOUND";

    /**
     * Number of unit of sound of the GUI.
     */
    public static final int N_UNIT_SOUND = 2;

    private final LinkActionGUI id;
    private final LinkActionGUI idBack;
    private final EngineMixerSound mixerSound;
    private Visibility visibility;

    /**
     * Create a model for the GUI sound.
     */
    public EngineSound() {
        this.id = LinkActionGUI.LINK_SOUND;
        this.idBack = LinkActionGUI.LINK_BACK;
        this.mixerSound = new EngineMixerSound(Arrays.asList(TypeUnitSound.values()));

        this.visibility = Visibility.HIDDEN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkActionGUI getMainLink() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getRectangle() {
        return RECTANGLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisibility(final Visibility visibility) {
        this.visibility = visibility;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisible() {
        return this.visibility.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LinkActionGUI> getLinks() {
        return List.of(this.idBack);
    }

    /**
     * Return the title of the GUI.
     * 
     * @return the title
     */
    public String getTitle() {
        return TITLE;
    }

    /**
     * Return the back link of this GUI.
     * 
     * @return an ActionGUI to go back from this GUI
     */
    public LinkActionGUI getBackLink() {
        return this.idBack;
    }

    /**
     * Get text for button back.
     * 
     * @return string of text.
     */
    public String getTextBtnBack() {
        return this.idBack.getIdName();
    }

    /**
     * Get a list of text for unit sound.
     * 
     * @return List of String  a list of text for unit sound.
     */
    public List<String> getListTextSlider() {
        return this.mixerSound.getTextUnitsSound();
    }

    /**
     * Get a list of TypeUnitSound of unit sound.
     * 
     * @return List of TypeUnitSound a list of TypeUnitSound.
     */
    public List<TypeUnitSound> getListTypeUnitSound() {
        return this.mixerSound.getListTypeUnitsSound();
    }

    /**
     * Get value of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound for search unit sound
     * @return value unit sound.
     */
    public int getValueUnitSound(final TypeUnitSound typeUnitSound) {
        return this.mixerSound.getValueSound(typeUnitSound);
    }

    /**
     * Set value of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound for search unit sound.
     * @param value for set value of unit sound.
     */
    public void setValueUnitSound(final TypeUnitSound typeUnitSound, final int value) {
        this.mixerSound.setValueSound(typeUnitSound, value);
    }

    /**
     * Get StateSlider of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound for search unit sound.
     * @return StateSlider of unit sound.
     */
    public StateSlider getStateUnitSound(final TypeUnitSound typeUnitSound) {
        return this.mixerSound.getStateSound(typeUnitSound);
    }

    /**
     * Set StateSlider of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound for search unit sound.
     * @param stateUnitSound for set value of unit sound.
     */
    public void setStateUnitSound(final TypeUnitSound typeUnitSound, final StateSlider stateUnitSound) {
        this.mixerSound.setStateSound(typeUnitSound, stateUnitSound);
    }

    /**
     * Get boolean if unit sound active.
     * 
     * @param typeUnitSound for search unit sound.
     * @return boolean.
     */
    public boolean isActiveUnitSound(final TypeUnitSound typeUnitSound) {
        return this.mixerSound.isActiveSound(typeUnitSound);
    }

    /**
     * Get EngineImage of unit sound from TypeUnitSound.
     * @param typeUnitSound for search unit sound.
     * @return EngineImage for model image.
     */
    public EngineImage getEngineImageUnitSound(final TypeUnitSound typeUnitSound) {
        return this.mixerSound.getStateSound(typeUnitSound).getEngineImage();
    }

    /**
     * Change StateSlider of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound for search unit sound.
     */
    public void changeStateUnitSound(final TypeUnitSound typeUnitSound) {
        this.mixerSound.changeStateSound(typeUnitSound);
    }

    /**
     * Get String of path image of unit sound from TypeUnitSound.
     * 
     * @param typeUnitSound  for search unit sound.
     * @return String for image.
     */
    public String getPathImageUnitSound(final TypeUnitSound typeUnitSound) {
        return this.mixerSound.getPathIconState(typeUnitSound);
    }

    /**
     * Get a list of String of path image of unit sound.
     * 
     * @return List of string of path image
     */
    public List<String> getIconStateSounds() {
        return this.mixerSound.getPathsIconState();
    }
}
