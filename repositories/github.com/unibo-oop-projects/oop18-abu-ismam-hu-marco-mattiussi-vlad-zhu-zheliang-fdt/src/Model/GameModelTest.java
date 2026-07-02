package model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import model.enemy.EnemyType;
import model.tower.TowerType;
import model.wave.Wave;
import utilityclasses.Pair;
/**
 * Test class for the Game logics and functionality.
 */
public class GameModelTest {
    private static final int TICKS_TO_SPAWN_ENEMY = 10 + 1;
    private static final int TICKS_ENEMY_WALK = 10 + 1;
    private static final int WAVE_TO_WIN = 20 + 1;
    private final GameModel gm = new GameModelImpl(1);

    /**
     * test case : 
     * places a tower, 
     * checks if status is PLAYING
     * removes a tower
     * checks if it is removed correctly from the map.
     */
    @org.junit.Test
    public void placeAndRemoveTowersTest() {
        gm.placeTower(new Pair<>(1, 2), TowerType.BASIC);
        assertTrue(gm.getGameStatus() == GameStatus.PLAYING);
        assertTrue(gm.getMap().getEntityList().stream().count() == 1);
        gm.removeTower(new Pair<>(1, 2));
        assertTrue(gm.getMap().getEntityList().stream().count() == 0);
    }
    /**
     * Test case :
     * spawns 5 enemies on the map
     * checks if they appear on the map correctly.
     */
    @org.junit.Test
    public void spawnEnemiesTest() {
        Wave w = gm.getCurrentWave();
        assertEquals(true, w.hasEnemies());
        gm.setReadyToSpawn(true);
        for (int i = 0; i <= TICKS_ENEMY_WALK * 2; i++) {
            gm.update();
        }
        assertEquals(gm.getMap().getEntityList().stream().count(), 2);
    }

    /**
     * test case :
     * checks if the nextWave call functions properly.
     */
    @org.junit.Test
    public void goToNextWaveTest() {
        gm.nextWave();
        Wave w = gm.getCurrentWave();
        w.populate(1, EnemyType.TANK);
        assertEquals(w.getWave(), 2);
    }

    /**
     * Test case :
     * checks if the enemy walks on the map.
     */
    @org.junit.Test
        public void enemyWalkTest() {
                gm.nextWave();
                Wave w = gm.getCurrentWave(); 
                gm.setReadyToSpawn(true);
                assertEquals(w.getWave(), 2);
                for (int i = 0; i <= TICKS_TO_SPAWN_ENEMY * 2; i++) {
                    gm.update();
                }
                assertEquals(2, gm.getMap().getEntityList().stream().count());
        }

    /**
     * test case :
     * Checks the win condition of the game (wave>20).
     */
    @org.junit.Test
    public void gameWinTest() {
        gm.getPlayer().setWave(WAVE_TO_WIN);
        gm.update();
        assertTrue(gm.getGameStatus() == GameStatus.WON);
    }

    /**
     * test case :
     * checks the lose condition of the game (player hp <0).
     */
    @org.junit.Test
    public void gameLostTest() {
        gm.setReadyToSpawn(true);
        for (int i = 0; i <= TICKS_TO_SPAWN_ENEMY * 4; i++) {
            gm.update();
        }
        for (int i = 0; i <= TICKS_ENEMY_WALK * gm.getMap().getPathList().size() - 1; i++) {
            gm.update();
        }
        assertTrue(gm.getGameStatus() == GameStatus.LOST);
    }
}
