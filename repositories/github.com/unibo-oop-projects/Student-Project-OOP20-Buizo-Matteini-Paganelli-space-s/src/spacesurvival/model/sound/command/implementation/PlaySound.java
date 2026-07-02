package spacesurvival.model.sound.command.implementation;

import spacesurvival.model.sound.Sound;
import spacesurvival.model.sound.command.CommandAudio;

/**
 * Command Play for the sound.
 */
public class PlaySound implements CommandAudio {

    /** 
     * Start the passed sound.
     * @param sound the sound that will start.
     * 
     */
    public void execute(final Sound sound) {
        sound.startClip();
    }
}
