package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The MenuVariablesUtils class is an utility class for menu that contains
 * constant.
 */
public final class MenuVariablesUtils {

    /**
     * SEPARTOR indicates the directory separator of the current operating system.
     */
    public static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * MENU_ICON is the path of the game icon.
     */
    public static final String MENU_ICON = "menu" + SEPARATOR + "window_icon.png";

    /* Screen size */

    private static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * WIDTH_MIN is the minimum width that the window can assume.
     */
    public static final double WIDTH_MIN = SCREENSIZE.getWidth() / 2;

    /**
     * HEIGHT_MIN is the minimum width that the window can assume.
     */
    public static final double HEIGHT_MIN = SCREENSIZE.getHeight() / 2.1;

    /**
     * WIDTH_DEFAULT is the default width of the window.
     */
    public static final double WIDTH_DEFAULT = SCREENSIZE.getWidth() / 1.2;

    /**
     * HEIGHT_DEFAULT is the default width of the window.
     */
    public static final double HEIGHT_DEFAULT = SCREENSIZE.getHeight() / 1.2;

    /* Fonts */

    private static final int LOWER_FONT_REDUCER = 70;
    private static final int MEDIUM_FONT_REDUCER = 40;
    private static final int GREATER_FONT_REDUCER = 30;

    /**
     * LOWER_FONT is a font type. It is the smallest of those used.
     */
    public static final Font LOWER_FONT = Font.font("", FontWeight.BOLD, SCREENSIZE.getHeight() / LOWER_FONT_REDUCER);

    /**
     * MEDIUM_FONT is a font type.
     */
    public static final Font MEDIUM_FONT = Font.font("", FontWeight.BOLD, SCREENSIZE.getHeight() / MEDIUM_FONT_REDUCER);

    /**
     * GREATER_FONT is a font type. It is the biggest of those used.
     */
    public static final Font GREATER_FONT = Font.font("", FontWeight.BOLD,
            SCREENSIZE.getHeight() / GREATER_FONT_REDUCER);

    private MenuVariablesUtils() {
    }
}
