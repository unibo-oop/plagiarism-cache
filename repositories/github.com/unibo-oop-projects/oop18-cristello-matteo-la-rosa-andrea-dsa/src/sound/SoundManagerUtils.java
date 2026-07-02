package sound;


import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This is a simple sound manager.
 */

public final class SoundManagerUtils {

    private SoundManagerUtils() {

    }

    /**
     * This method allow to play a sound giving to player the address of sound.
     * 
     * @param soundAddress
     *                         The address of sound.
     * @throws LineUnavailableException
     *                                           Line cannot be opened because it is unavailable.
     * @throws IOException
     *                                           Failed or interrupted I/O operations.
     * @throws UnsupportedAudioFileException
     *                                           If the audio file is not of the right kind.
     */
    public static synchronized void playSound(final String soundAddress)
            throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        final BufferedInputStream bufferedInput = new BufferedInputStream(
                SoundManagerUtils.class.getResourceAsStream(soundAddress));
        final Clip clip = AudioSystem.getClip();
        final AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedInput);
        clip.open(inputStream);
        clip.start();
        clip.addLineListener(new LineListener() {
            public void update(final LineEvent myLineEvent) {
              if (myLineEvent.getType() == LineEvent.Type.STOP) {
                clip.close();
              }
            }
          });
    }

}
