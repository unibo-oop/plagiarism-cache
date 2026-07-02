package spacesurvival.model.gameobject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import spacesurvival.utilities.RandomUtils;

/**
 * Contains the types of screen borders.
 */
public enum Edge {
    /**
     * Left edge of game screen.
     */
    LEFT,
    /**
     * Top edge of game screen.
     */
    TOP,
    /**
     * Right edge of game screen.
     */
    RIGHT,
    /**
     * Bottom edge of game screen.
     */
    BOTTOM;

    private static final List<Edge> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();

    /**
     * @return a random edge
     */
    public static Edge random()  {
        return VALUES.get(RandomUtils.RANDOM.nextInt(SIZE));
    }

}
