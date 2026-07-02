package it.unibo.modularcheckers.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Utilities for managing Swing panels and frames.
 */
public final class ViewUtils {

    private static final int PROPORTION = 1;

    private ViewUtils() {

    }

    /**
     * Sets FullScreen to frame given.
     * @param frame that needs to be resized
     */
    public static void setFullScreen(final JFrame frame) {
        setScreenProportion(frame, PROPORTION);
    }

    /**
     * Resizes the JFrame based on the proportion given, refers to the actual size of the screen.
     * @param frame that needs to be resized
     * @param proportion how many times the Frame is based on 1/x?
     */
    public static void setScreenProportion(final JFrame frame, final int proportion) {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / proportion, sh / proportion);
        frame.setLocationByPlatform(true);
    }
}
