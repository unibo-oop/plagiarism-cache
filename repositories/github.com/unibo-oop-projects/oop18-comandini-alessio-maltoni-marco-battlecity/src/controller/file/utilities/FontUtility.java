package controller.file.utilities;

import enums.GameFont;
import javafx.scene.text.Font;

/**
 * Class utility for load the fonts. The class uses the Singleton Pattern.
 */
public final class FontUtility {

    // Default size of the Font.
    private static final double DEFAULT_FONT_SIZE = 10.0;

    // Instance of the class.
    private static final FontUtility SINGLETON = new FontUtility();

    /**
     * Method that implements the Singleton Pattern. It returns only one instance of
     * the class, created only one time.
     * 
     * @return the instance of the class.
     */
    public static FontUtility getInstance() {
        return SINGLETON;
    }

    /*
     * Empty constructor of the class that load the fonts.
     */
    private FontUtility() {
    }

    /**
     * Method that return the requested font. Given a Font enumeration, it open the
     * specified file and return it back.
     * 
     * @param fontEnum the enumeration of the requested font.
     * @return the requested font.
     */
    public Font getFont(final GameFont fontEnum) {
        Font font = null;
        final String path = fontEnum.getPath();
        // Try to load the font from file.
        try {
            font = Font.loadFont(getClass().getResourceAsStream(path), DEFAULT_FONT_SIZE);
        } catch (final Exception e) {
            System.out.println("FONT " + fontEnum + " NOT FOUND");
        }
        return font;
    }

}
