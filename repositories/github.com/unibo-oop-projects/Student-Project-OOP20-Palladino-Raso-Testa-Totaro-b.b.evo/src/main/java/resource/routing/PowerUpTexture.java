package resource.routing;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PowerUp Texture resource path.
 *
 */
public enum PowerUpTexture implements Serializable {

    /**
     * Default theme, power up texture.
     */
    POWERUP_TEXTURE_DEFAULT("Images/powerup/defaultPowerUp.png", "Default"),

    /**
     * Arkanoid theme, power up texture.
     */
    POWERUP_TEXTURE_ARKANOID("Images/powerup/arkanoidPowerUp.png", "Arkanoid"),

    /**
     * Galaga theme, power up texture.
     */
    POWERUP_TEXTURE_GALAGA("Images/powerup/galagaPowerUp.png", "Galaga"),

    /**
     * Pacman theme, power up texture.
     */
    POWERUP_TEXTURE_PACMAN("Images/powerup/pacmanPowerUp.png", "Pacman"),

    /**
     * Crash Bandicoot theme, power up texture.
     */
    POWERUP_TEXTURE_CRASH("Images/powerup/crashPowerUp.png", "Crash"),

    /**
     * Super Mario theme, power up texture.
     */
    POWERUP_TEXTURE_SUPERMARIO("Images/powerup/marioPowerUp.png", "SuperMario");

    private String path;
    private String theme;

    PowerUpTexture(final String path, final String theme) {

        this.path = path;
        this.theme = theme;
    }

    /**
     * 
     * @return the path of PowerUpTexture
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * @return the name of PowerUpTexture
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available PowerUpTexture
     */
    public static List<String> getPowerupTextureNames() {
        return Arrays.asList(PowerUpTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a powerup by name.
     * @param theme to map
     * @return String path of PowerUpTexture
     */
    public static PowerUpTexture getPowerUpTextureByName(final String theme) {
        return Arrays.asList(PowerUpTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get();
    }

    /**
     * @param path of textureBrick
     * @return name of theme
     */
    public static PowerUpTexture getThemeNameByPath(final String path) {
        return Arrays.asList(PowerUpTexture.values()).stream()
                .filter(i -> i.getPath().equals(path))
                .findFirst()
                .get();
    }
}
