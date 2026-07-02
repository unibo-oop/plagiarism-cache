package it.unibo.the100dayswar.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.model.cell.api.BonusCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.GameMapBuilder;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.soldier.api.Soldier;

class GameMapTest {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int BONUS_CELLS_COUNT = 3;
    private static final int OBSTACLE_COUNT = 5;
    private static final String PLAYER_NAME = "Player1";
    private GameMap gameMap;
    private Cell bonusCell;

    @BeforeEach
    void setUp() {
        final GameMapBuilder mapBuilder;
        mapBuilder = new GameMapBuilderImpl(WIDTH, HEIGHT);
        mapBuilder.initializeBuildableCells()
                  .addSpawnCells()
                  .addBonusCell(BONUS_CELLS_COUNT)
                  .addObstacles(OBSTACLE_COUNT);

        gameMap = mapBuilder.build();

        bonusCell = gameMap.getAllCells()
                .filter(cell -> cell instanceof BonusCell)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No bonus cells found in the map."));
    }

    @Test
    void testGameMapInitialization() {
        assertNotNull(gameMap, "The game map should be initialized.");
        assertEquals(WIDTH, gameMap.getSize().getWidth(), "The map width should match.");
        assertEquals(HEIGHT, gameMap.getSize().getHeight(), "The map height should match.");

        final boolean hasSpawn = gameMap.getAllCells()
                .anyMatch(Cell::isSpawn);
        final  long numberOfSpawn = gameMap.getAllCells()
                .filter(Cell::isSpawn)
                .count();

        assertTrue(hasSpawn, "The map should contain at least one spawn cell.");
        assertTrue(numberOfSpawn == 2, "The map should contain exactly two spawn cells.");
    }

    @Test
    void testBonusCellActivation() {
        final Cell baseCell = bonusCell;
        final HumanPlayer player1 = new HumanPlayerImpl(PLAYER_NAME, baseCell);
        final Soldier soldier = new SoldierImpl(player1);

        gameMap.setOccupationOnCell(baseCell, Optional.of(soldier));
        ((BonusCell) bonusCell).notify(player1);

        assertFalse(((BonusCell) bonusCell).isBonusActive(), "The bonus cell should be inactive after activation.");
    }

}
