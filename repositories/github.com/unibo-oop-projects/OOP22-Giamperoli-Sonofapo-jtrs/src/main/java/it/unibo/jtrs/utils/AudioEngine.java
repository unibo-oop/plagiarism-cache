package it.unibo.jtrs.utils;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * A simple audio engine that makes possible to load a track from a file, play,
 * pause and resume as needed.
 */
public final class AudioEngine {

    private static final float LOW_VOL_DB = -10.0f;

    private static Clip clip;
    private static boolean isMuted;

    private AudioEngine() { }

    /**
     * Loads a file into the engine and start playing it.
     *
     * @param file the file to load
     */
    public static void load(final String file) throws IOException {

        isMuted = false;

        try {
            final var stream = AudioSystem.getAudioInputStream(ResourceLoader.loadAsUrl(file));

            clip = AudioSystem.getClip();
            clip.open(stream);

            // lower volume
            ((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(LOW_VOL_DB);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new IOException("Audio load failure", e);
        }
    }

    /**
     * Terminates the playing track and closes its file.
     */
    public static void stop() {
        clip.stop();
        clip.close();
    }

    /**
     * Plays the current track if the audio engine is allowed to.
     */
    public static void play() {
        if (!isMuted) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Pauses the current playing track if the audio engine is allowd to.
     */
    public static void pause() {
        if (!isMuted) {
            clip.stop();
        }
    }

    /**
     * Mutes the audio engine. Play and pause function will no longer produce
     * an effect as long as unmute is called.
     */
    public static void mute() {
        isMuted = true;
        clip.stop();
    }

    /**
     * Unmutes the audio engine.
     */
    public static void unmute() {
        isMuted = false;
        play();
    }

    /**
     * Return the running status of the loaded clip.
     *
     * @return true if the clip is playing
     */
    public static boolean isPlaying() {
        return clip.isRunning();
    }

}
