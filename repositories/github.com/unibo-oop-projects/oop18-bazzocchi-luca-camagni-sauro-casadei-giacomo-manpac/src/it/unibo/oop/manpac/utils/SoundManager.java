package it.unibo.oop.manpac.utils;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Represents the Sound Manager of the game. It's a SINGLETON, so to get the
 * instance call SoundManager.getSoundManager().
 *
 */
public final class SoundManager {

    private static final String SOUND_FOLDER = "Sounds/";
    private static final int VOLUME_DEFAULT = 50;
    private static final float VELOCITY_DEFAULT = 1f;
    private static final float PERCENTAGE = 100f;
    private static final float CUT_VELOCITY = 0.1f;
    private static final long DELAY_PLAY = 50L;

    // the sound volume level
    private float volume;
    // the speed of sound reproduction
    private float velocity;

    // the collection to associate SoundName -> Sound
    private final Map<SoundName, Optional<Sound>> sounds = new EnumMap<>(SoundName.class);

    private static class CreateSingleton {
        private static final SoundManager SINGLETON = new SoundManager();
    }

    /**
     * Enumerator to represent all the sounds of the game.
     */
    public enum SoundName {

        /**
         * The sound for when the application starts.
         */
        START_APP(SOUND_FOLDER + "intermission.mp3", 5277),
        /**
         * The sound for when a new game is started.
         */
        NEW_GAME(SOUND_FOLDER + "new_game.mp3", 4284),
        /**
         * The sound for when a button is pressed.
         */
        BUTTON_ONE(SOUND_FOLDER + "button_1.mp3", 313),
        /**
         * The sound for when a button is pressed.
         */
        BUTTON_TWO(SOUND_FOLDER + "button_2.mp3", 366),
        /**
         * The first sound for when a pill is eaten.
         */
        EAT_PILL_ONE(SOUND_FOLDER + "eat_pill_1.mp3", 157),
        /**
         * The second sound for when a pill is eaten.
         */
        EAT_PILL_TWO(SOUND_FOLDER + "eat_pill_2.mp3", 157),
        /**
         * The sound for when a phantom is eaten.
         */
        EAT_PHANTOM(SOUND_FOLDER + "eat_phantom.mp3", 601),
        /**
         * The sound for when the side portal is taken.
         */
        PACMAN_EFFECT(SOUND_FOLDER + "effect_pacman.mp3", 496),
        /**
         * The sound of wail of phantoms.
         */
        PHANTOM_WAIL(SOUND_FOLDER + "phantom_wail.mp3", 470),
        /**
         * The sound for when you lost a life.
         */
        DEATH(SOUND_FOLDER + "death.mp3", 209),
        /**
         * The sound for when you lost a game.
         */
        GAME_OVER(SOUND_FOLDER + "game_over.mp3", 1593),
        /**
         * The sound of when the power mode starts.
         */
        POWER_START(SOUND_FOLDER + "power_start.mp3", 209),
        /**
         * the sound of when the power mode is ending.
         */
        POWER_ENDING(SOUND_FOLDER + "power_ending.mp3", 340);

        // path of sound file
        private final String path;
        // duration in ms of sound
        private final int duration;

        SoundName(final String path, final int duration) {
            this.path = path;
            this.duration = duration;
        }

        /**
         * To get the duration of the chosen sound.
         * 
         * @return The duration of sound
         */
        public int getDuration() {
            return this.duration;
        }
    }


    // private constructor to prevent the creation of new SoundManager instances
    private SoundManager() {
        // loads the sound only the first time the singleton is created
        Arrays.asList(SoundName.values()).stream().forEach(soundName -> {
            Optional<Sound> sound;
            try {
                sound = Optional.of(Gdx.audio.newSound(Gdx.files.internal(soundName.path)));
            } catch (Exception e) { // is not good to catch generic exception
                // if it fails to load the sound, the sound will be empty
                sound = Optional.empty();
            }
            this.sounds.put(soundName, sound);
        });

        // default volume and velocity
        this.volume = VOLUME_DEFAULT / PERCENTAGE;
        this.velocity = VELOCITY_DEFAULT;
    }

    /**
     * To get the SoundManager.
     * 
     * @return The SoundManager of the game
     */
    public static SoundManager getSoundManager() {
        return CreateSingleton.SINGLETON;
    }

    /**
     * The volume of the SoundManager.
     * 
     * @return The actual volume of sounds in percentage (0-100)
     */
    public int getVolume() {
        return (int) (this.volume * PERCENTAGE);
    }

    /**
     * To set the volume of game.
     * 
     * @param volume It represents the game volume as a percentage (0-100). If the
     *               volume is not between 0 and 100, will to set the default value
     *               ({@value #VOLUME_DEFAULT})
     * @return The SoundManager of the game
     */
    public SoundManager setVolume(final int volume) {
        this.volume = (volume >= 0 && volume <= 100) ? volume : VOLUME_DEFAULT;
        this.volume = this.volume / PERCENTAGE;
        return this;
    }

    /**
     * The speed of sound reproduction.
     * 
     * @return The actual speed of sound reproduction
     */
    public float getVelocity() {
        return this.velocity;
    }

    /**
     * To set the speed of sound reproduction of the game.
     * 
     * @param velocity The speed of sound reproduction. The value must be greater
     *                 than {@value #CUT_VELOCITY} (a value of &#62; 0.3 is
     *                 recommended)
     * @return The SoundManager of the game
     */
    public SoundManager setVelocity(final float velocity) {
        if (velocity < CUT_VELOCITY) {
            this.velocity = VELOCITY_DEFAULT;
        } else {
            this.velocity = velocity;
        }
        return this;
    }

    /**
     * Play the chosen sound.
     * 
     * @param soundName The sound to choose
     * @return The SoundManager of the game
     */
    public SoundManager play(final SoundName soundName) {
        Objects.requireNonNull(soundName);
        sounds.get(soundName).ifPresent(sound -> sound.play(volume, velocity, 0));
        return this;
    }

    /**
     * Play the chosen sound with the game suspended during its duration.
     * 
     * @param soundName The sound to choose
     * @return The SoundManager of the game
     */
    public SoundManager playWhithSleep(final SoundName soundName) {
        play(soundName);
        try {
            // added a delay because the library libGDX is slow
            Thread.sleep((long) Math.ceil((soundName.duration / velocity) + DELAY_PLAY));
        } catch (InterruptedException e) {
            System.err.println("Impossible to set the program in suspension!");
        }
        return this;
    }

    /**
     * Play the chosen sound by repeating endlessly.
     * 
     * @param soundName The sound to choose
     * @return The SoundManager of the game
     */
    public SoundManager playWithLoop(final SoundName soundName) {
        Objects.requireNonNull(soundName);
        sounds.get(soundName).ifPresent(sound -> sound.loop(volume, velocity, 0));
        return this;
    }

    /**
     * Stop the chosen sound.
     * 
     * @param soundName The sound to choose
     * @return The SoundManager of the game
     */
    public SoundManager stop(final SoundName soundName) {
        Objects.requireNonNull(soundName);
        sounds.get(soundName).ifPresent(sound -> sound.stop());
        return this;
    }

    /**
     * Stop all the sounds currently playing.
     * 
     * @return The SoundManager of the game
     */
    public SoundManager stopAllSounds() {
        sounds.entrySet().forEach(soundEntry -> soundEntry.getValue().ifPresent(sound -> sound.stop()));
        return this;
    }

    /**
     * To release all the sounds; after this, playing the audio is impossible.
     */
    public void dispose() {
        sounds.entrySet().forEach(soundEntry -> soundEntry.getValue().ifPresent(sound -> sound.dispose()));
    }
}
