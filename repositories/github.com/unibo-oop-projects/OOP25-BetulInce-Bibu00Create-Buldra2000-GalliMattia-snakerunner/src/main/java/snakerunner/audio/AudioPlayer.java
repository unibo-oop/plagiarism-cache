package snakerunner.audio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineEvent.Type;

/**
 * AudioPlayer Class for PlaySound in game.
 */
public final class AudioPlayer {

    private static final Logger LOGGER = Logger.getLogger(AudioPlayer.class.getName());
    private static boolean soundEnable = true;

    private AudioPlayer() { } //Empty constructor

    /**
     * Enables or disables sound in the game.
     * 
     * @param enable true to enable sound, false to disable it.
     */
    public static void setSoundEnabled(final boolean enable) {
        soundEnable = enable;
    }

    /**
     * Check sound is currently enabled.
     * 
     * @return true if the sound is enabled, false otherwise.
     */
    public static boolean isSoundEnable() { 
        return soundEnable;
    }

    /**
     * Play a sound file from resource directory.
     * The sound is played asynchronously and the clip automatically closed.
     * 
     * @param fileName the name of the sound file to play.
     */
    public static void playSound(final String fileName) {
    if (!soundEnable) {
        return;
    }
    try {
        final byte[] audioData;
        try (InputStream sound = AudioPlayer.class.getResourceAsStream("/" + fileName)) {
            if (sound == null) {
                LOGGER.log(Level.WARNING, "Sound file not found: {0}", fileName);
                return;
            }
            audioData = sound.readAllBytes();
        }

        final ByteArrayInputStream byteStream = new ByteArrayInputStream(audioData);

        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(byteStream)) {
            final Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            clip.addLineListener(event -> {
                if (event.getType() == Type.STOP) {
                    clip.close();
                }
            });
        }

    } catch (final IOException | UnsupportedAudioFileException | LineUnavailableException e) {
        LOGGER.log(Level.SEVERE, "Error playing sound: {0}", fileName);
        LOGGER.log(Level.SEVERE, "Exception: ", e);
    }
}
}
