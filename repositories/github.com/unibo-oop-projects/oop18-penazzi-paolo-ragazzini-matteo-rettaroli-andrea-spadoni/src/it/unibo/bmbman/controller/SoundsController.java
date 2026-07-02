package it.unibo.bmbman.controller;

import java.io.IOException;
import java.util.Optional;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import it.unibo.bmbman.model.utilities.Sound;
import it.unibo.bmbman.model.utilities.SoundImpl;


/**
 * Class that stores and load all the {@link Sound}.
 */
public class SoundsController {

    private static Optional<Sound> music;
    private static Optional<Sound> explosion;
    private static Optional<Sound> placeBomb;
    private static Optional<Sound> key;
    private static String folder = "/Sounds";
    private boolean musicOn = true;
    private boolean effectsOn = true;

    /**
     * Creates a {@code SoundsController} and load all sounds.
     */
    public SoundsController() {
            loadMusic();
            loadEffects();
    }
    /**
     * Gets the explosion sound.
     * @return explosion {@link Sound}
     */
    public static Optional<Sound> getExplosionSound() {
        return explosion;
    }
    /**
     * Gets the placedBomb sound.
     * @return placeBomb {@link Sound}
     */
    public static Optional<Sound> getPlaceBombSound() {
        return placeBomb;
    }
    /**
     * Gets the {@link Key} sounds. 
     * @return key {@link Sound}
     */
    public static Optional<Sound> getKeySound() {
        return key;
    }
    /**
     * Gets the music in game.
     *
     * @return music {@link Sound}
     */
    public static Optional<Sound> getMusicSound() {
        return music;
    }
    /**
     * method used to enable music sound.
     */
    public void setMusicdOn() {
        this.musicOn = true;
    }
    /**
     * method used to disable music sounds.
     */
    public void setMusicOff() {
        this.musicOn = false;
    }
    /**
     * Method used to enable effects sounds.
     */
    public void setEffectsOn() {
        this.effectsOn = true;
    }
    /**
     * Method to disable effects sounds.
     */
    public void setEffectsOff() {
        this.effectsOn = false;
    }
    /**
     * Method used to adjust changes according to the options selected during a game session.
     */
    public void setSounds() {
        if (!musicOn) {
            SoundsController.music = Optional.empty();
        } else {
            loadMusic();
        }
        if (!effectsOn) {
            SoundsController.explosion = Optional.empty();
            SoundsController.placeBomb = Optional.empty();
            SoundsController.key = Optional.empty();
        } else {
            loadEffects();
        }
    }
    /**
     * Loads the music.
     * @throws UnsupportedAudioFileException : wrong audio file format
     * @throws IOException : problem during input/output
     * @throws LineUnavailableException : audio line can't be opened because it is unavailable
     */
    private void loadMusic() {
        try {
            SoundsController.music = Optional.of(new SoundImpl(folder + "/music2.wav"));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /**
     *Loads the effects.
     * @throws UnsupportedAudioFileException : wrong audio file format
     * @throws IOException : problem during input/output
     * @throws LineUnavailableException : audio line can't be opened because it is unavailable
     */
    private void loadEffects() {
        try {
            SoundsController.explosion = Optional.of(new SoundImpl(folder + "/explosion.wav"));
            SoundsController.placeBomb = Optional.of(new SoundImpl(folder + "/placeBomb.wav"));
            SoundsController.key = Optional.of(new SoundImpl(folder + "/key.wav"));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
