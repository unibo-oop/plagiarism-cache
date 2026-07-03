package breakout.view.graphics;

import java.util.EnumSet;

import javafx.scene.paint.Color;

/**
 * Available colors in the game.<br/>
 * <b>Classic</b>:
 * <li>{@link #CLASSICRED}
 * <li>{@link #CLASSICORANGE}
 * <li>{@link #CLASSICBROWN}
 * <li>{@link #CLASSICYELLOW}
 * <li>{@link #CLASSICRED}
 * <li>{@link #CLASSICGREEN}
 * <li>{@link #CLASSICGREY}<br/>
 * <b>Advanced</b>:
 * <li>{@link #BLACK}
 * <li>{@link #BLUE}
 * <li>{@link #DARKBLUE}
 * <li>{@link #FIRE}
 * <li>{@link #GREEN}
 * <li>{@link #LIGHTYELLOW}
 * <li>{@link #PINK}
 * <li>{@link #RED}
 * <li>{@link #SILVER}<br/>
 */
public enum Colors {

    // Classic Game
    /** Classic Red. */
    CLASSICRED(Color.rgb(234, 78, 89)),

    /** Classic Orange. */
    CLASSICORANGE(Color.rgb(218, 109, 66)),

    /** Classic Brown. */
    CLASSICBROWN(Color.rgb(187, 118, 53)),

    /** Classic Yellow. */
    CLASSICYELLOW(Color.rgb(164, 169, 49)),

    /** Classic Green. */
    CLASSICGREEN(Color.rgb(58, 176, 82)),

    /** Classic Blue. */
    CLASSICBLUE(Color.rgb(60, 80, 205)),

    /** Classic Grey. */
    CLASSICGREY(Color.rgb(142, 142, 142)),

    // Advanced Game
    /** Black. */
    BLACK(Color.rgb(50, 60, 57)),

    /** Blue. */
    BLUE(Color.rgb(91, 110, 225)),

    /** Darkblue. */
    DARKBLUE(Color.rgb(63, 63, 116)),

    /** Fire. */
    FIRE(null),

    /** Green. */
    GREEN(Color.rgb(75, 105, 47)),

    /** Light Yellow. */
    LIGHTYELLOW(Color.rgb(217, 160, 102)),

    /** Olive Green. */
    OLIVEGREEN(Color.rgb(138, 111, 48)),

    /** Pink. */
    PINK(Color.rgb(217, 87, 99)),

    /** Red. */
    RED(Color.rgb(172, 50, 50)),

    /** Silver. */
    SILVER(Color.rgb(203, 219, 252)),

    /** Yellow. */
    YELLOW(Color.rgb(223, 113, 38));

    /**
     * EnumSet of Colors available for bricks in classic mode.
     */
    public static final EnumSet<Colors> CLASSIC = EnumSet.of(CLASSICRED, CLASSICORANGE, CLASSICBROWN, CLASSICYELLOW,
            CLASSICGREEN, CLASSICBLUE, CLASSICGREY);
    /**
     * EnumSet of Colors available for simple bricks,paddles and balls in
     * advanced mode.
     */
    public static final EnumSet<Colors> ADVANCED_SIMPLE = EnumSet.of(BLACK, BLUE, DARKBLUE, GREEN, LIGHTYELLOW,
            OLIVEGREEN, PINK, RED, YELLOW);
    /**
     * EnumSet of Colors available for hard bricks in advanced mode.
     */
    public static final EnumSet<Colors> ADVANCED_HARD = EnumSet.of(BLACK, BLUE, DARKBLUE, GREEN, LIGHTYELLOW,
            OLIVEGREEN, PINK, RED, YELLOW);
    /**
     * EnumSet of Colors available for unbreakable bricks in advanced mode.
     */
    public static final EnumSet<Colors> ADVANCED_UNBREAKABLE = EnumSet.of(SILVER);

    private final Color color;

    Colors(final Color c) {
        this.color = c;
    }

    /**
     * @return the RGB color
     */
    public Color get() {
        return this.color;
    }
}
