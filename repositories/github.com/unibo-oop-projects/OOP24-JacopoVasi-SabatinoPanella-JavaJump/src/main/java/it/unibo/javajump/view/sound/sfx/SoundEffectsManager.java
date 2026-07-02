package it.unibo.javajump.view.sound.sfx;

/**
 * Interface that describes the manager of sound effects.
 */
public interface SoundEffectsManager {
    /**
     * Plays a requested sound.
     *
     * @param type the type of sound requested
     */
    void playSound(SFXType type);

}
