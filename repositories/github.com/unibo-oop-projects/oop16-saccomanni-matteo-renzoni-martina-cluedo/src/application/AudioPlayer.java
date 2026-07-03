package application;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Class representing an audio player.
 */
public final class AudioPlayer {
    private static final float VOLUME_REDUCE = -18f;
    private Clip clip;
    private boolean mute;

    /**
     * Constructor.
     * 
     * @param filePath
     *            path of the file to play
     */
    public AudioPlayer(final String filePath) {
        try {
            final AudioInputStream is = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource(filePath));
            clip = AudioSystem.getClip();
            clip.open(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Play the music or the sound using thread.
     * 
     * 
     * @param loop
     *            flag to determine if music should be played loop
     */
    public void play(final boolean loop) {
        new Thread() {
            public void run() {
                if (loop) {
                    final FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    volume.setValue(VOLUME_REDUCE);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    clip.start();
                }
            }
        }.start();
    }

    /**
     * Set mute if there's volume, set volume if it's mute.
     */
    public void changeMute() {
        this.mute = !this.mute;
        if (this.mute) {
            clip.stop();
        } else {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Returns if the music is activated or not.
     * 
     * @return true if it's muted, false otherwise
     */
    public boolean isMute() {
        return this.mute;
    }
}