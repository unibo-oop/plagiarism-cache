package spacesurvival.model.gui.sound;

import spacesurvival.utilities.SoundUtils;

/**
 * Implements the model for the unit sound.
 */
public class EngineUnitSound {
    private int valueSound;
    private StateSlider stateSlider;

    /**
     * Create a model for the unit of sound.
     */
    public EngineUnitSound() {
        this.valueSound = SoundUtils.DEFAULT_VALUE_SOUND;
        this.stateSlider = StateSlider.ON;
    }

    /**
     * Get value of unit sound.
     * @return value unit sound.
     */
    public int getValueSound() {
        return this.valueSound;
    }

    /**
     * Set value of unit sound.
     * @param valueSound for set value sound.
     */
    public void setValueSound(final int valueSound) {
        this.valueSound = valueSound;
    }

    /**
     * Get StateSlider of unit sound.
     * @return StateSlider of unit sound.
     */
    public StateSlider getStateSlider() {
        return this.stateSlider;
    }

    /**
     * Set StateSlider of unit sound.
     * @param stateSlider of unit sound.
     */
    public void setStateSlider(final StateSlider stateSlider) {
        this.stateSlider = stateSlider;
    }

    /**
     * Get path of StateSlider.
     * @return path of StateSlider.
     */
    public String getPathIconState() {
        return this.stateSlider.getEngineImage().getPath();
    }

    /**
     * Get boolean if state if active.
     * @return boolean state if active.
     */
    public boolean isActiveSlider() {
        return this.stateSlider.isActive();
    }

}
