package spacesurvival.model.gui.sound;

import spacesurvival.model.EngineImage;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.path.Icon;

/**
 * Implements the change of state of a sound unit and associates an image with a state.
 */
public enum StateSlider {
    /**
     * The State On of the slider composed by the icon dimension, a rectangle and the sound on icon.
     */
    ON(true, new EngineImage(ScaleOf.ICON_MEDIUM, EngineSound.RECTANGLE.width, Icon.SOUND_ON)),

    /**
     * The State Off of the slider composed by the icon dimension, a rectangle and the sound off icon.
     */
    OFF(false, new EngineImage(ScaleOf.ICON_MEDIUM,  EngineSound.RECTANGLE.width, Icon.SOUND_OFF));

    private final boolean state;
    private final EngineImage engineImage;

    /**
     * Creates states for activation and icon change depending on the state.
     * @param state enumeration.
     * @param engineImage enumeration.
     */
    StateSlider(final boolean state, final EngineImage engineImage) {
        this.state = state;
        this.engineImage = engineImage;
    }

    /**
     * Check if its status is active.
     * @return state if active.
     */
    public boolean isActive() {
        return this.state;
    }

    /**
     * Get engineImage.
     * @return engineImage.
     */
    public EngineImage getEngineImage() {
        return this.engineImage;
    }

    /**
     * Describes enumeration from state and engineImage. 
     */
    @Override
    public String toString() {
        return "StateSlider{" 
                + "state = " + state 
                + ", engineImage=" + engineImage + '}';
    }
}
