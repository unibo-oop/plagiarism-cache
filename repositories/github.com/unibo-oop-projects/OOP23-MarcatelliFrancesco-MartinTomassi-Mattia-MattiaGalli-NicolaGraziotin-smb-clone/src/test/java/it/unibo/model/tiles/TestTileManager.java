package it.unibo.model.tiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.commons.Constants;
import it.unibo.commons.Point2D;
import it.unibo.model.entity.obstacles.CircularSawImpl;
import it.unibo.model.entity.obstacles.PlatformImpl;
import it.unibo.model.tiles.manager.TileManager;
import it.unibo.model.tiles.manager.TileManagerImpl;
import it.unibo.model.tiles.manager.factory.TileManagerFactoryImpl;

/**
 * JUnit tests for the TileManager class.
 */
class TestTileManager {

    private static final double MEATBOY_X = 60 * Constants.SCALE_PROPORTION;
    private static final double MEATBOY_Y = 460 * Constants.SCALE_PROPORTION;
    private static final double BANDAGEGIRL_X = 660 * Constants.SCALE_PROPORTION;
    private static final double BANDAGEGIRL_Y = 380 * Constants.SCALE_PROPORTION;
    private static final int NUM_TILES = 902;
    private static final int NUM_PLATFORMS = 6;
    private static final int NUM_SAWS = 2;
    private static final int MAP_ROWS = 30;
    private static final int MAP_COLUMNS = 36;
    private TileManager tileManager;

    /**
     * Initializes the test environment before each test method is executed.
     */
    @BeforeEach
    void init() {
        this.tileManager = new TileManagerFactoryImpl()
            .createTileManager(Constants.SOURCE_MAP);
        assertNotNull(this.tileManager);
    }

    /**
     * Tests the initialization of the TileManager.
     */
    @Test
    void testInitialization() {
        assertNotNull(tileManager.getPlatforms());
        assertNotNull(tileManager.getSaws());
        assertNotNull(tileManager.getStationary());
        assertEquals(NUM_TILES, tileManager.getTiles().size());
    }

    /**
     * Tests the {@link TileManagerImpl#getNumCols()} and {@link TileManagerImpl#getNumRows()} methods.
     */
    @Test
    void testGetNumColsAndRows() {
        assertEquals(MAP_COLUMNS, tileManager.getNumCols());
        assertEquals(MAP_ROWS, tileManager.getNumRows());
    }

    /**
     * Tests the {@link TileManagerImpl#getPlatforms()} method.
     */
    @Test
    void testGetPlatforms() {
        assertNotNull(tileManager.getPlatforms());
        assertEquals(NUM_PLATFORMS, tileManager.getPlatforms().size());
    }

    /**
     * Tests the {@link TileManagerImpl#getSaws()} method.
     */
    @Test
    void testGetSaws() {
        assertNotNull(tileManager.getSaws());
        assertEquals(NUM_SAWS, tileManager.getSaws().size());
    }

    /**
     * Tests the {@link TileManagerImpl#getStationary()} method.
     */
    @Test
    void testGetStationary() {
        assertNotNull(tileManager.getStationary());
        assertEquals(MAP_ROWS, tileManager.getStationary().size());
        assertEquals(MAP_COLUMNS, tileManager.getStationary().get(0).size());
    }

    /**
     * Tests the {@link TileManagerImpl#getTiles()} method.
     */
    @Test
    void testTiles() {
        assertNotNull(tileManager.getTiles());
        assertEquals(NUM_TILES, tileManager.getTiles().size());
    }

    /**
     * Tests the {@link TileManagerImpl#getMeatBoy()} method.
     */
    @Test
    void testMeatBoy() {
        assertNotNull(tileManager.getMeatBoy());
        assertEquals(MEATBOY_X, tileManager.getMeatBoy().getX());
        assertEquals(MEATBOY_Y, tileManager.getMeatBoy().getY());
    }

    /**
     * Tests the {@link TileManagerImpl#getBandageGirl()} method.
     */
    @Test
    void testBandageGirl() {
        assertNotNull(tileManager.getBandageGirl());
        assertEquals(BANDAGEGIRL_X, tileManager.getBandageGirl().getX());
        assertEquals(BANDAGEGIRL_Y, tileManager.getBandageGirl().getY());
    }

    /**
     * Tests the {@link TileManagerImpl#setSaw()} method.
     */
    @Test
    void testSetSaw() {
        tileManager.setSaw(new CircularSawImpl(0, 0, 0));
        assertEquals(NUM_SAWS + 1, tileManager.getSaws().size());
    }

    /**
     * Tests the {@link TileManagerImpl#setPlatform()} method.
     */
    @Test
    void testSetPlatform() {
        tileManager.setPlatform(new PlatformImpl(0, 0, 0, 0));
        assertEquals(NUM_PLATFORMS + 1, tileManager.getPlatforms().size());
    }

    /**
     * Tests the {@link TileManagerImpl#setMeatBoyCoord()} method.
     */
    @Test
    void testSetMeatBoyCoord() {
        tileManager.setMeatBoyCoord(new Point2D<Double, Double>(0.0, 0.0));
        assertEquals(0, tileManager.getMeatBoy().getX());
        assertEquals(0, tileManager.getMeatBoy().getY());
    }

    /**
     * Tests the {@link TileManagerImpl#setBandageGirlCoord()} method.
     */
    @Test
    void testSetBandageGirlCoord() {
        tileManager.setBandageGirlCoord(new Point2D<Double, Double>(0.0, 0.0));
        assertEquals(0, tileManager.getBandageGirl().getX());
        assertEquals(0, tileManager.getBandageGirl().getY());
    }

}
