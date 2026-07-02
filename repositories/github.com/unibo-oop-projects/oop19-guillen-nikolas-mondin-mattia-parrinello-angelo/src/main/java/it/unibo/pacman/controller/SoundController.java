package it.unibo.pacman.controller;

import java.io.IOException;
import java.util.Optional;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import it.unibo.pacman.model.utilities.Sound;
import it.unibo.pacman.model.utilities.SoundImpl;


/**
 * Class that stores and load all the Sound.
 */
public class SoundController {

    private static Optional<Sound> startGame;
    private static Optional<Sound> pacmanDeath;
    private static Optional<Sound> pacmanStartGame;
    private static Optional<Sound> pacmanEatenPowerPill;
    private static Optional<Sound> pacmanTriumph;
    private static String folder = "/Sounds/";
    private boolean effectsOn = true;
    /**
     * 
     */
    public SoundController() {
        loadEffects();
    }

    /**
     * Gets the startGame sound.
     * @return startGame
     */
    public static Optional<Sound> getStartGameSound() {
        return startGame;
    }
    /**
     * Gets the pacmanDeath sound.
     * @return pacmanDeath
     */
    public static Optional<Sound> getPacmanDeathSound() {
        return pacmanDeath;
    }
    /**
     * Gets the startGame alternative sound.
     * @return startGame
     */
    public static Optional<Sound> getStartGameSoundAlternative() {
        return pacmanStartGame;
    }
    /**
     * Gets the pacmanTriumph sound.
     * @return startGame
     */
    public static Optional<Sound> getTriumphantSound() {
        return pacmanTriumph;
    }
    /**
     * Gets the pacmanEatenPowerPill sound.
     * @return startGame
     */
    public static Optional<Sound> getEatenPillSound() {
        return pacmanEatenPowerPill;
    }
    /**
     * Method used to enable music sound.
     */
    public void setMusicdOn() {
        this.effectsOn = true;
    }
    /**
     * Method used to disable music sounds.
     */
    public void setMusicOff() {
        this.effectsOn = false;
    }
    /**
     * Method used to adjust changes according to the options selected.
     */
    public void setSounds() {
        if (!effectsOn) {
            SoundController.pacmanDeath = Optional.empty();
            SoundController.startGame = Optional.empty();
            SoundController.pacmanStartGame = Optional.empty();
            SoundController.pacmanTriumph = Optional.empty();
            SoundController.pacmanEatenPowerPill = Optional.empty();
        } else {
            loadEffects();
        }
    }
    /**
     *Loads the effects.
     */
    private void loadEffects() {
        try {
            SoundController.startGame = Optional.of(new SoundImpl(folder + "pacman_start_game.wav"));
            SoundController.pacmanDeath = Optional.of(new SoundImpl(folder + "pacman_death.wav"));
            SoundController.pacmanTriumph = Optional.of(new SoundImpl(folder + "pacman_won.wav"));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
