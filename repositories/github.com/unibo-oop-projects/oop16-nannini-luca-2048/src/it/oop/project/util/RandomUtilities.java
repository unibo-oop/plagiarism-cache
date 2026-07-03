package it.oop.project.util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for getting random values.
 * 
 */
public class RandomUtilities {

    private static final int MULTIPLIER_VALUE = 10;

    private RandomUtilities() {
    }

    /**
     * Returns random coordinates within 0 and max specified value.
     * 
     * @param max
     *            max value a coordinate can be.
     * @return random coordinates generated.
     */
    public static Point getRandomCoordinates(final int max) {
        return new Point(getRandomInt(max), getRandomInt(max));
    }

    private static int getRandomInt(final int max) {
        return ThreadLocalRandom.current().nextInt(0, max + 1);
    }

    /**
     * Returns a random K value with specified probability among provided ones.
     * 
     * @param map
     *            a Map containing <K> candidate values mapped with probability
     *            (provided with double precision i.e.: 0.1 for 10%)
     * @return drawn random value
     */
    public static <K> K getRandAmongValues(final Map<K, Double> map) {
        if (checkPercentages(map.values())) {
            List<K> list = new ArrayList<>();
            for (K k : map.keySet()) {
                for (int i = 0; i < map.get(k) * MULTIPLIER_VALUE; i++) {
                    list.add(k);
                }
            }
            int randomValue = getRandomInt(list.size() - 1);
            return list.get(randomValue);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static boolean checkPercentages(
            final Collection<Double> percentages) {
        double total = 0;
        for (Double el : percentages) {
            total += el;
        }
        return total == 1;
    }

}
