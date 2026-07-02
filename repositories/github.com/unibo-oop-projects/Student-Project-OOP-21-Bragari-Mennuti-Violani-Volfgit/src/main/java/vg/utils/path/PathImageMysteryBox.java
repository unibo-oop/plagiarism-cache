package vg.utils.path;

import java.util.List;

/**
 * Utility class for path of mystery box.
 */
public final class PathImageMysteryBox {

    private static final String PATH_RADICE = "img/mysteryBox/";
    private static final String PATH_COIN = PATH_RADICE + "coins/";

    /**
     * Provides the path of the image of the mystery box.
     */
    public static final String MYSTERY_BOX = PATH_RADICE + "box.png";

    /**
     * Provides the path of the image of special mystery box, for the animation.
     */
    public static final List<String> MYSTERY_BOSS = List.of(
            PATH_RADICE + "BoxBoss.png",
            PATH_RADICE + "BoxBoss1.png"
    );

    /**
     * Provides the path of the image of the coins Time, for the animation.
     */
    public static final String COIN_TIME = PATH_COIN + "coin-T.png";
    /**
     * Provides the path of the image of the coins Speed, for the animation.
     */
    public static final String COIN_SPEED = PATH_COIN + "coin-S.png";
    /**
     * Provides the path of the image of the coins Score, for the animation.
     */
    public static final String COIN_SCORE = PATH_COIN + "coin-P.png";
    /**
     * Provides the path of the image of the coins to kill mosquitoes, for the animation.
     */
    public static final String COIN_KILL_ALL_MOQUETOES = PATH_COIN + "coin-C.png";

    private PathImageMysteryBox() {
    }
}
