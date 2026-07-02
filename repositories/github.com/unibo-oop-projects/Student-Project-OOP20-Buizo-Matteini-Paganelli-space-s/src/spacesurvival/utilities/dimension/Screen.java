package spacesurvival.utilities.dimension;

import spacesurvival.model.common.P2d;
import spacesurvival.utilities.SystemVariables;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * Utils for manage Screen.
 */
public final class Screen {
    private static final float PROPORTION_BIG = 0.9F;
    private static final float PROPORTION_MEDIUM = 0.6F;
    private static final float PROPORTION_MINI = 0.4F;

    private static final double FIND_CENTER_X = 2 * SystemVariables.SCALE_X;
    private static final double FIND_CENTER_Y = 2 * SystemVariables.SCALE_Y;

    /**
     * Represent the point (0,0).
     */
    public static final Point POINT_ZERO = new Point(0, 0);
    /**
     * Dimension for the full screen.
     */
    public static final Dimension FULLSCREEN = SystemVariables.SCREEN; 

    /**
     * Width of fullscreen.
     */
    public static final int WIDTH_FULLSCREEN = (int) (FULLSCREEN.getWidth());
    /**
     * Height of fullscreen.
     */
    public static final int HEIGHT_FULLSCREEN = (int) (FULLSCREEN.getHeight());
    /**
     * X coordinate for the center. 
     */
    public static final double CENTER_X_FULLSCREEN = (WIDTH_FULLSCREEN * SystemVariables.SCALE_X) / FIND_CENTER_X;
    /**
     * Y coordinate for the center. 
     */
    public static final double CENTER_Y_FULLSCREEN = (HEIGHT_FULLSCREEN * SystemVariables.SCALE_Y) / FIND_CENTER_Y;
    /**
     * P2d for the center. 
     */
    public static final P2d POINT_CENTER_FULLSCREEN = new P2d(CENTER_X_FULLSCREEN, CENTER_Y_FULLSCREEN);
    /**
     * P2d for the absolute center.
     */
    public static final P2d POINT_CENTER_ABSOLUTE = new P2d((WIDTH_FULLSCREEN * SystemVariables.SCALE_X) / 2, (HEIGHT_FULLSCREEN * SystemVariables.SCALE_Y) / 2);
    /**
     * Point for the center.
     */
    public static final Point JAVA_POINT_CENTER_FULLSCREEN = new Point((int) POINT_CENTER_FULLSCREEN.getX(), (int) POINT_CENTER_FULLSCREEN.getY());
    /**
     * Rectangle representing the screen.
     */
    public static final Rectangle RECTANGLE_FULLSCREEN = new Rectangle(POINT_ZERO, FULLSCREEN);
    /**
     * Proportion big.
     */
    public static final int WIDTH_BIG = (int) (WIDTH_FULLSCREEN * PROPORTION_BIG);
    /**
     * Height big.
     */
    public static final int HEIGHT_BIG = (int) (HEIGHT_FULLSCREEN * PROPORTION_BIG);
    /**
     * X coordinate for center big.
     */
    public static final int CENTER_X_BIG = (int) (POINT_CENTER_FULLSCREEN.getX() - ((WIDTH_BIG * SystemVariables.SCALE_X) / FIND_CENTER_X));
    /**
     * Y coordinate for center big.
     */
    public static final int CENTER_Y_BIG = (int) (POINT_CENTER_FULLSCREEN.getY() - ((HEIGHT_BIG * SystemVariables.SCALE_Y) / FIND_CENTER_Y));
    /**
     * Dimension big.
     */
    public static final Dimension DIMENSION_BIG = new Dimension(WIDTH_BIG, HEIGHT_BIG);
    /**
     * P2d for pointer center big.
     */
    public static final P2d POINT_CENTER_BIG = new P2d(CENTER_X_BIG, CENTER_Y_BIG);
    /**
     * Point for center big.
     */
    public static final Point JAVA_POINT_CENTER_BIG = new Point((int) POINT_CENTER_BIG.getX(), (int) POINT_CENTER_BIG.getY());
    /**
     * Rectangle Big.
     */
    public static final Rectangle RECTANGLE_BIG = new Rectangle(JAVA_POINT_CENTER_BIG, DIMENSION_BIG);
    /**
     * Width medium.
     */
    public static final int WIDTH_MEDIUM = (int) (WIDTH_FULLSCREEN * PROPORTION_MEDIUM);
    /**
     * Height medium.
     */
    public static final int HEIGHT_MEDIUM = (int) (HEIGHT_FULLSCREEN * PROPORTION_MEDIUM);
    /**
     * X coordinate for center medium.
     */
    public static final int CENTER_X_MEDIUM = (int) (POINT_CENTER_FULLSCREEN.getX() - ((WIDTH_MEDIUM * SystemVariables.SCALE_X) / FIND_CENTER_X));
    /**
     * Y coordinate for center medium.
     */
    public static final int CENTER_Y_MEDIUM = (int) (POINT_CENTER_FULLSCREEN.getY() - ((HEIGHT_MEDIUM * SystemVariables.SCALE_Y) / FIND_CENTER_Y));
    /**
     * Dimension medium.
     */
    public static final Dimension DIMENSION_MEDIUM = new Dimension(WIDTH_MEDIUM, HEIGHT_MEDIUM);
    /**
     * P2d for center medium.
     */
    public static final P2d POINT_CENTER_MEDIUM = new P2d(CENTER_X_MEDIUM, CENTER_Y_MEDIUM);
    /**
     * Point for center medium.
     */
    public static final Point JAVA_POINT_CENTER_MEDIUM = new Point((int) POINT_CENTER_MEDIUM.getX(), (int) POINT_CENTER_MEDIUM.getY());
    /**
     * Rectangle medium.
     */
    public static final Rectangle RECTANGLE_MEDIUM = new Rectangle(JAVA_POINT_CENTER_MEDIUM, DIMENSION_MEDIUM);
    /**
     * Width mini.
     */
    public static final int WIDTH_MINI = (int) (WIDTH_FULLSCREEN * PROPORTION_MINI);
    /**
     * Height mini.
     */
    public static final int HEIGHT_MINI = (int) (HEIGHT_FULLSCREEN * PROPORTION_MINI);
    /**
     * X coordinate for center mini.
     */
    public static final int CENTER_X_MINI = (int) (POINT_CENTER_FULLSCREEN.getX() - ((WIDTH_MINI * SystemVariables.SCALE_X) / FIND_CENTER_X));
    /**
     * Y coordinate for center mini.
     */
    public static final int CENTER_Y_MINI = (int) (POINT_CENTER_FULLSCREEN.getY() - ((HEIGHT_MINI * SystemVariables.SCALE_Y) / FIND_CENTER_Y));
    /**
     * Dimension for mini screen.
     */
    public static final Dimension DIMENSION_MINI = new Dimension(WIDTH_MINI, HEIGHT_MINI);
    /**
     * P2d for center mini.
     */
    public static final P2d POINT_CENTER_MINI = new P2d(CENTER_X_MINI, CENTER_Y_MINI);
    /**
     * Point for center mini.
     */
    public static final Point JAVA_POINT_MINI = new Point((int) POINT_CENTER_MINI.getX(), (int) POINT_CENTER_MINI.getY());
    /**
     * Rectangle mini.
     */
    public static final Rectangle RECTANGLE_MINI = new Rectangle(JAVA_POINT_MINI, DIMENSION_MINI);

    /**
     * Scale the dimension respect to other dimension.
     * 
     * @param scaleOf factor to scale
     * @param respectTo respect to another int
     * @return the scaled dimension
     */
    public static int scaleRespectTo(final int scaleOf, final int respectTo) {
        return scaleOf * respectTo / 1000;
    }

    private Screen() {
    }
}
