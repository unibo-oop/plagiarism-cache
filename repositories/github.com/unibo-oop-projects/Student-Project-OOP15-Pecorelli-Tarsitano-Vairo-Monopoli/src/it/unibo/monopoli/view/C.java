package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;

/**
 * 
 * class that contains all the magic number used in the view.
 *
 */
public class C {

    /**
     * true if you have to carry out tests to test the result.
     */
    public static final boolean DEBUG = false;

    /**
     * 
     */
    public static final int JTAB_PREFERRED_HEIGHT = 32;

    /**
     * JShape Preferred size.
     */
    public static final int JSHAPE_PREFERRED_SIZE = 8;

    /**
     * String to check if it is initialized some version.
     */
    public static final String NOT_SELECTABLE_OPTION = " - Select an Option - ";

    /**
     * 
     * maximum number of players.
     */
    public static final int MAX_PLAYERS = 6;

    /**
     * separator character in a map.
     */
    public static final String SPLITTOKEN = ":";

    /**
     * array that contains the colors to be associated with players.
     */
    private static Color[] colors = { Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.GRAY };
    /**
     * list to keep track of the colors chosen by users.
     */
    public static final LinkedList<Color> CL;

    static {
        CL = new LinkedList<Color>();
        for (int i = 0; i <= colors.length - 1; i++) {
            CL.add(colors[i]);
        }
    }

    /**
     * X Location JDilaog.
     */
    public static final int X_LOCATION_JDIALOG = 500;

    /**
     * Y Location JDialog.
     */
    public static final int Y_LOCATION_JDIALOG = 300;

    /**
     * PreferredSize for card.
     */
    public static final Dimension DIM = new Dimension(55, 55);

    /**
     * PreferredSize for panelPlayer.
     */
    public static final Dimension DIM_PLAYER = new Dimension(120, 150);
}
