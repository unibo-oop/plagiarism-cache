package breakout.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import breakout.view.utils.Utils;
import javafx.scene.media.AudioClip;

/**
 * Class that play sounds of the game.
 *
 */
public final class Sounds {

    private static Sounds singleton;
    private final Map<AvailableSounds, AudioClip> soundsMap = new HashMap<>();

    /**
     * Enum for available sounds.
     *
     */
    public enum AvailableSounds {

        /**
         * 
         */
        CLASSIC_PADDLE_HIT("/Sounds/FX/Classic_bip.wav"),
        /**
         * 
         */
        PADDLE_HIT("/Sounds/FX/Jump.wav"),

        /**
         * 
         */
        BRICK_HIT("/Sounds/FX/Suntemple.wav"),

        /**
         * 
         */
        CLASSIC_BRICK_HIT("/Sounds/FX/Classic_bop.wav"),

        /**
         * 
         */
        POWER_UP("/Sounds/FX/PowerUP.wav"),

        /**
         * 
         */
        BRICK_CRACK("/Sounds/FX/Crack.wav"),

        /**
         * 
         */
        UNBREAKABLE_HIT("/Sounds/FX/Metal_hit.wav"),

        /**
         * 
         */
        PROJECTILE_EXPLOSION("/Sounds/FX/Fuzzsound-bullet.wav"),
        /**
         * 
         */
        WALL_HIT("/Sounds/FX/Wall_Hit.wav");

        private final String path;

        /**
         * @param path
         *            the path for the sounds in the res folder
         */
        AvailableSounds(final String path) {
            this.path = path;
        }

        /**
         * @return the path for the sound
         */
        public String path() {
            return this.path;
        }

    }

    private Sounds() {
    }

    /**
     * @return the instance of the singleton
     */
    public static Sounds get() {
        if (Objects.isNull(singleton)) {
            singleton = new Sounds();
        }
        return singleton;
    }

    /**
     * 
     * @param sound
     *            the name of the sound
     * @return the audioclip
     */
    public AudioClip getSound(final AvailableSounds sound) {
        if (!this.soundsMap.containsKey(sound)) {
            this.soundsMap.put(sound, new AudioClip(Utils.getPath(sound.path())));

        }
        return this.soundsMap.get(sound);

    }

    /**
     * Loads all sounds available.
     */
    public void loadSounds() {
        Arrays.asList(AvailableSounds.values()).forEach(sound -> {
            if (!this.soundsMap.containsKey(sound)) {
                this.soundsMap.put(sound, new AudioClip(Utils.getPath(sound.path())));

            }
        });
    }
}
