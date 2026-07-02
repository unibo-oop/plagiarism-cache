package bzzbomber.test;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import bzzbomber.model.Level;
import bzzbomber.model.Model;

/**
 * This class tests Level's public methods.
 */
public class TestLevel {

    private final Model m = new Model();
    private final Level level = new Level(this.m, 0);

    /**
     * Checking the defensiveness of the matrix from level.
     */
    @Test
    public void testGetGameMap() {
        final int[][] map = level.getGameMap();
        map[0][0] = 100;
        final int val1 = map[0][0];
        final int val2 = level.getGameMap()[0][0];
        Assert.assertFalse(val1 == val2);
    }

    /**
     * Checking that entityManager exists.
     */
    @Test
    public void testGetEntityManager() {
        Assert.assertNotNull(level.getEntityManager());
    }

    /**
     * Checking number of rows is greater than zero.
     */
    @Test
    public void testGetRows() {
        Assert.assertTrue(level.getRows() > 0);
    }

    /**
     * Checking number of columns is greater than zero.
     */
    @Test
    public void testGetCols() {
        Assert.assertTrue(level.getCols() > 0);
    }

    /**
     * Checking the defensiveness of returned unmodified list.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetInsectsPositions() {
        final List<Point> ip = level.getInsectsPositions();
        final Point p = new Point(1, 2);
        ip.set(0, p);
    }

    /**
     * Checking the defensiveness of returned unmodified list.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetBoxPositions() {
        final List<Point> ip = level.getBoxPositions();
        final Point p = new Point(1, 2);
        ip.set(0, p);
    }

    /**
     * Checking the defensiveness of returned unmodified list.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetDoorPositions() {
        final List<Point> ip = level.getDoorPositions();
        final Point p = new Point(1, 2);
        ip.set(0, p);
    }

    /**
     * Checking the defensiveness of returned unmodified list.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testHealthPositions() {
        final List<Point> ip = level.getHealthPositions();
        final Point p = new Point(1, 2);
        ip.set(0, p);
    }

    /**
     * Checking that number of insects is greater than zero.
     */
    @Test
    public void testNumOfInsects() {
        final int count = level.getNumOfInsects();
        Assert.assertTrue(count > 0);
    }

    /**
     * Given a rectangle and its position, checking if it intersects with a wall.
     */
    @Test
    public void testIntersectsWithWall() {
        final Point p = new Point(0, 0);
        final Rectangle r = new Rectangle(p, new Dimension(5, 5));
        Assert.assertTrue(level.intersectsWithWall(r));
    }
}
