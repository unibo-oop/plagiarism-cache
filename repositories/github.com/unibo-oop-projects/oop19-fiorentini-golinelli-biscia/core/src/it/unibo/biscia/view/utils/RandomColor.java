package it.unibo.biscia.view.utils;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

/**
 * Random Color utility class.
 *
 */
public final class RandomColor {

    private static final Random RAND = new Random();

    /**
     * @return A random light color.
     */
    public static Color light() {
        return new Color(RAND.nextFloat() / 2f + 0.5f, RAND.nextFloat() / 2f + 0.5f, RAND.nextFloat() / 2f + 0.5f, 1f);
    }

    private RandomColor() {
    }

}
