package it.oop.project.util;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A class for providing informations about screen size.
 *
 */
public class ScreenSize {

    private static int smallerSide;
    static {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        smallerSide = (int) (screen.getHeight() < screen.getWidth()
                ? screen.getHeight()
                : screen.getWidth());
    }

    private ScreenSize() {
    }

    /**
     * Returns the size of the smaller side of the screen.
     * 
     * @return the size of the smaller side of the screen.
     */
    public static int getSmallerSide() {
        return smallerSide;
    }

}
