package utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;


/**Static class responsible of loading fonts from the jar application file making them portable around different machines.*/
public final class FontLoader {

    private FontLoader() {
    }

    /**It loads the font used in the view.*/
    public static void loadFont() {
        final GraphicsEnvironment gE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            final InputStream is = ImageLoader.class.getResourceAsStream("/bgothm.ttf");
            gE.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
        } catch (FontFormatException | IOException e1) {
            System.out.println("Error loading font." + e1);
        }
    }
}
