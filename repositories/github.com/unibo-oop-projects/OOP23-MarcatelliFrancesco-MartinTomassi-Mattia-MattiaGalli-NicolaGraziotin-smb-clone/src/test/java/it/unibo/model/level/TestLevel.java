package it.unibo.model.level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.level.factory.LevelFactoryImpl;
import it.unibo.model.tiles.Tile;
import it.unibo.commons.Constants;
import it.unibo.commons.Point2D;

/**
 * JUnit test class for LevelImpl.
 */
class TestLevel {

    private static final int NUM_SAWS = 2;
    private static final int NUM_PLATFORMS = 6;
    private static final int NUM_ROWS = 30;
    private static final int NUM_COLUMNS = 36;
    private Level level;

    /**
     * Initialize the LevelImpl instance before each test.
     */
    @BeforeEach
    void setUp() {
        level = new LevelFactoryImpl().createLevel(Constants.SOURCE_MAP);
        assertNotNull(level);
    }

    /**
     * Test the {@link LevelImpl#getMeatBoy()} method.
     */
    @Test
    void testGetMeatBoy() {
        final MeatBoy meatBoy = level.getMeatBoy();
        assertNotNull(meatBoy);
        assertNotEquals(0, meatBoy.getX());
        assertNotEquals(0, meatBoy.getY());
    }

    /**
     * Test the {@link LevelImpl#getMeatBoyStartCoord()} method.
     */
    @Test
    void testGetMeatBoyStartCoord() {
        final Point2D<Double, Double> startCoord = level.getMeatBoyStartCoord();
        assertNotNull(startCoord);
        assertNotEquals(0, startCoord.getX());
        assertNotEquals(0, startCoord.getY());
    }

    /**
     * Test the {@link LevelImpl#getBandageGirl()} method.
     */
    @Test
    void testGetBandageGirl() {
        final BandageGirl bandageGirl = level.getBandageGirl();
        assertNotNull(bandageGirl);
        assertNotEquals(0, bandageGirl.getX());
        assertNotEquals(0, bandageGirl.getY());
    }

    /**
     * Test the {@link LevelImpl#getStationary()} method.
     */
    @Test
    void testGetStationary() {
        final List<List<Optional<Tile>>> stationary = level.getStationary();
        assertNotNull(stationary);
        assertEquals(NUM_ROWS, stationary.size());
        assertEquals(NUM_COLUMNS, stationary.get(0).size());
    }

    /**
     * Test the {@link LevelImpl#getPlatforms()} method.
     */
    @Test
    void testGetPlatforms() {
        final List<Platform> platforms = level.getPlatforms();
        assertNotNull(platforms);
        assertEquals(NUM_PLATFORMS, platforms.size());
    }

    /**
     * Test the {@link LevelImpl#getSaws()} method.
     */
    @Test
    void testGetSaws() {
        final List<CircularSaw> saws = level.getSaws();
        assertNotNull(saws);
        assertEquals(NUM_SAWS, saws.size());
    }

    /**
     * Test the {@link LevelImpl#getNumCols()} method.
     */
    @Test
    void testGetNumCols() {
        assertEquals(NUM_COLUMNS, level.getNumCols());
    }

    /**
     * Test the {@link LevelImpl#getNumRows()} method.
     */
    @Test
    void testGetNumRows() {
        assertEquals(NUM_ROWS, level.getNumRows());
    }

}
