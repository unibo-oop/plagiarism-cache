package resource.routing;

import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Brick Texture resource path.
 *
 */
public enum BrickTexture implements Serializable {

    /**
     * Default theme, brick texture.
     */
    BRICK_TEXTURE_DEFAULT("Images/brick/defaultBrick.jpg", "Default"),

    /**
     * Arkanoid theme, brick texture.
     */
    BRICK_TEXTURE_ARKANOID("Images/brick/arkanoidBrick.png", "Arkanoid"),

    /**
     * Galaga theme, brick texture.
     */
    BRICK_TEXTURE_GALAGA("Images/brick/galagaBrick.png", "Galaga"),

    /**
     * Pacman theme, brick texture.
     */
    BRICK_TEXTURE_PACMAN("Images/brick/pacmanBrick.png", "Pacman"),

    /**
     * Crash Bandicoot theme, brick texture.
     */
    BRICK_TEXTURE_CRASH("Images/brick/crashBrick.png", "Crash"),

    /**
     * Super Mario theme, brick texture.
     */
    BRICK_TEXTURE_SUPERMARIO("Images/brick/marioBrick.png", "SuperMario"),

    /**
     * Undestructible brick.
     */
    BRICK_TEXTURE_UNDESTRUCTIBLE("Images/brick/greyBrick.png", "Undestructible");

    private String path;
    private String theme;

    BrickTexture(final String path, final String theme) {
        this.path = path;
        this.theme = theme;
    }

    /**
     * 
     * @return the path of brickTexture
     */
    public String getPath() {
        return path;
    }

    public URL getURL() {
        return ClassLoader.getSystemResource(path);
    }

    /**
     * 
     * @return the name of brickTexture
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available brickTexture
     */
    public static List<String> getBrickTextureNamesVisible() {
        return Arrays.asList(BrickTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .filter(i -> !(i.equals(BrickTexture.BRICK_TEXTURE_UNDESTRUCTIBLE.getTheme())))
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a brick by name.
     * @param theme to map
     * @return String path of BrickTextureName
     */
    public static BrickTexture getBrickTextureByName(final String theme) {
        return Arrays.asList(BrickTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get();
    }
}
