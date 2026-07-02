
package resource.routing;

import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Background resource path.
 * 
 */
public enum BackGround implements Serializable {

    /**
     * Default theme, background.
     */
    BACKGROUND_DEFAULT("Images/background/DefaultBackground.png", "Default"),

    /**
     * Arkanoid theme, background.
     */
    BACKGROUND_ARKANOID("Images/background/ArkanoidBackground.png", "Arkanoid"),

    /**
     * Galaga theme, background.
     */
    BACKGROUND_GALAGA("Images/background/GalagaBackground.png", "Galaga"),

    /**
     * Pacman theme, background.
     */
    BACKGROUND_PACMAN("Images/background/PacmanBackground.png", "Pacman"),

    /**
     * Crash Bandicoot theme, background.
     */
    BACKGROUND_CRASH("Images/background/CrashBackground.jpg", "Crash"),

    /**
     * Super Mario theme, background.
     */
    BACKGROUND_SUPERMARIO("Images/background/MarioBackground.jpg", "SuperMario");

    private String path;
    private String theme;

    BackGround(final String path, final String theme) {
        this.path = path;
        this.theme = theme;
    }

    /**
     * 
     * @return the path of background
     */
    public String getPath() {
        return path;
    }

    public URL getUrl() {
        return ClassLoader.getSystemResource(this.path);
    }

    /**
     * 
     * @return the  name of background 
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available backgrounds
     */
    public static List<String> getBackGroundNames() {
        return Arrays.asList(BackGround.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a background by name.
     * @param theme to map
     * @return backGround enum
     */
    public static BackGround getBackGroundByName(final String theme) {
        return Arrays.asList(BackGround.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get();
    }
}
