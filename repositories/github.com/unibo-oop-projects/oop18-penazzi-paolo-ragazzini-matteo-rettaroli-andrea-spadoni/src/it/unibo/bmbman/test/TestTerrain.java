package it.unibo.bmbman.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import it.unibo.bmbman.model.LevelImpl;
import it.unibo.bmbman.model.Terrain;
import it.unibo.bmbman.model.TerrainFactory;
import it.unibo.bmbman.model.TerrainFactoryImpl;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
/**
 * Used to test terrain.
 *
 */
public class TestTerrain {
    private final TerrainFactory terrainFactory = new TerrainFactoryImpl();
    private LevelImpl level = new LevelImpl();
    private Terrain terrain;
    /**
     * Attests that there is a correct number of blocks.
     */
    @Test
    public void testNumberBlock() {
        IntStream.iterate(0, i -> i + 1).limit(LevelImpl.LEVEL_MAX).forEach(k -> {
            terrain = terrainFactory.create(level.getBlocksNumber());
            assertTrue(terrain.getBlocks().size() <= level.getBlocksNumber());
            level.levelUp();
        });
    }
    /**
     * Attests that the player have two free position for each level. 
     */
    @Test
    public void testAroundPlayerPositions() {
        IntStream.iterate(0, i -> i + 1).limit(LevelImpl.LEVEL_MAX).forEach(k -> {
            terrain = terrainFactory.create(level.getBlocksNumber());
            final List<Position> safePosition = new ArrayList<>();
            safePosition.add(TerrainFactoryImpl.PLAYER_POSITION);
            safePosition.add(new Position(TerrainFactoryImpl.PLAYER_POSITION.getX() + TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE,
                    TerrainFactoryImpl.PLAYER_POSITION.getY()));
            safePosition.add(new Position(TerrainFactoryImpl.PLAYER_POSITION.getX(), 
                    TerrainFactoryImpl.PLAYER_POSITION.getY() + TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE));
            /*use assertFalse because they have already been removed*/
            assertFalse(terrain.getFreeTiles().stream().map(i -> i.getPosition()).collect(Collectors.toList()).containsAll(safePosition));
            level.levelUp();
        });
    }
    /**
     * Attests that the player have two block around that prevent monster attacks.
     */
    @Test
    public void testSafeBlocks() {
        IntStream.iterate(0, i -> i + 1).limit(LevelImpl.LEVEL_MAX).forEach(k -> {
            terrain = terrainFactory.create(level.getBlocksNumber());
            /* use 0 and 1 position because the margin blocks are inserted initially*/
            assertEquals(new Position(3 * TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE, 
                    TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE), terrain.getBlocks().get(0).getPosition());
            assertEquals(new Position(TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE,
                    3 * TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE), terrain.getBlocks().get(1).getPosition());
            level.levelUp();
        });
    }
    /**
     * Attests that the door have a correct position.
     */
    @Test
    public void testDoorPosition() {
        IntStream.iterate(0, i -> i + 1).limit(LevelImpl.LEVEL_MAX).forEach(k -> {
            terrain = terrainFactory.create(level.getBlocksNumber());
            assertEquals(new Position((TerrainFactoryImpl.TERRAIN_COLUMNS - 2) * TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE,
                    (TerrainFactoryImpl.TERRAIN_ROWS - 2) * TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE),
                    TerrainFactoryImpl.DOOR_POSITION);
            level.levelUp(); 
        });
    }
    /**
     * Attests that the dimension of cells is correct.
     */
    @Test
    public void testTerrainDimension() {
        IntStream.iterate(0, i -> i + 1).limit(LevelImpl.LEVEL_MAX).forEach(k -> {
            terrain = terrainFactory.create(level.getBlocksNumber());
            assertEquals(TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE,
                    TerrainFactoryImpl.TERRAIN_HEGHT / TerrainFactoryImpl.TERRAIN_ROWS);
            assertEquals(TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE,
                    TerrainFactoryImpl.TERRAIN_WIDTH / TerrainFactoryImpl.TERRAIN_COLUMNS);
            level.levelUp(); 
        });
    }
}
