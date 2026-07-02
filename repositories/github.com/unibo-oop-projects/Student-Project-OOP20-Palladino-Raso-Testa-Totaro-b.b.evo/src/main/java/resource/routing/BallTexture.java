package resource.routing;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ball Texture resource path.
 *
 */
public enum BallTexture implements Serializable {

    /**
     * Default theme, ball.
     */
    BALL_DEFAULT("Images/ball/defaultBall.png", "Default"),

    /**
     * Arkanoid theme, ball.
     */
    BALL_ARKANOID("Images/ball/arkanoidBall.png", "Arkanoid"),

    /**
     * Galaga theme, ball.
     */
    BALL_GALAGA("Images/ball/galagaBall.png", "Galaga"),

    /**
     * Pacman theme, ball.
     */
    BALL_PACMAN("Images/ball/pacmanBall.png", "Pacman"),

    /**
     * Crash Bandicoot theme, ball.
     */
    BALL_CRASH("Images/ball/crashBall.png", "Crash"),

    /**
     * Super Mario theme, ball.
     */
    BALL_SUPERMARIO("Images/ball/marioBall.png", "SuperMario");

    private String path;
    private String theme;

    BallTexture(final String path, final String theme) {
        this.path = path;
        this.theme = theme;
    }

    /**
     * 
     * @return the path of ball
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * @return the name of ball
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 
     * @return a list of all available ball
     */
    public static List<String> getBallTexturedNames() {
        return Arrays.asList(BallTexture.values()).stream()
                                          .map(i -> i.getTheme())
                                          .collect(Collectors.toList());
    }

    /**
     * The reference to the enumeration of a ball by name.
     * @param theme to map
     * @return ball enum
     */
    public static BallTexture getBallTextureByName(final String theme) {
        return Arrays.asList(BallTexture.values()).stream()
                                                 .filter(i -> i.getTheme().equals(theme))
                                                 .findFirst()
                                                 .get();
    }
}
