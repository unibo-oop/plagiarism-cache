package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import it.unibo.commons.Constants;
import it.unibo.model.collision.CollisionHandler;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.tiles.Tile;
import it.unibo.commons.Point2D;
import it.unibo.controller.GameControllerImpl;

/**
 * JUnit tests for the GameModel class.
 */
class TestGameModel {

    private static final int MAP_ROWS = 30;
    private static final int MAP_COLUMNS = 36;
    private GameModel gameModel;

    /**
     * Initializes the test environment before each test method is executed.
     */
    @BeforeEach
    void init() {
        this.gameModel = new GameModelImpl(Constants.SOURCE_MAP, new GameControllerImpl());
        assertNotNull(this.gameModel);
    }

    /**
     * Tests the {@link GameModel#getStationary()} method.
     */
    @Test
    void testGetStationary() {
        final List<List<Optional<Tile>>> stationary = gameModel.getStationary();
        assertNotNull(stationary);
        assertFalse(stationary.isEmpty());
    }

    /**
     * Tests the {@link GameModel#getSaws()} method.
     */
    @Test
    void testGetSaws() {
        final List<CircularSaw> saws = gameModel.getSaws();
        assertNotNull(saws);
        assertFalse(saws.isEmpty());
    }

    /**
     * Tests the {@link GameModel#getPlatforms()} method.
     */
    @Test
    void testGetPlatforms() {
        final List<Platform> platforms = gameModel.getPlatforms();
        assertNotNull(platforms);
        assertFalse(platforms.isEmpty());
    }

    /**
     * Tests the {@link GameModel#getBandageGirl()} method.
     */
    @Test
    void testGetBandageGirl() {
        final BandageGirl bandageGirl = gameModel.getBandageGirl();
        assertNotNull(bandageGirl);
    }

    /**
     * Tests the {@link GameModel#getMeatBoy()} method.
     */
    @Test
    void testGetMeatBoy() {
        final MeatBoy meatBoy = gameModel.getMeatBoy();
        assertNotNull(meatBoy);
    }

    /**
     * Tests the {@link GameModel#getMeatBoyStartCoord()} method.
     */
    @Test
    void testGetMeatBoyStartCoord() {
        final Point2D<Double, Double> startCoord = gameModel.getMeatBoyStartCoord();
        assertNotNull(startCoord);
    }

    /**
     * Tests the {@link GameModel#getNumRows()} method.
     */
    @Test
    void testGetNumRows() {
        final int numRows = gameModel.getNumRows();
        assertEquals(MAP_ROWS, numRows);
    }

    /**
     * Tests the {@link GameModel#getNumCols()} method.
     */
    @Test
    void testGetNumCols() {
        final int numCols = gameModel.getNumCols();
        assertEquals(MAP_COLUMNS, numCols);
    }

    /**
     * Tests the {@link GameModel#getCollisionHandler()} method.
     */
    @Test
    void testGetCollisionHandler() {
        final CollisionHandler collisionHandler = gameModel.getCollisionHandler();
        assertNotNull(collisionHandler);
    }

}
