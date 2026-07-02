package it.unibo.michelito.model.bomb.api;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Represents the type of a bomb.
 */
public enum BombType {
    /**
     * A bomb that explodes in a cross shape.
     */
    STANDARD,

    /**
     * A bomb that explodes in a cross shape with a really long range
     * and can go through boxes.
     */
    NUKE,

    /**
     * A bomb that explodes in a cross shape and has a longer range.
     */
    PASS_THROUGH,

    /**
     * A bomb that explodes in a cross shape and has a longer range
     * and can go through boxes.
     */
    LONG;

    private static final Random RANDOM = new Random();
    private static final List<BombType> TYPES = Arrays.asList(values());
    /**
     * Get a random bomb type.
     *
     * @return A random bomb type.
     */
    public static BombType getRandomType() {
        return TYPES.get(RANDOM.nextInt(TYPES.size()));
    }
}
