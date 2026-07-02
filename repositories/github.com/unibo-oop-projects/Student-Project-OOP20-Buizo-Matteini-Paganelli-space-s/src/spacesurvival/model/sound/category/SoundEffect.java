package spacesurvival.model.sound.category;

import spacesurvival.model.sound.Sound;
import spacesurvival.utilities.path.SoundPath;

/**
 * 
 * Class for sound effect, each sound will terminate.
 *
 */
public class SoundEffect extends Sound {

    /**
     * Create an empty sound effect.
     */
    public SoundEffect() {
        super();
    }

    /**
     * Create a sound effect from the passed sound path.
     * 
     * @param soundPath the path of the sound
     */
    public SoundEffect(final SoundPath soundPath) {
        super(soundPath);
    }

    /** 
     * Start the sound effect at the current volume.
     * @param volume the volume at which the sound effect will start in the range 0-100.
     */
    protected void playSound(final double volume) {
        super.getClip().start();
        setVolume(volume);
    }
}
