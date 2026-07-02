package vg.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class AbstractSound {
    private static final int FRAME_INIT = 0;
    private Clip clip;

    protected  AbstractSound(final String pathSound) {
        this.changeSound(pathSound);
    }

    protected void playLoop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    protected void playOne() {
        this.clip.setFramePosition(FRAME_INIT);
        this.clip.start();
    }

    public void stop() {
        this.clip.stop();
    }
    public void changeSound(final String pathSound) {
        final URL url = ClassLoader.getSystemResource(pathSound);
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

}
