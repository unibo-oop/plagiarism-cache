package resource.routing;

import java.io.InputStream;
import java.net.URL;
/**
 * This enum permise to find the path of the fonts in the game.
 * */
public enum PersonalFonts {
    /**
     * Font of all button and label. 
     */
    FONT_BUTTON("Fonts/Staatliches-Regular.ttf"),

    /**
     * Font of all title.
     */
    FONT_TITLE("Fonts/BungeeShade-Regular.ttf");

    private String path;

    PersonalFonts(final String path) {
        this.path = path;
    }

    public URL getURL() {
        return ClassLoader.getSystemResource(this.path);
    }

    public InputStream getResourceAsStream() {
        return ClassLoader.getSystemResourceAsStream(this.path);
    }

}
