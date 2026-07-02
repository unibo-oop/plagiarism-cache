package menu.components;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class generates an instance designed to create a new font. Use getter to get the font created.
 *
 */
public class GameFont {

    private static final String URL_FONT = "/Videophreak.ttf";

    private Font font;

    /**
     * Creates a new GameFont.
     */
    public GameFont() {
        final InputStream is = this.getClass().getResourceAsStream(URL_FONT);
        try {
            this.font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter a font.
     * 
     * @return a font
     */
    public Font getFont() {
        return this.font;
    }
}
