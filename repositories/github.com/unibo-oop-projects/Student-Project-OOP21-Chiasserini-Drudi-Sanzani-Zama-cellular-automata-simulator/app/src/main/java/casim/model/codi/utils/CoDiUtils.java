package casim.model.codi.utils;

import java.util.EnumMap;
import java.util.Random;
import java.util.function.Supplier;

import casim.model.codi.cell.CoDiCell;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;

/**
 * A static utility class for CoDi rules.
 */
public final class CoDiUtils {

    private static final int MAX_PERCENTAGE = 101; 
    private static final Random RNG = new Random();

    private CoDiUtils() {
    }

    /**
     * Return a new {@link EnumMap} filled with a supplier.
     * 
     * @param <T> the type of the values returned by the supplier.
     * @param valuesSupplier the supplier which gives the values to put in each { @link Direction}.
     * @return the new {@link EnumMap}.
     */
    public static <T> EnumMap<CoDiDirection, T> newFilledEnumMap(final Supplier<T> valuesSupplier) {
        final EnumMap<CoDiDirection, T> map = new EnumMap<>(CoDiDirection.class);
        for (final var d: CoDiDirection.values()) {
            map.put(d, valuesSupplier.get());
        }
        return map;
    }

    /**
     * Return the sum of all the values contained in a {@link EnumMap}.
     * 
     * @param enumMap the enumMap of which the method calculates the sum.
     * @return the sum of all the values.
     */
    public static int sumEnumMapValues(final EnumMap<CoDiDirection, Integer> enumMap) {
        return enumMap.values().stream().reduce((n1, n2) -> n1 + n2).get();
    }

    /**
     * Return the sum of all the values of a specific type in a {@link EnumMap}.
     * 
     * @param enumMap the enumMap of which the method calculates the sum.
     * @param value the specific value used by the method.
     * @return the sum of the values.
     */
    public static int sumEnumMapSpecificValues(final EnumMap<CoDiDirection, Integer> enumMap, final int value) {
        return enumMap.values().stream().filter(v -> v == value).reduce((n1, n2) -> n1 + n2).orElse(0);

    }

    /**
     * Make a copy of an {@link EnumMap}.
     * 
     * @param <T> the type of the values of the enumMap
     * @param map the map to copy
     * @return the new {@link EnumMap}
     */
    public static <T> EnumMap<CoDiDirection, T> enumMapCopy(final EnumMap<CoDiDirection, T> map) {
        return new EnumMap<>(map);
    }

    /**
     * Return an {@link EnumMap} containing valueA if chromosome in that direction is true, else return valueB.
     * 
     * @param cell the cell of which calculate the neighbors previous input.
     * @param valueA the value put in the map if chromosome direction is true.
     * @param valueB the value put in the map if chromosome direction is false.
     * @return the filled {@link EnumMap}.
     */
    public static EnumMap<CoDiDirection, Integer> conditionalFillNeighborsPreviosInput(final CoDiCell cell,
            final int valueA, final int valueB) {
        final EnumMap<CoDiDirection, Integer> map = new EnumMap<>(CoDiDirection.class);
        for (final var d: CoDiDirection.values()) {
            map.put(d, cell.getChromosome().get(d) ? valueA : valueB);
        }
        return map;
    }

    /**
     * Return the {@link Coordinates3D} of the neighbours of a cell in a specific {@link CoDiDirection}. 
     * 
     * @param coord the {@link Coordinates3D} of the current cell.
     * @param direction the direction of the neighbour.
     * @return the resultant {@link Coordinates3D}.
     */
    public static Coordinates3D<Integer> getNeighbourCoordinates(final Coordinates3D<Integer> coord,
            final CoDiDirection direction) {
        return CoordinatesUtil.sumInt(coord, direction.getDirectionOffset());
    }

    /**
     * Return true with a specific probability take as input.
     * 
     * @param probability the probability with which the method return true.
     * @return true with the probability take as input, else return false.
     */
    public static boolean booleanWithSpecificProbability(final int probability) {
        if (probability < 0 || probability > 100) {
            throw new IllegalArgumentException();
        }
        return RNG.nextInt(MAX_PERCENTAGE) < probability;
    }

    /**
     * Return a random {@link CoDiDirection}.
     * 
     * @return a random {@link CoDiDirection}.
     */
    public static CoDiDirection getRandomDirection() {
        return CoDiDirection.values()[RNG.nextInt(CoDiDirection.values().length)];
    }

}
