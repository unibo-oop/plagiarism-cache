package enums;

/**
 * This enumeration identifies a font stored on the disk and its path.
 */
public enum GameFont {

    /**
     * The NAMCO Font.
     */
    NAMCO("namco.ttf"),
    /**
     * The PRESS_START Font.
     */
    PRESS_START("press_start.ttf");

    // The path of the font on disk.
    private String path;
    // The directory which the font is stored.
    private static final String DIR = "fonts";

    /*
     * Constructor of the font enumeration.
     * 
     * @param fontName the name which the font is stored on the disk with extension.
     */
    GameFont(final String fontName) {
        path = "/" + DIR + "/" + fontName;
    }

    /**
     * Getter method of the path of the Font.
     * 
     * @return the path of the font on disk.
     */
    public String getPath() {
        return path;
    }

}
