package it.oop.project.sound;

import it.oop.project.util.ResourceLoader;
import it.oop.project.util.ResourceLoaderImpl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * {@inheritDoc}
 */
public class SoundPlayerImpl implements SoundPlayer {

    private enum Sound {
        SHIFT("Blop.wav"), 
        NO_SHIFT("Thud.wav"), 
        GAME_WON("Ting.wav"), 
        GAME_OVER("Failure.wav");

        private String SOUND_FOLDER = "sounds/";
        private final String path;

        private Sound(final String path) {
            this.path = SOUND_FOLDER + path;
        }

    }

    private final Map<Sound, URL> soundMap;
    private final ResourceLoader loader;

    public SoundPlayerImpl() {
        this.soundMap = new HashMap<>();
        loader = new ResourceLoaderImpl();
        for (Sound s : Sound.values()) {
            this.soundMap.put(s, loader.load(s.path));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playShift() {
        playSound(this.soundMap.get(Sound.SHIFT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playNoShift() {
        playSound(this.soundMap.get(Sound.NO_SHIFT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playGameWon() {
        playSound(this.soundMap.get(Sound.GAME_WON));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playGameOver() {
        playSound(this.soundMap.get(Sound.GAME_OVER));
    }

    private void playSound(URL sound) {
        try {
            InputStream bufferedIn = new BufferedInputStream(
                    sound.openStream());
            AudioInputStream audioStream = AudioSystem
                    .getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
