package zombietsunami;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import zombietsunami.model.MapData;
import zombietsunami.model.mapmodel.api.GameMap;
import zombietsunami.model.mapmodel.impl.GameMapImpl;
import zombietsunami.model.obstaclemodel.impl.BombImpl;
import zombietsunami.model.obstaclemodel.api.ObstacleManager;
import zombietsunami.model.obstaclemodel.impl.BreakableImpl;
import zombietsunami.model.obstaclemodel.impl.ObstacleManagerImpl;
import zombietsunami.model.zombiemodel.api.Zombie;
import zombietsunami.model.zombiemodel.impl.ZombieImpl;

/**
 * Class which tests ObstacleManager's functions.
 */
class TestObstacle {

    private final ObstacleManager obstacleManager = new ObstacleManagerImpl();
    private final GameMap gamemap = new GameMapImpl();
    private final Zombie zombie = new ZombieImpl();

    /**
     * Assures that the lists are correctly filled.
     */
    @Test
    void checkIfListsAreFilled() {
        obstacleManager.fillBombListFromMap(gamemap.getLoadedObstacleList(),
                gamemap.getScreenTilePos(MapData.getMaxWorldRow(), MapData.getMaxWorldCol(),
                        MapData.getTitSize(),
                        zombie.getX(), zombie.getY(), this.zombie.getScreenX(), this.zombie.getScreenY()),
                zombie.getStrength());

        obstacleManager.fillBreakableListFromMap(gamemap.getLoadedObstacleList(),
                gamemap.getScreenTilePos(MapData.getMaxWorldRow(), MapData.getMaxWorldCol(),
                        MapData.getTitSize(),
                        zombie.getX(), zombie.getY(), this.zombie.getScreenX(), this.zombie.getScreenY()),
                zombie.getStrength());

        assertNotEquals(obstacleManager.getBreakableList().size(), 0);
        assertNotEquals(obstacleManager.getBombList().size(), 0);
    }

    /**
     * Checks if the bomb and breakable are correctly
     * added into the list.
     */
    @Test
    void testAddObstacle() {

        final int bombListSize = obstacleManager.getBombList().size();
        final int breakableListSize = obstacleManager.getBreakableList().size();

        obstacleManager.addBomb(new BombImpl());
        obstacleManager.addBreakable(new BreakableImpl(3));

        assertNotEquals(bombListSize, obstacleManager.getBombList());
        assertNotEquals(breakableListSize, obstacleManager.getBreakableList());
    }

    /**
     * Checks if the bomb and breakable are correctly
     * removed out of the list.
     */
    @Test
    void testRemoveObstacle() {
        obstacleManager.addBomb(new BombImpl());
        obstacleManager.addBreakable(new BreakableImpl(3));

        obstacleManager.removeBombFromList(0);
        obstacleManager.removeBreakableFromList(0);

        assertEquals(obstacleManager.getBombList().get(0), null);
        assertEquals(obstacleManager.getBreakableList().get(0), null);
    }

    /**
     * Checks if getBombList() and getBreakableList()
     * are correctly returned.
     */
    @Test
    void testGetObstacleList() {
        assertNotEquals(obstacleManager.getBombList(), null);
        assertNotEquals(obstacleManager.getBreakableList(), null);
    }
}
