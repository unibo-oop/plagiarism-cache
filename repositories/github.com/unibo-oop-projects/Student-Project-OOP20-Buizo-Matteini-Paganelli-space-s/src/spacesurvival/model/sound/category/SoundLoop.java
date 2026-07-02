package spacesurvival.model.sound.category;

import spacesurvival.model.sound.Sound;
import spacesurvival.utilities.path.SoundPath;

import javax.sound.sampled.Clip;

/**
 * 
 * Class for sound loop, each sound will not terminate.
 *
 */
public class SoundLoop extends Sound {

    /**
     * Create an empty sound loop.
     */
    public SoundLoop() {
        super();
    }

    /**
     * Create a sound loop from the passed sound path.
     * 
     * @param soundPath the sound path
     */
    public SoundLoop(final SoundPath soundPath) {
        super(soundPath);
    }

    /** 
     * Start the sound loop at the current volume.
     * @param volume the volume at which the sound loop will start in the range 0-100.
     */
    protected void playSound(final double volume) {
        setVolume(volume);
        super.getClip().loop(Clip.LOOP_CONTINUOUSLY);
        super.getClip().start();
    }
}
