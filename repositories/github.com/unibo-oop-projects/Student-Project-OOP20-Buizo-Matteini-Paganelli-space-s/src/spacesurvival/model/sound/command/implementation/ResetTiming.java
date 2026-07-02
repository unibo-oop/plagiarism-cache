package spacesurvival.model.sound.command.implementation;

import spacesurvival.model.sound.Sound;
import spacesurvival.model.sound.command.CommandAudio;

/**
 * Command reset time for the sound.
 */
public class ResetTiming implements CommandAudio {

    /** 
     * Reset the timing of the passed sound.
     * @param sound the sound on which the timing will be reset.
     * 
     */
    public void execute(final Sound sound) {
        sound.getClip().setMicrosecondPosition(0);
    }
}
