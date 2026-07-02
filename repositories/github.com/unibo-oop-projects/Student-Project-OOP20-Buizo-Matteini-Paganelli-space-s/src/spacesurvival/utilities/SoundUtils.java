package spacesurvival.utilities;

public final class SoundUtils {

    /**
     * The starting volume for the sound.
     */
    public static final double STARTING_VOLUME = 0.50;

    /**
     * Default value for sound slider.
     */
    public static final int DEFAULT_VALUE_SOUND = (int) (STARTING_VOLUME * 100);

    /**
     * Major spacing between the sound slider.
     */
    public static final int DEFAULT_MAJOR_TICK_SPACING = 20;

    /**
     * Minor spacing between the sound slider.
     */
    public static final int DEFAULT_MINOR_TICK_SPACING = 5;

    /**
     * Sound zero.
     */
    public static final int SOUND_ZERO = 0;

    /**
     *  Max Sound.
     */
    public static final float MAX_SOUND = 100.0f;

    /**
     * Multiplication factor for converting the amplitude of the decibels to the actual volume.
     */
    public static final double AMPLITUDE_MULTIPLICATOR_FACTOR = 20;

    private SoundUtils() {

    }

}
