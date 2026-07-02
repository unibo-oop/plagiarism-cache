package it.unibo.the100dayswar.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.map.api.GameMapBuilder;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.soldier.api.Soldier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class MapMangerTest {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final String PLAYER_NAME = "Player1";
    private MapManager mapManager;
    private Cell spawnCell;
    private Cell targetCell;
    private Cell playerSpawnCell;

    @BeforeEach
    void setUp() {
        final GameMapBuilder mapBuilder;
        mapBuilder = new GameMapBuilderImpl(WIDTH, HEIGHT);

        mapManager = new MapManagerImpl(mapBuilder);

        spawnCell = mapManager.getMapAsAStream()
                .filter(Cell::isSpawn)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No spawn cells found in the map."));
        playerSpawnCell = mapManager.getMapAsAStream()
                .filter(Cell::isSpawn)
                .filter(c -> !c.equals(spawnCell))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No spawn cells found in the map."));
        targetCell = mapManager.getMapAsAStream()
                .filter(cell -> cell.isAdiacent(spawnCell) && cell.isFree())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No non-spawn cells found in the map adiacent at the start cell."));
    }

    @Test
    void testMapManagerInitialization() {
        assertNotNull(mapManager, "The map manager should be initialized.");
        assertNotNull(mapManager.getPlayersCells(), "The map manager should manage players' cells.");
        assertTrue(mapManager.getPlayersCells().isEmpty(), "No players should own cells initially.");
        assertTrue(mapManager.getMapAsAStream().filter(Cell :: isSpawn).count() == 2, "There should be two spawn cells.");
    }

    @Test
    void testPlayerOwnership() {
        final HumanPlayer player1  = new HumanPlayerImpl(PLAYER_NAME, spawnCell);
        final Soldier soldier1 = new SoldierImpl(player1);

        mapManager.update(new Pair<>(soldier1, spawnCell));

        final Set<Cell> playerCells = mapManager.getPlayersCells().get(player1);
        assertTrue(spawnCell.getUnit().isPresent(), "The spawn cell should contain the soldier.");
        assertTrue(spawnCell.isSpawn(), "The spawn cell should be a spawn cell.");
        assertNotNull(playerCells, "The player should own some cells.");
        assertTrue(playerCells.contains(spawnCell), "The player should own their spawn cell.");
    }

    @Test
    void testSoldierMovement() {
        final HumanPlayer player1 = new HumanPlayerImpl(PLAYER_NAME, spawnCell);
        final Soldier soldier1 = new SoldierImpl(player1);

        mapManager.update(new Pair<>(soldier1, spawnCell));

        assertTrue(spawnCell.getUnit().isPresent(), "The start cell should contain the soldier.");
        assertFalse(targetCell.getUnit().isPresent(), "The target cell should not contain the soldier.");

        mapManager.update(new Pair<>(soldier1, targetCell));

        assertTrue(targetCell.getUnit().isPresent(), "The target cell should now contain the soldier.");
        assertFalse(spawnCell.getUnit().isPresent(), "The start cell should no longer contain the soldier.");
    }

    @Test
    void testSpawnCellOccupiedByNewSoldier() {
        final HumanPlayer player1 = new HumanPlayerImpl(PLAYER_NAME, spawnCell);
        final Soldier soldier1 = new SoldierImpl(player1);
        final Soldier soldier2 = new SoldierImpl(player1);
        mapManager.update(new Pair<>(soldier1, spawnCell));

        assertTrue(spawnCell.isSpawn(), "The spawn cell should is a spawnCell.");
        assertTrue(spawnCell.getUnit().isPresent(), "The spawn cell should contain the first soldier.");

        final Exception exception = assertThrows(IllegalStateException.class, () -> {
            mapManager.update(new Pair<>(soldier2, spawnCell));
        });

        assertEquals("Target cell is not free for soldier movement.", exception.getMessage());
        assertTrue(spawnCell.getUnit().isPresent(), "The spawn cell should still contain the first soldier.");
    }

    @Test
    void testObstaclePlacement() {
        final long obstacleCount = mapManager.getMapAsAStream()
                .filter(cell -> !cell.isBuildable())
                .count();

        assertTrue(obstacleCount > 0, "There should be obstacles placed in the map.");
    }

    @Test
    void testGetSpawnsCells() {
        assertEquals(spawnCell, mapManager.getBotSpawn(), "The player spawn cell should match.");
        assertEquals(playerSpawnCell, mapManager.getPlayerSpawn(), "The bot spawn cell should match.");
    }
}
