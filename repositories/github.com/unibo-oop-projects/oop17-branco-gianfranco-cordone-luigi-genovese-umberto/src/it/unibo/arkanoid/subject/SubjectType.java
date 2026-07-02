package it.unibo.arkanoid.subject;

import java.util.ArrayList;
import java.util.List;

import it.unibo.arkanoid.utility.Pair;

/**
 * Enumeration witch define the type of {@link Subject}.
 *
 */

public enum SubjectType {
    /**
     * Represent the object used by player to destroy bricks.
     */
    BALL,
    /**
     * Represent the object that must be destroyed.
     */
    BRICK,
    /**
     * Represent the rectangular object controlled by player.
     */
    PADDLE,
    /**
     * Represent a rectangle that is not overable by the Ball.
     */
    BLOCK,
    /**
     * Represent powerUp entity with bonus or malus effect.
     */
    POWER_UP;

    private static List<Pair<SubjectType>> collisionPairs;
    static {

        collisionPairs = new ArrayList<>();
        collisionPairs.add(new Pair<>(BALL, BRICK));
        collisionPairs.add(new Pair<>(BALL, PADDLE));
        collisionPairs.add(new Pair<>(BALL, BLOCK));
        collisionPairs.add(new Pair<>(BRICK, BALL));
        collisionPairs.add(new Pair<>(PADDLE, BALL));
        collisionPairs.add(new Pair<>(PADDLE, POWER_UP));
        collisionPairs.add(new Pair<>(POWER_UP, PADDLE));
        collisionPairs.add(new Pair<>(BLOCK, BALL));
    }

    /**
     * 
     * @param a
     *            first SubjectType
     * @param b
     *            second SubjectType
     * @return if two object collides
     */
    public static boolean pairCollides(final SubjectType a, final SubjectType b) {
        return collisionPairs.stream().filter(p -> p.getFirst().equals(a)).filter(p -> p.getSecond().equals(b))
                .count() == 1;
    }

}
