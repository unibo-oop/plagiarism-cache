package resource.routing;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PowerUpDrop Texture resource path.
 *
 */
public enum PowerUpDropTexture implements Serializable {

    /**
     * Default theme, drop powerup texture.
     */
    DROP_TEXTURE_DEFAULT("Images/dropPowerup/defaultDrop.png", "Default"),

    /**
     * Arkanoid theme, drop powerup texture.
     */
    DROP_TEXTURE_ARKANOID("Images/dropPowerup/arkanoidDrop.png", "Arkanoid"),

    /**
     * Galaga theme, drop powerup texture.
     */
    DROP_TEXTURE_GALAGA("Images/dropPowerup/galagaDrop.png", "Galaga"),

    /**
     * Pacman theme, drop powerup texture.
     */
    DROP_TEXTURE_PACMAN("Images/dropPowerup/pacmanDrop.png", "Pacman"),

    /**
     * Crash Bandicoot theme, drop powerup texture.
     */
    DROP_TEXTURE_CRASH("Images/dropPowerup/crashDrop.png", "Crash"),

    /**
     * Super Mario theme, drop powerup texture.
     */
    DROP_TEXTURE_MARIO("Images/dropPowerup/marioDrop.png", "SuperMario");

    private String path;
    private String theme;

    PowerUpDropTexture(final String path, final String theme) {
        this.path = path;
        this.theme = theme;
    }

    /**
     * 
     * @return the path of PowerUpDropTexture
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * @return the name of PowerUpDropTexture
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available PowerUpDropTexture
     */
    public static List<String> getPowerupDropTextureNames() {
        return Arrays.asList(PowerUpTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a dropped powerup by name.
     * @param theme to map
     * @return String path of PowerUpDropTexture
     */
    public static PowerUpDropTexture getPowerUpDropTextureByName(final String theme) {
        return Arrays.asList(PowerUpDropTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get();
    }
}
