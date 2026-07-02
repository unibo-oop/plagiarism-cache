package resource.routing;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Paddle Texture resource path.
 *
 */
public enum PaddleTexture implements Serializable {

    /**
     * Default theme, paddle.
     */
    PADDLE_DEFAULT("Images/paddle/defaultPaddle.png", "Default"),

    /**
     * Arkanoid theme, paddle.
     */
    PADDLE_ARKANOID("Images/paddle/arkanoidPaddle.png", "Arkanoid"),

    /**
     * Galaga theme, paddle.
     */
    PADDLE_GALAGA("Images/paddle/galagaPaddle.png", "Galaga"),

    /**
     * Pacman theme, ball.
     */
    PADDLE_PACMAN("Images/paddle/pacmanPaddle.png", "Pacman"),

    /**
     * Crash Bandicoot theme, paddle.
     */
    PADDLE_CRASH("Images/paddle/crashPaddle.png", "Crash"),

    /**
     * Super Mario theme, paddle.
     */
    PADDLE_SUPERMARIO("Images/paddle/marioPaddle.png", "SuperMario");

    private String path;
    private String theme;

    PaddleTexture(final String path, final String theme) {
        this.path = path;
        this.theme = theme;
    }

    /**
     * 
     * @return the path of paddle
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * @return the name of paddle
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available paddle
     */
    public static List<String> getPaddleTexturedNames() {
        return Arrays.asList(PaddleTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a paddle by name.
     * @param theme to map
     * @return paddle enum
     */
    public static PaddleTexture getPaddleTextureByName(final String theme) {
        return Arrays.asList(PaddleTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get();
    }
}
