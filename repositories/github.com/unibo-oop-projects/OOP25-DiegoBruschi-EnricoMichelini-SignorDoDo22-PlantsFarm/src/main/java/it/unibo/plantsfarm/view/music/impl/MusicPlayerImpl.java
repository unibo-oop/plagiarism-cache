package it.unibo.plantsfarm.view.music.impl;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import it.unibo.plantsfarm.view.music.api.MusicPlayer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the background music and sound effects.
 */
public class MusicPlayerImpl implements MusicPlayer {

    private static final float MUSIC_VOLUME = -15.0f;
    private static final float SOUND_VOLUME = -5.0f;
    private static final Logger LOGGER = Logger.getLogger(MusicPlayerImpl.class.getName());

    private Clip backgroundMusic;

    /**
     * Plays the specified music file in a loop.
     *
     * @param fileName The name of the file.
     */
    @Override
    public void playLoop(final String fileName) {
        try {
            final Clip clip = loadClip(fileName);
            if (clip != null) {
                this.backgroundMusic = clip;
                setVolume(this.backgroundMusic, MUSIC_VOLUME);
                this.backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
                this.backgroundMusic.start();
            }
        } catch (final IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            LOGGER.log(Level.SEVERE, "Error playing background music", e);
        }
    }

    /**
     * Plays a sound effect once.
     *
     * @param fileName The name of the file.
     */
    @Override
    public void playEffect(final String fileName) {
        try {
            final Clip effectClip = loadClip(fileName);
            if (effectClip != null) {
                setVolume(effectClip, SOUND_VOLUME);
                effectClip.start();
            }
        } catch (final IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            LOGGER.log(Level.WARNING, "Error playing sound effect", e);
        }
    }

    private Clip loadClip(final String fileName) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        final InputStream audio = MusicPlayerImpl.class.getResourceAsStream("/" + fileName);
        if (audio == null) {
            LOGGER.severe("Audio file not found: " + fileName);
            return null;
        }
        final InputStream bufferedAudio = new BufferedInputStream(audio);
        final AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedAudio);
        final Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }

    private void setVolume(final Clip clip, final float decibels) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            final FloatControl audioController = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            audioController.setValue(decibels);
        }
    }

    /**
     * Stops the background music.
     */
    @Override
    public void stop() {
        if (this.backgroundMusic != null && this.backgroundMusic.isRunning()) {
            this.backgroundMusic.stop();
        }
    }
}
