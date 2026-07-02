package it.unibo;

import it.unibo.controller.LevelManager;
import it.unibo.controller.PuyoDropper;
import it.unibo.model.Grid;
import it.unibo.model.Puyo;
import it.unibo.model.interfaces.PuyoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the PuyoDropper class, including spawning,
 * dropping, and initializing Puyos within a Grid. It also checks the behavior of the
 * drop mechanism and the timing logic based on game ticks.
 */
class PuyoDropperTest {
    private Grid grid;
    private PuyoDropper puyoDropper;
    private LevelManager.LevelConfig levelConfig;

    /**
     * Sets up the testing environment by initializing a Grid and a PuyoDropper
     * instance with a specific LevelConfig.
     */
    @BeforeEach
    void setUp() {
        grid = new Grid(6, 6);
        levelConfig = new LevelManager.LevelConfig(5, 3);
        puyoDropper = new PuyoDropper(grid, levelConfig);
    }

    /**
     * Tests that a Puyo is spawned and dropped in the first row of the grid.
     * Verifies if a Puyo is placed correctly.
     */
    @Test
    void testSpawnAndDropPuyo() {
        puyoDropper.spawnAndDropPuyo();
        boolean puyoFound = false;

        for (int x = 0; x < grid.getCols(); x++) {
            PuyoInterface puyo = grid.getPuyo(x, 0);
            if (puyo != null) {
                puyoFound = true;
                break;
            }
        }

        assertTrue(puyoFound, "A Puyo should be present in the first row.");
    }

    /**
     * Tests the random filling of the grid with a specified number of Puyos.
     * Verifies that the correct number of Puyos are placed on the grid.
     */
    @Test
    void testFillGridRandomly() {
        puyoDropper.fillGridRandomly(5);

        int puyoCount = 0;
        for (int y = 0; y < grid.getRows(); y++) {
            for (int x = 0; x < grid.getCols(); x++) {
                if (grid.getPuyo(x, y) != null) {
                    puyoCount++;
                }
            }
        }

        assertEquals(5, puyoCount, "The grid should contain 5 Puyos after initialization.");
    }

    /**
     * Tests the dropping behavior of Puyos within the grid.
     * Verifies that a Puyo correctly falls one row down if the space below is empty.
     */
    @Test
    void testDropPuyo() {
        Puyo puyo = new Puyo("red", 0, 0);
        grid.addPuyo(puyo, 0, 0);

        puyoDropper.dropPuyo();

        PuyoInterface movedPuyo = grid.getPuyo(0, 1);
        assertNotNull(movedPuyo, "The Puyo should be moved down by one position.");
    }

    /**
     * Tests the behavior of the game ticks and verifies that the spawn and drop
     * mechanism works as expected after a specific number of ticks.
     */
    @Test
    void testOnTick() {
        for (int i = 0; i < 15; i++) {
            puyoDropper.onTick();
        }

        boolean puyoFound = false;
        for (int x = 0; x < grid.getCols(); x++) {
            PuyoInterface puyo = grid.getPuyo(x, 0);
            if (puyo != null) {
                puyoFound = true;
                break;
            }
        }

        assertTrue(puyoFound, "A Puyo should have been spawned after 15 ticks.");
    }

    /**
     * Tests the initialization of the grid by verifying that the last two rows
     * are filled with Puyos of random colors.
     */
    @Test
    void testInitialize() {
        puyoDropper.initialize();

        boolean allRowsFilled = true;
        for (int y = grid.getRows() - 2; y < grid.getRows(); y++) {
            for (int x = 0; x < grid.getCols(); x++) {
                if (grid.getPuyo(x, y) == null) {
                    allRowsFilled = false;
                    break;
                }
            }
        }

        assertTrue(allRowsFilled, "The last two rows should be filled with Puyos.");
    }
}
