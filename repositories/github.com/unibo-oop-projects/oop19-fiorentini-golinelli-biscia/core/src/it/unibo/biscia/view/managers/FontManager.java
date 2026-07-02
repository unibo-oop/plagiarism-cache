package it.unibo.biscia.view.managers;

/**
 * Utility class for managing fonts files.
 *
 */
public final class FontManager {

    /**
     * Arcade font.
     */
    public static final Asset<Font> ARCADE = new AssetImpl<>("fonts/arcade.ttf", "arcade", new Font(14));
    /**
     * The logo font for "Biscia".
     */
    public static final Asset<Font> LOGO = new AssetImpl<>("fonts/logo.ttf", "logo", new Font(140));

    /**
     * A font asset info.
     *
     */
    // TODO: Maybe extract in packege with interface
    public static final class Font {
        private final int size;

        private Font(final int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    private FontManager() {
    }
}
