package sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class implements the interface {@link Sound}.
 */
public final class SoundImpl implements Sound {

    private boolean isEnabled;

    /**
     * Constructor of class SoundImpl. 
     * It is used to initialize the field.
     */
    public SoundImpl() {
        this.isEnabled = true;
    }

    @Override
    public void play(final String soundName) {
        if (this.isEnabled) {
            try {
                // create URL
                final URL url = ClassLoader.getSystemResource(soundName);
                // new clip
                final Clip clip;
                // create and open the audio input stream
                try (AudioInputStream inputSound = AudioSystem.getAudioInputStream(url)) {
                    clip = AudioSystem.getClip();
                    // open the clip
                    clip.open(inputSound);
                    clip.start();

                }

            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setEnable(final boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

}
