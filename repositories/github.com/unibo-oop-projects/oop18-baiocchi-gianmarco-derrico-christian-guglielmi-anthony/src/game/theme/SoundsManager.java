package game.theme;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import sounds.Sound;
import sounds.SoundImpl;

/**
 * Class to store and get any sound.
 */
public class SoundsManager {

    private final Sound walking;
    private final Sound music;
    private final Sound door;
    private final Sound explosion;
    private final Sound select;

    /**
     * Constructor and initializer of all sounds.
     * @param folder : theme folder where to take the sounds
     * @throws UnsupportedAudioFileException : wrong audio file format
     * @throws IOException : problem during input/output
     * @throws LineUnavailableException : audio line can't be opened because it is unavailable
     */
    public SoundsManager(final String folder) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.walking = new SoundImpl("/walking.wav");
        this.music = new SoundImpl(folder + "/music.wav");
        this.door = new SoundImpl("/door.wav");
        this.explosion = new SoundImpl("/explosion.wav");
        this.select = new SoundImpl("/select.wav");
    }

    /**
     * Getter for walking sound.
     * @return walking sound
     */
    public Sound getWalkingSound() {
        return this.walking;
    }

    /**
     * Getter for music in game.
     * @return music sound
     */
    public Sound getMusicSound() {
        return this.music;
    }

    /**
     * Getter for opening door sound.
     * @return opening door sound
     */
    public Sound getDoorSound() {
        return this.door;
    }

    /**
     * Getter for explosion sound.
     * @return explosion sound
     */
    public Sound getExplosionSound() {
        return this.explosion;
    }

    /**
     * Getter for selection sound.
     * @return selection sound
     */
    public Sound getSelectSound() {
        return this.select;
    }
}
