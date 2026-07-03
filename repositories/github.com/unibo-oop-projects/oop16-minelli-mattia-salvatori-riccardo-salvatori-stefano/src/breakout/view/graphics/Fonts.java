package breakout.view.graphics;

import breakout.view.utils.Utils;
import javafx.scene.text.Font;

/**
 * Enum for fonts available in the game.
 *
 */
public enum Fonts {

    /** Pixel font. */
    PIXEL_FONT("/Fonts/PixelFont.ttf"),
    /** Advanced font. */
    ADVANCED_FONT("/Fonts/airstrikeacad.ttf"),
    /** Classic font. */
    CLASSIC_FONT("/Fonts/BasicGameFont.ttf");

    private final String path;

    Fonts(final String path) {
        this.path = path;
    }

    /**
     * @param size
     *            the size of the font
     * @return The font
     */
    public Font get(final double size) {
        return Font.loadFont(Utils.getPath(this.path), size);
    }

}
