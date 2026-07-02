package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import controller.levels.Levels;
import controller.loader.FileController;
import controller.loader.FileControllerImpl;
import model.Model;
import model.World;

/**
 * Test class for the file controller.
 */
public class TestFileController {

    private static final int PLAYER_POSX = 14;
    private static final int POSY = 175;
    private static final int ENEMY_POSX = 536;
    private static final int INITIAL_LIFE = 5;

    /**
     * Test the initial configuration read by the file controller.
     */
    @Test
    public void testFileControllerConfiguration() {
        final Model world = new World();
        final FileController file = new FileControllerImpl(world);
        file.loadLevel(Levels.LEVEL_1);
        assertEquals(world.getPlayer().getPosition().getFirst().intValue(), PLAYER_POSX);
        assertEquals(world.getPlayer().getPosition().getSecond().intValue(), POSY);
        assertEquals(world.getEnemy().getPosition().getFirst().intValue(), ENEMY_POSX);
        assertEquals(world.getEnemy().getPosition().getSecond().intValue(), POSY);
        assertEquals(world.getPlayer().getLifes(), INITIAL_LIFE);
        assertEquals(world.getEnemy().getLifes(), INITIAL_LIFE);
        file.loadLevel(Levels.LEVEL_2);
        assertEquals(world.getPlayer().getPosition().getFirst().intValue(), PLAYER_POSX);
        assertEquals(world.getPlayer().getPosition().getSecond().intValue(), POSY);
        assertEquals(world.getEnemy().getPosition().getFirst().intValue(), ENEMY_POSX);
        assertEquals(world.getEnemy().getPosition().getSecond().intValue(), POSY);
        assertEquals(world.getPlayer().getLifes(), 4);
        assertEquals(world.getEnemy().getLifes(), INITIAL_LIFE);
        file.loadLevel(Levels.LEVEL_3);
        assertEquals(world.getPlayer().getPosition().getFirst().intValue(), PLAYER_POSX);
        assertEquals(world.getPlayer().getPosition().getSecond().intValue(), POSY);
        assertEquals(world.getEnemy().getPosition().getFirst().intValue(), ENEMY_POSX);
        assertEquals(world.getEnemy().getPosition().getSecond().intValue(), POSY);
        assertEquals(world.getPlayer().getLifes(), 3);
        assertEquals(world.getEnemy().getLifes(), INITIAL_LIFE);
        file.loadLevel(Levels.LEVEL_4);
        assertEquals(world.getPlayer().getPosition().getFirst().intValue(), PLAYER_POSX);
        assertEquals(world.getPlayer().getPosition().getSecond().intValue(), POSY);
        assertEquals(world.getEnemy().getPosition().getFirst().intValue(), ENEMY_POSX);
        assertEquals(world.getEnemy().getPosition().getSecond().intValue(), POSY);
        assertEquals(world.getPlayer().getLifes(), 2);
        assertEquals(world.getEnemy().getLifes(), INITIAL_LIFE);
    }

}
