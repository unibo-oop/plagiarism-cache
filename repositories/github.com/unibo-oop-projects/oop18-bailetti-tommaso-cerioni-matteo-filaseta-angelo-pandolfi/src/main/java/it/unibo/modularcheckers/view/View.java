package it.unibo.modularcheckers.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * View Interface for the StartController.
 */
public abstract class View extends JFrame {

    private static final long serialVersionUID = 7840650771527323310L;
    private static final int FULL_SCREEN = 1;

    /**
     * Builds the view, setting the defaults.
     */
    public View() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    /**
     * Resize the JFrame based on the proportion given, refers to the actual size of the screen.
     *
     * @param proportion how many times the Frame is based on 1/x?
     */
    private void setScreenProportion(final int proportion) {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        this.setSize(sw / proportion, sh / proportion);
        this.setLocationByPlatform(true);
    }

    /**
     * Sets FullScreen to frame given.
     */
    public void setFullScreen() {
        setScreenProportion(FULL_SCREEN);
    }

}
