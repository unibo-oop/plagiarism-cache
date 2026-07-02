package spacesurvival.utilities.path.animation;

import spacesurvival.utilities.path.MainFolder;

import java.util.List;

public final class AnimationChase {
    /**
     * Name of the package of enemies animations.
     */
    public static final String ENEMIES = "/enemies";
    /**
     * Name of the package of chase enemies animations.
     */
    public static final String TYPE = "/chase";

    /**
     * Chase enemy animation 0.
     */
    public static final String CHASE0 = MainFolder.GAME_OBJECT + ENEMIES + TYPE + "/chase.png";
    /**
     * Chase enemy animation 1.
     */
    public static final String CHASE1 = MainFolder.GAME_OBJECT + ENEMIES + TYPE + "/chase_1.png";
    /**
     * Chase enemy animation 2.
     */
    public static final String CHASE2 = MainFolder.GAME_OBJECT + ENEMIES + TYPE + "/chase_2.png";
    /**
     * Chase enemy animation 3.
     */
    public static final String CHASE3 = MainFolder.GAME_OBJECT + ENEMIES + TYPE + "/chase_3.png";
    /**
     * Chase enemy animation 4.
     */
    public static final String CHASE4 = MainFolder.GAME_OBJECT + ENEMIES + TYPE + "/chase_4.png";
    /**
     * List of chase enemies animation.
     */

    public static final List<String> LIST_CHASE = List.of(CHASE1, CHASE2, CHASE3, CHASE4);

    private AnimationChase() {

    }
}
