package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.util.Arrays;

import org.junit.Test;

import maingame.entity.mob.enemy.Enemy;
import maingame.level.Level;
import maingame.level.LevelImpl;
import maingame.level.tile.TileImpl;
import util.Vector2Impl;

/**
 * Classe per testare Level.
 */
public class TestLevel {

    /**
     * Testa la correttezza dei valori del level.
     */
    @Test
    public void testLevelStatus() {
        Level level;
        int[] tiles;
        int[] mobsItems;
        Dimension dimension;
        int n = 0;
        dimension = new Dimension(100, 100);
        tiles = new int[(int) (dimension.getWidth() * dimension.getHeight())];
        Arrays.fill(tiles, TileImpl.getTiles().get(0).getLevelColor());
        mobsItems = new int[(int) (dimension.getWidth() * dimension.getHeight())];
        Arrays.fill(mobsItems, LevelImpl.MOBS.get(1).getLevelColor());
        mobsItems[0] = LevelImpl.MOBS.get(0).getLevelColor();
        level = new LevelImpl(tiles, mobsItems, dimension, -1);
        assertEquals(level.getMobs().size(), (int) (dimension.getHeight() * dimension.getWidth()) - 1);
        assertEquals(level.getItems().size(), 0);
        for (final boolean b : level.getSolidTiles()) {
            if (b) {
                n++;
            }
        }
        assertEquals(n, 0);
        for (int y = 0; y < level.getDimension().getHeight(); y++) {
            for (int x = 0; x < level.getDimension().getWidth(); x++) {
                if (x == 0 && y == 0) {
                    continue;
                }
                assertEquals(level.getTile(new Vector2Impl<Integer>(x, y)), TileImpl.getTiles().get(0));
                assertTrue(level.getMobs().get(x + y * (int) level.getDimension().getWidth() - 1) instanceof Enemy);
            }
        }
        mobsItems = new int[(int) (dimension.getWidth() * dimension.getHeight())];
        Arrays.fill(mobsItems, LevelImpl.ITEMS.get(3).getLevelColor());
        mobsItems[0] = LevelImpl.MOBS.get(0).getLevelColor();
        level = new LevelImpl(tiles, mobsItems, dimension, -1);
        assertEquals(level.getItems().size(), (int) (dimension.getHeight() * dimension.getWidth()) - 1);
        assertEquals(level.getMobs().size(), 0);
        n = 0;
        for (final boolean b : level.getSolidTiles()) {
            if (b) {
                n++;
            }
        }
        assertEquals(n, (int) (dimension.getHeight() * dimension.getWidth()));
        Arrays.fill(mobsItems, 0);
        mobsItems[0] = LevelImpl.MOBS.get(0).getLevelColor();
        tiles[0] = TileImpl.TORCH.getLevelColor();
        level = new LevelImpl(tiles, mobsItems, dimension, -1);
        level.addTorchCoordinate(new Vector2Impl<Integer>(0, 0));
        final Enemy enemy1 = new Enemy(new Vector2Impl<Integer>(4, 0));
        final Enemy enemy2 = new Enemy(new Vector2Impl<Integer>(10, 0));
        level.add(enemy1);
        level.add(enemy2);
        assertTrue(level.getLightIntensity(enemy1) < level.getLightIntensity(enemy2));
    }
}
