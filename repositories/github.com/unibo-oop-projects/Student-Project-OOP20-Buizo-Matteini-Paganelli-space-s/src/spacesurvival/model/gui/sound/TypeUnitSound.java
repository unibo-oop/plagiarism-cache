package spacesurvival.model.gui.sound;

/**
 * Implements a sound unit identifier.
 */
public enum TypeUnitSound {
    /**
     * Type Unit sound main.
     */
    SLIDER_BACKGROUND("Main"),

    /**
     * Type Unit sound effect.
     */
    SLIDER_EFFECT("Effect");

    private final String name;

    /**
     * Create TypeUnitSound from name.
     * @param name for type unit sound.
     */
    TypeUnitSound(final String name) {
        this.name = name;
    }

    /**
     * Get name's unit sound.
     * @return String.
     */
    public String getNameUnitSound() {
        return this.name;
    }
}
