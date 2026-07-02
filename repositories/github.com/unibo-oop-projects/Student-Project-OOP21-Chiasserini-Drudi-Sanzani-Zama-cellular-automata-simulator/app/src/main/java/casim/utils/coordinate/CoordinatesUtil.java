package casim.utils.coordinate;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class that defines methods for the {@link Coordinates} type.
 */
public final class CoordinatesUtil {

    private static final Random RNG = new Random();

    private CoordinatesUtil() {

    }

    /**
     * Sum two {@link Coordinates2D} objects returning a new {@link Coordinates2D} with Integer values.
     * 
     * The sum operation generates a {@link Coordinates2D} that has:
     *  - X equals to the sum of the X values of a and b
     *  - Y equals to the sum of the Y values of a and b
     * 
     * @param a first element of the sum.
     * @param b second element of the sum.
     * @return a new {@link Coordinates2D} object representing the sum of a and b.
     */
    public static Coordinates2D<Integer> sumInt(final Coordinates2D<Integer> a, final Coordinates2D<Integer> b) {
        return CoordinatesUtil.of(a.getX() + b.getX(), a.getY() + b.getY());
    }

    /**
     * Create a new {@link Coordinates2D} object from the values given as arguments.
     * 
     * @param <T> type of the {@link Coordinates2D} involved.
     * @param x value to be used as coordinate x.
     * @param y value to be used as coordinate y.
     * @return a {@link Coordinates2D} with the x and y values given as arguments.
     */
    public static <T extends Number> Coordinates2D<T> of(final T x, final T y) {
        return new Coordinates2D<T>(x, y);
    }

    /**
     * Checks if the {@link Coordinates2D} given as argument is inside the rectangle formed
     * by the {@link Coordinates2D} topLeft and bottomRight.
     * The check is inclusive for the topLeft {@link Coordinates2D} but not for bottomRight.
     * 
     * @param <T> type of the {@link Coordinates2D} involved.
     * @param coord the {@link Coordinates2D} to be checked against topLeft and bottomRight.
     * @param topLeft the {@link Coordinates2D} representing the point at the top left of
     *          the rectangle that coord has to be inside of.
     * @param bottomRight the {@link Coordinates2D} representing the point at the bottom
     *          right of the rectangle that coord has to be inside of.
     * @return True if coord is inside the rectangle, false otherwise.
     */
    public static <T extends Number & Comparable<T>> boolean  isValid(final Coordinates2D<T> coord,
            final Coordinates2D<T> topLeft, final Coordinates2D<T> bottomRight) {
        return coord.getX().compareTo(topLeft.getX()) >= 0 && coord.getY().compareTo(topLeft.getY()) >= 0 
                && coord.getX().compareTo(bottomRight.getX()) < 0 && coord.getY().compareTo(bottomRight.getY()) < 0;
    }

    /**
     * Checks if the {@link Coordinates2D} given as argument is inside the rectangle formed by
     * (0, 0) as topLeft and bottomRight.
     * 
     * @param <T> type of the {@link Coordinates2D} involved.
     * @param coord the {@link Coordinates2D} to be checked against the rectangle.
     * @param bottomRight the {@link Coordinates2D} representing the point at the bottom right
     *          of the rectangle that coord has to be inside of.
     * @return True if coord is inside the rectangle, false otherwise.
     */
    public static <T extends Number & Comparable<T>> boolean isValid(final Coordinates2D<T> coord,
            final Coordinates2D<T> bottomRight) {
        final var coordI = CoordinatesUtil.of(coord.getX().intValue(), coord.getY().intValue());
        final var bottomRightI = CoordinatesUtil.of(bottomRight.getX().intValue(), bottomRight.getY().intValue());
        return isValid(coordI, CoordinatesUtil.of(0, 0), bottomRightI);
    }

    /**
     * Checks if the {@link Coordinates2D} given as argument are less than the values of maxX and maxY.
     * 
     * @param <T> type of the {@link Coordinates2D} involved.
     * @param coord the {@link Coordinates2D} to be checked against the other parameters.
     * @param maxX the value that the X coordinate of coord cannot be greater of.
     * @param maxY the value that the Y coordinate of coord cannot be greator of.
     * @return True if both the X and Y values of coord are smaller than maxX and maxY respectively.
     */
    public static <T extends Number & Comparable<T>> boolean isValid(final Coordinates2D<T> coord, final T maxX,
            final T maxY) {
        return isValid(coord, CoordinatesUtil.of(maxX, maxY));
    }

    /**
     * Returns the Von Neumann's neighbors of the {@link Coordinates2D} given as argument.
     * 
     * @param coord The {@link Coordinates2D} of which to calculate the neighbors.
     * @return a {@link List} of {@link Coordinates2D} composed of the neighbors of the argument coordinate.
     */
    public static List<Coordinates2D<Integer>> get2DNeighbors(final Coordinates2D<Integer> coord) {
        return Stream.of(CoordinatesUtil.of(1, 0), CoordinatesUtil.of(0, 1), CoordinatesUtil.of(0, -1),
                CoordinatesUtil.of(-1, 0))
            .map(x -> CoordinatesUtil.sumInt(x, coord))
            .collect(Collectors.toList());
    }

    /**
     * Returns a {@link Coordinates2D} of type {@link Integer} with random values between 0 (inclusive)
     * and the specified values (exclusive).
     * 
     * @param maxX the upper limit of the X coordinate. Must be positive.
     * @param maxY the upper limit of the Y coordinate. Must be positive.
     * @return {@link Coordinates2D} with random values between 0 and arguments.
     */
    public static Coordinates2D<Integer> random(final int maxX, final int maxY) {
        return CoordinatesUtil.of(RNG.nextInt(maxX), RNG.nextInt(maxY));
    }

    /**
     * @param <T> type of the {@link Coordinates3D} involved.
     * @param x value to be used as coordinate x.
     * @param y value to be used as coordinate y.
     * @param z value to be used as coordinate z.
     * @return a {@link Coordinates3D} with the x, y and z values given as arguments.
     */
    public static <T extends Number> Coordinates3D<T> of(final T x, final T y, final T z) {
        return new Coordinates3D<T>(x, y, z);
    }

    /**
     * Sum two {@link Coordinates3D} objects returning a new {@link Coordinates3D} with Integer values.
     * 
     * The sum operation generates a {@link Coordinates3D} that has:
     *  - X equals to the sum of the X values of a and b
     *  - Y equals to the sum of the Y values of a and b
     * 
     * @param a first element of the sum.
     * @param b second element of the sum.
     * @return a new {@link Coordinates3D} object representing the sum of a and b.
     */
    public static Coordinates3D<Integer> sumInt(final Coordinates3D<Integer> a, final Coordinates3D<Integer> b) {
        return CoordinatesUtil.of(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

    /**
     * Checks if the {@link Coordinates3D} given as argument is inside the rectangle formed by
     * the {@link Coordinates3D} topLeft and bottomRight.
     * The check is inclusive for the topLeft {@link Coordinates3D} but not for bottomRight.
     * 
     * @param <T> type of the {@link Coordinates3D} involved.
     * @param coord the {@link Coordinates3D} to be checked against topLeft and bottomRight.
     * @param topLeft the {@link Coordinates3D} representing the point at the top left of the 
     *          ectangle that coord has to be inside of.
     * @param bottomRight the {@link Coordinates3D} representing the point at the bottom right
     *          of the rectangle that coord has to be inside of.
     * @return True if coord is inside the rectangle, false otherwise.
     */
    public static <T extends Number & Comparable<T>> boolean  isValid(final Coordinates3D<T> coord,
            final Coordinates3D<T> topLeft, final Coordinates3D<T> bottomRight) {
        return coord.getX().compareTo(topLeft.getX()) >= 0 && coord.getY().compareTo(topLeft.getY()) >= 0 
                &&  coord.getZ().compareTo(topLeft.getZ()) >= 0 && coord.getX().compareTo(bottomRight.getX()) < 0 
                && coord.getY().compareTo(bottomRight.getY()) < 0 && coord.getZ().compareTo(bottomRight.getZ()) < 0;
    }

    /**
     * Checks if the {@link Coordinates3D} given as argument is inside the rectangle formed
     * by (0, 0, 0) as topLeft and bottomRight.
     * 
     * @param <T> type of the {@link Coordinates3D} involved.
     * @param coord the {@link Coordinates3D} to be checked against the rectangle.
     * @param bottomRight the {@link Coordinates3D} representing the point at the bottom
     *          right of the rectangle that coord has to be inside of.
     * @return True if coord is inside the rectangle, false otherwise.
     */
    public static <T extends Number & Comparable<T>> boolean isValid(final Coordinates3D<T> coord,
            final Coordinates3D<T> bottomRight) {
        final var coordI = CoordinatesUtil.of(coord.getX().intValue(), coord.getY().intValue(),
                coord.getZ().intValue());
        final var bottomRightI = CoordinatesUtil.of(bottomRight.getX().intValue(), bottomRight.getY().intValue(),
                bottomRight.getZ().intValue());
        return isValid(coordI, CoordinatesUtil.of(0, 0, 0), bottomRightI);
    }

    /**
     * Checks if the {@link Coordinates3D} given as argument are less than the values of maxX, maxY and maxZ.
     * 
     * @param <T> type of the {@link Coordinates3D} involved.
     * @param coord the {@link Coordinates3D} to be checked against the other parameters.
     * @param maxX the value that the X coordinate of coord cannot be greater of.
     * @param maxY the value that the Y coordinate of coord cannot be greater of.
     * @param maxZ the value that the Z coordinate of coord cannot be greater of.
     * @return True if both the X and Y values of coord are smaller than maxX and maxY respectively.
     */
    public static <T extends Number & Comparable<T>> boolean isValid(final Coordinates3D<T> coord, final T maxX,
            final T maxY, final T maxZ) {
        return isValid(coord, CoordinatesUtil.of(maxX, maxY, maxZ));
    }

    /**
     * Returns the Von Neumann's neighbors of the {@link Coordinates3D} given as argument.
     * 
     * @param coord the {@link Coordinates3D} of which to calculate the neighbors.
     * @return a {@link List} of {@link Coordinates3D} composed of the neighbors of the argument coordinate.
     */
    public static List<Coordinates3D<Integer>> get3DNeighbors(final Coordinates3D<Integer> coord) {
        return Stream.of(CoordinatesUtil.of(1, 0, 0), CoordinatesUtil.of(-1, 0, 0),
                CoordinatesUtil.of(0, 1, 0), CoordinatesUtil.of(0, -1, 0),
                CoordinatesUtil.of(0, 0, 1), CoordinatesUtil.of(0, 0, -1))
            .map(x -> CoordinatesUtil.sumInt(x, coord))
            .collect(Collectors.toList());
    }

    /**
     * Returns a {@link Coordinates3D} of type {@link Integer} with
     * random values between 0 (inclusive) and arguments (exclusive).
     * 
     * @param maxX the upper limit of the X coordinate. Must be positive.
     * @param maxY the upper limit of the Y coordinate. Must be positive.
     * @param maxZ the upper limit of the Z coordinate. Must be positive.
     * @return a {@link Coordinates3D} with random values between 0 and arguments.
     */
    public static Coordinates3D<Integer> random(final int maxX, final int maxY, final int maxZ) {
        return CoordinatesUtil.of(RNG.nextInt(maxX), RNG.nextInt(maxY), RNG.nextInt(maxZ));
    }
}
