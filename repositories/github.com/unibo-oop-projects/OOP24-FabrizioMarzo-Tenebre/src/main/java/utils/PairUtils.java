package utils;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Utility class providing operations for working with {@link Pair} objects
 * containing {@link Double} values.
 * <p>
 * These methods are typically used for mathematical operations involving 2D
 * vectors,
 * such as velocity, position, or direction.
 */
public class PairUtils {

    /**
     * Returns the component-wise sum of two pairs.
     *
     * @param pair1 the first pair of values (x1, y1)
     * @param pair2 the second pair of values (x2, y2)
     * @return a new {@link Pair} representing the sum (x1 + x2, y1 + y2)
     */
    public static Pair<Double, Double> sum(final Pair<Double, Double> pair1, final Pair<Double, Double> pair2) {
        double x = pair1.getLeft() + pair2.getLeft();
        double y = pair1.getRight() + pair2.getRight();
        return Pair.of(x, y);
    }

    /**
     * Returns the component-wise difference between two pairs (v1 - v2).
     *
     * @param v1 the first pair (minuend)
     * @param v2 the second pair (subtrahend)
     * @return a new {@link Pair} representing the difference (v1.x - v2.x, v1.y -
     *         v2.y)
     */
    public static Pair<Double, Double> diff(final Pair<Double, Double> v1, final Pair<Double, Double> v2) {
        return Pair.of(v1.getLeft() - v2.getLeft(), v1.getRight() - v2.getRight());
    }

    /**
     * Computes the Euclidean norm (magnitude) of a 2D vector represented by a pair.
     *
     * @param v the pair representing the vector (x, y)
     * @return the Euclidean norm √(x² + y²)
     */
    public static double norm2(final Pair<Double, Double> v) {
        double x = v.getLeft();
        double y = v.getRight();
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Multiplies each component of the pair by a scalar value.
     *
     * @param pair  the pair of values (x, y)
     * @param value the scalar to multiply by
     * @return a new {@link Pair} representing the scaled vector (x * value, y *
     *         value)
     */
    public static Pair<Double, Double> mulScale(final Pair<Double, Double> pair, final double value) {
        double x = pair.getLeft() * value;
        double y = pair.getRight() * value;
        return Pair.of(x, y);
    }

    /**
     * Performs component-wise multiplication between two pairs.
     *
     * @param pair1 the first pair (x1, y1)
     * @param pair2 the second pair (x2, y2)
     * @return a new {@link Pair} representing the product (x1 * x2, y1 * y2)
     */
    public static Pair<Double, Double> mul(final Pair<Double, Double> pair1, final Pair<Double, Double> pair2) {
        double x = pair1.getLeft() * pair2.getLeft();
        double y = pair1.getRight() * pair2.getRight();
        return Pair.of(x, y);
    }

    /**
     * Computes the module of the vector represented by the pair.
     *
     * @param vel the pair of velocity components (vx, vy)
     * @return the module of the vector √(vx² + vy²)
     */
    public static Double module(final Pair<Double, Double> vel) {
        Double x = vel.getLeft();
        Double y = vel.getRight();
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Converte un {@code Pair<Double, Double>} in un {@code Pair<Integer, Integer>}
     * troncando i decimali.
     *
     * @param doublePair il pair con valori double
     * @return un nuovo pair con valori interi
     */
    public static Pair<Integer, Integer> toIntPair(final Pair<Double, Double> doublePair) {
        return Pair.of(
                doublePair.getLeft().intValue(),
                doublePair.getRight().intValue());
    }

    /**
     * Normalizes the vector represented by the pair to a unit vector.
     * If the vector magnitude is zero, returns the original pair.
     *
     * @param pair the pair representing the vector (x, y)
     * @return the normalized vector (x / magnitude, y / magnitude) or the original
     *         pair if magnitude is zero
     */
    public static Pair<Double, Double> normalize(final Pair<Double, Double> pair) {
        double x = pair.getLeft();
        double y = pair.getRight();

        double module = Math.sqrt(x * x + y * y);

        if (module > 0) {
            return Pair.of(x / module, y / module);
        } else {
            return pair;
        }
    }

}
