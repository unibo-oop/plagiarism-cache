package spacesurvival.view.utilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.URL;

import spacesurvival.utilities.path.MainFolder;

/**
 * Implementation of graphics layout utilities.
 */
public final class GraphicsLayoutUtils {

    /**
     * Round text.
     */
    public static final String ROUND_STRING = "Round ";

    /**
     * Score text.
     */
    public static final String SCORE_STRING = "Score: ";

    /**
     * Enemies text.
     */
    public static final String ENEMIES_STRING = "Enemies";

    /**
     * Timer initialization text.
     */
    public static final String INIT_TIMER_STRING = "00:00:00";

    /**
     * Timer initialization text.
     */
    public static final String INIT_HEART_STRING = "x3";

    /**
     * Size font H0.
     */
    public static final int SIZE_FONT_H0 = 200;

    /**
     * Size font H1.
     */
    public static final int SIZE_FONT_H1 = 100;

    /**
     * Size font H2.
     */
    public static final int SIZE_FONT_H2 = 60;

    /**
     * Size font H3.
     */
    public static final int SIZE_FONT_H3 = 35;

    /**
     * Size font H4.
     */
    public static final int SIZE_FONT_H4 = 30;

    /**
     * Size font H5.
     */
    public static final int SIZE_FONT_H5 = 25;

    /**
     * Size font H6.
     */
    public static final int SIZE_FONT_H6 = 20;

    /**
     * Size font H7.
     */
    public static final int SIZE_FONT_H7 = 15;

    /**
     * Font Standard.
     */
    public static final String TYPE_FONT_STANDARD = "Helvetica";

    /**
     * Font standard H4.
     */
    public static final Font FONT_STANDARD_H4 = new Font(TYPE_FONT_STANDARD, Font.BOLD, SIZE_FONT_H4);

    /**
     * Font standard H5.
     */
    public static final Font FONT_STANDARD_H5 = new Font(TYPE_FONT_STANDARD, Font.BOLD, SIZE_FONT_H5);

    /**
     * Font standard H6.
     */
    public static final Font FONT_STANDARD_H6 = new Font(TYPE_FONT_STANDARD, Font.BOLD, SIZE_FONT_H6);

    /**
     * Font standard H7.
     */
    public static final Font FONT_STANDARD_H7 = new Font(TYPE_FONT_STANDARD, Font.BOLD, SIZE_FONT_H7);

    /**
     * Standard size columns JTextFields.
     */
    public static final int SIZE_COLUMNS_TEXT = 10;

    /**
     * Color 1 for text.
     */
    public static final Color COLOR_1 = new Color(90, 165, 232);

    /**
     * Color 2 for text.
     */
    public static final Color COLOR_2 = new Color(128, 213, 255);

    /**
     * Color 3 for text.
     */
    public static final Color COLOR_3 = new Color(148, 233, 255);

    /**
     * Color 4 for text.
     */
    public static final Color COLOR_4 = new Color(58, 241, 255);

    /**
     * Color opacity black for text.
     */
    public static final Color COLOR_OPACITY_BLACK = new Color(0, 0, 0, 80);

    /**
     * Color for background game.
     */
    public static final Color BACKGROUND_GAME_COLOR = new Color(3, 88, 149);

    /**
     * Path font for title menu.
     */
    public static final String PATH_FONT_TITLE = MainFolder.FONT + "/main.ttf";

    /**
     * Path font for game.
     */
    public static final String PATH_FONT_GAME = MainFolder.FONT + "/game.ttf";

    /**
     * Path font for end game.
     */
    public static final String PATH_FONT_END_GAME = MainFolder.FONT + "/chiller.ttf";

    /**
     * Method for get Font from path.
     * @param size for dimension font.
     * @param pathFont is path of font.
     * @return Font for text.
     */
    public static Font getFontFromPath(final int size, final String pathFont) {
        final URL fontUrl = ClassLoader.getSystemResource(pathFont);
        Font myFont = null;
        try {
            myFont = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
            myFont = myFont.deriveFont(Font.PLAIN, size);
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(myFont);
        } catch (IOException | FontFormatException e) {
            System.err.println("Path font no found");
        }

        return myFont;
    }

    /**
     * Get font title menu from size.
     * @param size for font.
     * @return myFont title menu.
     */
    public static Font getFontForTitle(final int size) {
        return getFontFromPath(size, PATH_FONT_TITLE);
    }

    /**
     * Get font title menu from size.
     * @param size for font.
     * @return myFont game.
     */
    public static Font getFontForGame(final int size) {
        return getFontFromPath(size, PATH_FONT_GAME);

    }

    /**
     * Get font title menu from size.
     * @param size for font.
     * @return myFont end game. 
     */
    public static Font getFontForDead(final int size) {
        return getFontFromPath(size, PATH_FONT_END_GAME);
    }

    private GraphicsLayoutUtils() {
    }
}
