package unibo.exiled.utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

/**
 * The FontManager class is responsible for loading and managing the custom font
 * used in the application.
 */
public final class FontManager {
    private static final float DEFAULT_FONT_SIZE = 20f;

    private static Font customFont;

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private FontManager() {
    }

    /**
     * Loads the custom font from the specified file path. If the loading fails, it
     * falls back to a default font.
     */
    public static void loadFont() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT,
                            ClassLoader.getSystemResourceAsStream("unibo/exiled/font.ttf"))
                    .deriveFont(DEFAULT_FONT_SIZE);
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            // If the custom font loading fails, use a fallback font (Arial, bold, size 16).
            customFont = new Font("Arial", Font.BOLD, 16);
        }
    }

    /**
     * Retrieves the custom font loaded by the FontManager.
     *
     * @return The custom font.
     */
    public static Font getCustomFont() {
        return customFont;
    }

    /**
     * Retrieves the custom font loaded by the FontManager with a specified size.
     *
     * @param size The size to set for the custom font.
     * @return The custom font with the specified size.
     */
    public static Font getCustomFont(final int size) {
        return customFont.deriveFont((float) size);
    }
}

