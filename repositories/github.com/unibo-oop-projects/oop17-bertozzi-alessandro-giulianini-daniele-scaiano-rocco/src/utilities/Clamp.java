package utilities;

/**
 * Class used to clamp a value between two limits.
 */
public final class Clamp {
    private Clamp() { }

    /**
     * @param var The value to be clamped
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value
     */
    public static int clamp(final int var, final int min, final int max) {
        return var < min ? min : (var > max ? max : var);
    }
}
