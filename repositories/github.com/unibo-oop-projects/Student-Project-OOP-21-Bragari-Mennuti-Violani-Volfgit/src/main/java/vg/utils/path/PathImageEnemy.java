package vg.utils.path;

import java.util.List;

/**
 * Utility class for path of enemies.
 */
public final class PathImageEnemy {
    private static final String PATH_RADICE = "img/";
    private static final String PATH_BOSS = PATH_RADICE + "boss/";
    private static final String PATH_ENEMY = PATH_RADICE + "enemy/";

    /**
     * Provides the path of the image of the boss, for the animation.
     */
    public static final List<String> BOSS = List.of(
            PATH_BOSS + "boss1.png",
            PATH_BOSS + "boss1.png",
            PATH_BOSS + "boss2.png",
            PATH_BOSS + "boss2.png",
            PATH_BOSS + "boss3.png",
            PATH_BOSS + "boss3.png",
            PATH_BOSS + "boss4.png",
            PATH_BOSS + "boss4.png"
    );

    /**
     * Provides the path of the image of the mosquitoes, for the animation.
     */
    public static final List<String> MOSQUITOES = List.of(
            PATH_ENEMY + "alien1.png",
            PATH_ENEMY + "alien1.png",
            PATH_ENEMY + "alien2.png",
            PATH_ENEMY + "alien2.png",
            PATH_ENEMY + "alien3.png",
            PATH_ENEMY + "alien3.png"
    );
    private PathImageEnemy() {
    }
}
