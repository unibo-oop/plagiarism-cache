package ballblast.view.imageloader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 * Enumeration used to select ball colors.
 */
public enum BallColors {
    /**
     * Ball color blue.
     */
    BALL_BLUE("/view/balls/blue.png"),
    /**
     * Ball color red.
     */
    BALL_RED("/view/balls/red.png"),
    /**
     * Ball color green.
     */
    BALL_GREEN("/view/balls/green.png"),
    /**
     * Ball color yellow.
     */
    BALL_YELLOW("/view/balls/yellow.png"),
    /**
     * Ball color bordeaux.
     */
    BALL_BORDEAUX("/view/balls/bordeaux.png"),
    /**
     * Ball color light blue.
     */
    BALL_LIGHTBLUE("/view/balls/lightblue.png"),
    /**
     * Ball color purple.
     */
    BALL_PURPLE("/view/balls/purple.png"),
    /**
     * Ball color orange.
     */
    BALL_ORANGE("/view/balls/orange.png");

    private static final List<BallColors> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private final String ballPath;

    BallColors(final String ballPath) {
        this.ballPath = ballPath;
    }

    /**
     * 
     * @return A random {@link BallColors} color.
     */
    public static String getRandomColor() {
        return VALUES.get(RANDOM.nextInt(SIZE)).getBallPath();
    }

    /**
     * 
     * @return the ball image path.
     */
    public String getBallPath() {
        return this.ballPath;
    }
}
