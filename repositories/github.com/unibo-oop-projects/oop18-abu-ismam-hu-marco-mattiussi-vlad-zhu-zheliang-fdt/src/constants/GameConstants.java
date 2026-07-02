package constants;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * This class collects all static parameters of the game.
 */
public final class GameConstants {
    private GameConstants() { }
    /**
     * ScreenSize of the current monitor.
     */
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final double width = screenSize.getWidth();
    public static final double height = screenSize.getHeight();
    public static final double buttonSize = height/30;
    public static final double gameWidth = buttonSize*38;
    public final static double gameHeight = buttonSize*22;
}
