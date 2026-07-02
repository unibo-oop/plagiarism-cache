package view.audio;

/**
 * Volume values for audio tracks.
 */
public enum Volume {

    /**
     * Mute volume.
     */
    MUTE(0.0),

    /**
     * Low volume.
     */
    LOW(0.15),

    /**
     * Medium volume.
     */
    MEDIUM(0.4),

    /**
     * High volume.
     */
    HIGH(1.0);

    private final double volume;

    Volume(final double volume) {
        this.volume = volume;
    }

    /**
     * @return the volume value as a double.
     */
    public double getVolume() {
        return this.volume;
    }

}
