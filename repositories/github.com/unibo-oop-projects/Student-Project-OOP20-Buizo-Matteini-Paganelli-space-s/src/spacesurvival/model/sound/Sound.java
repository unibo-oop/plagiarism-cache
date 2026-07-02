package spacesurvival.model.sound;

import spacesurvival.utilities.SoundUtils;
import spacesurvival.utilities.path.SoundPath;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Abstract class Sound.
 */
public abstract class Sound {
    private AudioInputStream audioInputStream;
    //private Optional<Clip> clip = Optional.empty();
    private Clip clip;
    private SoundPath soundPath;
    private double volume;
    private boolean isPlaying;


    public Sound() {
        this.soundPath = null;
        this.volume = SoundUtils.STARTING_VOLUME;
    }

    /**
     * Create a sound.
     * 
     * @param sound the sound path
     */
    public Sound(final SoundPath sound) {
        this.soundPath = sound;
        this.volume = SoundUtils.STARTING_VOLUME;
        initializeSound(sound);
    }

    /**
     * Initialize the sound from the passed sound path.
     * 
     * @param sound the sound path
     */
    private void initializeSound(final SoundPath sound) {

        audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(ClassLoader.getSystemResource(sound.getPath()));
            setClip(AudioSystem.getClip());
            getClip().open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /** 
     * Return the sound type composed by the name, the path of the file wav and the specified type like effect or loop.
     * 
     * @return the sound type rappresenting the currrent sound.
     */
    public SoundPath getSoundType() {
       return this.soundPath;
    }

    /** 
     * Set the current sound type. 
     * 
     * @param sound the soundType rappresenting the sound to be set.
     */
    public void setSoundType(final SoundPath sound) {
        this.soundPath = sound;
    }

    /** 
     * Return the clip of the current sound.
     * 
     * @return clip of the current sound.
     */
    public Clip getClip() {
        return this.clip;
    }

    /** 
     * Set the clip of the current sound.
     * 
     * @param clip that will be set in the sound.
     */
    private void setClip(final Clip clip) {
        this.clip = clip;
    }

    /** 
     * Get the volume of sound.
     * 
     * @return volume the volume that is set on the sound.
     */
    public double getVolume() {
        return this.volume;
    }

    /** 
     * Set the volume of sound.
     * 
     * @param volume the volume that will be set on the sound.
     */
    public void setVolume(final double volume) {
        this.volume = volume;
        final FloatControl gain = (FloatControl) getClip().getControl(FloatControl.Type.MASTER_GAIN);
        final float dB = (float) (Math.log(volume) / Math.log(10) * SoundUtils.AMPLITUDE_MULTIPLICATOR_FACTOR);

        gain.setValue(dB);
    }

    /** 
     * Start the clip of the current sound.
     * 
     */
    public void startClip() {
        playSound(this.volume);
        this.isPlaying = true;
    }

    /** 
     * Stop the clip of the current sound.
     * 
     */
    public void stopClip() {
        this.clip.stop();
        try {
            audioInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.isPlaying = false;
    }

    /** 
     * Return if the clip of the current sound is playing.
     * 
     * @return true if is playing
     */
    public boolean isPlaying() {
        return this.isPlaying;
    }


    /** 
     * Method to be implemented for type loop or effect.
     * 
     * @param volume the volume that will be set before starting the sound.
     */
    protected abstract void playSound(double volume);


}
