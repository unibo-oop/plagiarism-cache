package utils;

import tmw.common.Dim2D;
import tmw.common.P2d;

/**
 * class with a static method that permit to calculate the right position
 * proportion between an entities and the world.
 *
 */
public final class ProportionalPosUtils {

    private static final Dim2D MAPPROPORTIONS = new Dim2D(800, 600);
    private static final Dim2D TUTORIAL_PROPORTION = new Dim2D(800, 600);

    private ProportionalPosUtils() {
    }

    /**
     * Return the proportioned position.
     * 
     * @param x       default x position
     * @param y       default y position
     * @param gameRes game resolution
     * @return new position
     */
    public static P2d propDimention(final double x, final double y, final Dim2D gameRes) {
        return new P2d(x * gameRes.getWidth() / MAPPROPORTIONS.getWidth(),
                y * gameRes.getHeight() / MAPPROPORTIONS.getHeight());
    }

    /**
     * Return the proportioned x position.
     * 
     * @param x       default x position
     * @param gameRes game resolution
     * @return new x value
     */
    public static int propTrigger(final double x, final Dim2D gameRes) {
        return  (int) (x * gameRes.getWidth() / TUTORIAL_PROPORTION.getWidth());
    }
}
