package thatlevelagain.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 */
public class Sound {
    private Clip clip;
    private final String path;
    /**
     * 
     * @param path
     *          the path of the sound to reproduce.
     */
    public Sound(final String path) {
        this.path = path;
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
            final AudioFormat unformattedAudio = audioInputStream.getFormat();
            final AudioFormat formattedAudio = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, unformattedAudio.getSampleRate(), 16, 
                    unformattedAudio.getChannels(), unformattedAudio.getChannels() * 2, unformattedAudio.getSampleRate(), false);
            final AudioInputStream formattedAudioInputStream = AudioSystem.getAudioInputStream(formattedAudio, audioInputStream);
            clip = AudioSystem.getClip();
            clip.open(formattedAudioInputStream);
        } catch (Exception e) {
            System.out.println("Unable to load the sound " + this.path + "\nERROR: " + e.toString());
        }
    }

    /**
     * Manage the start of sound effect.
     *
     */
    public void play() {
        try {
            stopClip();
            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
            System.out.println("Unplayable sound: " + this.path + "\nERROR: " + e.toString());
        }
    }

    /**
     * Manage the stop of sound effect.
     *
     */
    public void stopClip() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    /**
    *   @return 
    *           current Clip
    */
   public Clip getClip() {
       return clip;
   }
}
