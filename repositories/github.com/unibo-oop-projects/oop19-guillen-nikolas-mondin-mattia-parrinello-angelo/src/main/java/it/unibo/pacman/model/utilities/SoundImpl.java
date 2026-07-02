package it.unibo.pacman.model.utilities;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class that stores a sound, and plays it.
 */
public class SoundImpl implements Sound {
    private static final int FRAME_SONG_START = 0;
    private final Clip clip;

    /**
     * Creates {@code SoundImpl}.
     *
     * @param path from where loads the sounds.
     * @throws UnsupportedAudioFileException wrong audio file format
     * @throws IOException problem during input/output
     * @throws LineUnavailableException audio line can't be opened because it's unavailable
     */
    public SoundImpl(final String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.clip = AudioSystem.getClip();
        this.clip.open(AudioSystem.getAudioInputStream(this.getClass().getResource(path)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void play() {
        this.clip.stop();
        this.clip.setFramePosition(FRAME_SONG_START);
        this.clip.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        this.clip.stop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlaying() {
        return this.clip.isRunning();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playInLoop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}
