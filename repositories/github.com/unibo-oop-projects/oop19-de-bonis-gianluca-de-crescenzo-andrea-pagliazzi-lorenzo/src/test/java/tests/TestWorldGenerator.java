package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import javafx.geometry.Point2D;
import zombieversity.model.world.WorldGenerator;
import zombieversity.model.world.WorldGeneratorImpl;


/**
 * Test world generation.
 */
public class TestWorldGenerator {

    private static final int TILE_SZ = 128;
    private static final String AMBIENT_PATH = "/ambient.png";

    private WorldGenerator wg;

    @Test
    public final void testRandomGenerate() {
        final int numObj = 20;
        final int width = 200;
        final int height = 200;
        wg = new WorldGeneratorImpl(AMBIENT_PATH, TILE_SZ);
        final Map<Point2D, Integer> comparator = new HashMap<>();
        final Map<Point2D, Integer> comparer  = new HashMap<>();
        wg.generateRandom(comparator, numObj, width, height);
        wg.generateRandom(comparer, numObj, width, height);
        /**
         * Same input doesn't give same output - could happen rarely.
         */
        comparator.forEach((k, v) -> {
            if (comparer.containsKey(k)) {
                assertNotEquals(comparer.get(k), v);
           }
        });
    }
    @Test
    public final void testFillNextTo() {
        final int numObj = 20;
        final int tileSz = 128;
        final Point2D center = new Point2D(numObj, numObj);

        wg = new WorldGeneratorImpl(AMBIENT_PATH, tileSz);

        final Map<Point2D, Integer> comparator = new HashMap<>();
        final Map<Point2D, Integer> comparer = new HashMap<>();

        /**
         * Given point (20, 20) -> 1
         * I want to fill next to him with 1.
         */
        comparator.put(center, 1);
        comparator.put(new Point2D(19, 19), 1);
        comparator.put(new Point2D(20, 19), 1);
        comparator.put(new Point2D(21, 19), 1);

        comparator.put(new Point2D(19, 20), 1);
        comparator.put(new Point2D(21, 20), 1);

        comparator.put(new Point2D(19, 21), 1);
        comparator.put(new Point2D(20, 21), 1);
        comparator.put(new Point2D(21, 21), 1);

        comparer.put(center, 1);
        wg.fillNextTo(comparer, 1, center);

        comparator.forEach((k, v) -> {
            assertEquals(comparer.get(k), v);
        });


    }
    @Test
    public final void testFillBorder() {
        /**
         * Verified if others are verified.
         */
    }
    @Test
    public final void testFillRow() {
        final int count = 5;

        wg = new WorldGeneratorImpl(AMBIENT_PATH, TILE_SZ);

        final Map<Point2D, Integer> comparator  = new HashMap<>();
        final Map<Point2D, Integer> comparer  = new HashMap<>();

        comparator.put(new Point2D(0, 0), 1);
        comparator.put(new Point2D(1, 0), 1);
        comparator.put(new Point2D(2, 0), 1);
        comparator.put(new Point2D(3, 0), 1);
        comparator.put(new Point2D(4, 0), 1);

        wg.fillRow(comparer, 1, new Point2D(0, 0), count);

        comparator.forEach((k, v) -> {
            assertEquals(comparer.get(k), v);
        });
    }
    @Test
    public final void testFillColumn() {
        final int count = 5;

        wg = new WorldGeneratorImpl(AMBIENT_PATH, TILE_SZ);

        final Map<Point2D, Integer> comparator  = new HashMap<>();
        final Map<Point2D, Integer> comparer  = new HashMap<>();

        comparator.put(new Point2D(0, 0), 1);
        comparator.put(new Point2D(0, 1), 1);
        comparator.put(new Point2D(0, 2), 1);
        comparator.put(new Point2D(0, 3), 1);
        comparator.put(new Point2D(0, 4), 1);

        wg.fillColumn(comparer, 1, new Point2D(0, 0), count);

        comparator.forEach((k, v) -> {
            assertEquals(comparer.get(k), v);
        });
    }
    @Test
    public final void testFillAngles() {
        final double w = 5;
        final double h = 5;
        final Point2D center = new Point2D(2, 2);
        wg = new WorldGeneratorImpl(AMBIENT_PATH, TILE_SZ);
        final Map<Point2D, Integer> comparator  = new HashMap<>();
        final Map<Point2D, Integer> comparer  = new HashMap<>();

        comparator.put(new Point2D(0, 0), 1);
        comparator.put(new Point2D(0, 4), 1);
        comparator.put(new Point2D(4, 0), 1);
        comparator.put(new Point2D(4, 4), 1);

        wg.fillAngles(comparer, 1, center, w, h);
        comparator.forEach((k, v) -> {
            assertEquals(comparer.get(k), v);
        });
    }

}
