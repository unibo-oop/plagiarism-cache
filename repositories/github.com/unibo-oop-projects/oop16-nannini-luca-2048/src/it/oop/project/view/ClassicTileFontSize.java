package it.oop.project.view;

import it.oop.project.util.ScreenSize;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to provide scalable sizes for font of 2048 tiles.
 * 
 */
public class ClassicTileFontSize {

    private static final Map<Integer, Integer> proportionMap = new HashMap<>();
    // 4x4 2048 can't have a number with more than 6 digits
    static {
        proportionMap.put(1, 15);
        proportionMap.put(2, 15);
        proportionMap.put(3, 18);
        proportionMap.put(4, 25);
        proportionMap.put(5, 30);
        proportionMap.put(6, 37);
    }

    private ClassicTileFontSize() {
    }

    /**
     * Returns the font size for the specified string.
     * 
     * @param s
     *            text of the tile you need to resize.
     * @return the font size for the specified string.
     */
    public static float getSizeFor(final String s) {
        return ScreenSize.getSmallerSide() / proportionMap.get(s.length());
    }

}
