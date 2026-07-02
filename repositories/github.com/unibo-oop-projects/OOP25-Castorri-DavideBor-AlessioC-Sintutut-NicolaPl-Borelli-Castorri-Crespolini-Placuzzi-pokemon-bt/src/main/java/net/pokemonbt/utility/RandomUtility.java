package net.pokemonbt.utility;

import java.io.Serial;
import java.util.Random;

/**
 * Utility for the use of one single {@link Random} object throughout the
 * entire solution. It adds a "check" over a threshold.
 */
public class RandomUtility extends Random {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final Random RAND = new Random();

    /**
     * @param threshold Is the limit
     * @return The result: 'true' if the number generated is less
     *      then the threshold -> success. 'false' if it is more
     *      then the threshold -> fail.
     */
    public static boolean check(final float threshold) {
        if (Float.compare(threshold, 0.0f) < 0 || Float.compare(threshold, 1.0f) > 0) {
            throw new IllegalArgumentException(
                "Provided threshold ("
                .concat(String.valueOf(threshold))
                .concat(") cannot be outside the bounds [ 0.0, 1.0 ].")
            );
        }
        return Float.compare(RAND.nextFloat(), threshold) <= 0;
    }

    /**
     * @return A randomly generated {@link Integer}.
     */
    public static Integer nextInteger() {
        return RAND.nextInt();
    }

    /**
     * @param bound The upper limit.
     * @return A randomly generated {@link Integer} with a value from 
     *      0 (inclusive) to the bound passed (exclusive).
     */
    public static Integer nextInteger(final int bound) {
        return RAND.nextInt(bound);
    }

    /**
     * @param origin The lest value you can get.
     * @param bound The upper limit.
     * @return A randomly generated {@link Integer} with a value from 
     *      origin (inclusive) to the bound passed (exclusive).
     */
    public static Integer nextInteger(final int origin, final int bound) {
        return RAND.nextInt(origin, bound);
    }
}
