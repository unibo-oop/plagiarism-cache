package vg.utils.path;

import java.util.List;

/**
 * Utility class for path of player.
 */
public final class PathImagePlayer {
    private static final String PATH_RADICE = "img/";
    private static final String PATH_PLAYER = PATH_RADICE + "player/";

    /**
     * Provides the path of the image of the player, for the animation.
     */
    public static final List<String> PLAYER = List.of(
            PATH_PLAYER + "player1.png",
            PATH_PLAYER + "player1.png",
            PATH_PLAYER + "player1.png",
            PATH_PLAYER + "player2.png",
            PATH_PLAYER + "player2.png",
            PATH_PLAYER + "player2.png"
    );

    /**
     * Provides the path of the image of the shield player.
     */
    public static final String SHIELD = PATH_RADICE + "shield.png";
    private PathImagePlayer() {
    }
}
