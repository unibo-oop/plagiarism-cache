package utilities;

/**
 * 
 * Enumeration that defines theme.
 *
 */
public enum Theme {

    /**
     * Light theme.
     */
    LIGHT("light_theme_"),

    /**
     * Dark theme.
     */
    DARK("dark_theme_");

    private static final String PATH = "css/";
    private static final String FORMAT = ".css";
    private String name;

    /**
     * Create new Theme.
     * @param name of the theme
     */
    Theme(final String name) {
        this.name = name;
    }

    /**
     * @param scene
     * @return the .css for the scene
     */
    public String getCss(final Scenes scene) {
        return PATH + name + scene.toString() + FORMAT;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
