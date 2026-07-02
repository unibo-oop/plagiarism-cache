package spacesurvival.model.sound.command.implementation;

import spacesurvival.model.sound.Sound;
import spacesurvival.model.sound.command.CommandAudio;

/**
 * Command Stop for the sound.
 */
public class StopSound implements CommandAudio {

    /** 
     * Stop the passed sound.
     * @param sound the sound that will stop.
     * 
     */
    public void execute(final Sound sound) {
        sound.stopClip();
    }
}
