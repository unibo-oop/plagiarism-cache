package it.unibo.roguekong.controller;

import javax.sound.sampled.*;

/**
 * This class will be used to play sounds in the game loop
 */
public class SoundManager {
    private final float volume;
    private Clip clip;
    private final String soundPath;

    /**
     * Create a new SoundManager. Must be used for every new sound
     * @param musicPath sound resource path
     * @param volume of the sound
     */
    public SoundManager(final String musicPath, final float volume) {
        this.soundPath = musicPath;
        this.volume = volume;
    }

    public Clip getClip() { return this.clip; }

    public float getVolume() { return this.volume; }

    /**
     * Use this to play the music for the first time
     */
    public void play() {
        try{
            // Check if the sound is already running
            if (this.clip != null && this.clip.isRunning()) {
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(this.soundPath));

            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);


            // This part right below, must be used to change the volume sound
            if (this.clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gain = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
                gain.setValue(this.volume);
            }

            this.clip.setFramePosition(0);

            this.clip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * When the game loop is set on pause, even the music need to be set on pause
     */
    public void stop() {
        if(this.clip != null && this.clip.isRunning()) {
            this.clip.stop();
        }
    }

    /**
     * When the game loop is set on resume, even the music need to be set on resume. So restart the sound where it got interrupted
     */
    public void restart() {
        if(this.clip != null && !this.clip.isRunning()) {
            this.clip.start();
        }
    }

    /**
     * When a sound must be repeated continuously, call this method it will loop till the game will be set on pause or quit
     */
    public void loop() {
        this.play();
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
