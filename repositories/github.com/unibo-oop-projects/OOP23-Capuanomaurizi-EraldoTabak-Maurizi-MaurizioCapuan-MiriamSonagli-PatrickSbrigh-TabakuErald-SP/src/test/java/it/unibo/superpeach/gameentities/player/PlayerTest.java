package it.unibo.superpeach.gameentities.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.game.Scoreboard;
import it.unibo.superpeach.gameentities.blocks.BlocksHandler;
import it.unibo.superpeach.gameentities.enemies.EnemiesHandler;
import it.unibo.superpeach.gameentities.powerups.PowerupsHandler;

/**
 * player testing class.
 * 
 * @author Patrick Sbrighi
 */
final class PlayerTest {
    private static final int SPEED_X = 4;
    private static final int SPEED_Y = 52;
    private static final int FALL_SPEED = 3;

    private static final int X = 100;
    private static final int Y = 50;
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int SCALE = 2;

    private final BlocksHandler blocksHandler = new BlocksHandler();
    private final EnemiesHandler enemiesHandler = new EnemiesHandler();
    private final PowerupsHandler powerUpsHandler = new PowerupsHandler();
    private final Scoreboard scoreboard = new Scoreboard(3, 0, SCALE);
    private final Game game = new Game();
    private Player peach;

    @Test
    void testCreation() {
        // checking the initial parameter
        game.init();
        peach = new Peach(X, Y, WIDTH, HEIGHT, SCALE, blocksHandler, enemiesHandler, powerUpsHandler, scoreboard);
        assertNotNull(peach);
        assertEquals(X * SCALE, peach.getX());
        assertEquals(Y * SCALE, peach.getY());
        assertEquals(WIDTH * SCALE, peach.getWidth());
        assertEquals(HEIGHT * SCALE, peach.getHeight());
        assertEquals(null, peach.getPowerUp());
        assertEquals(SCALE, peach.getScale());
        assertEquals(0, peach.getScore());
        assertEquals(0, peach.getMoveY());
        assertFalse(peach.hasJumped());
        assertFalse(peach.hasWon());
        assertFalse(peach.hasLost());
    }

    @Test
    void testMovement() {
        game.init();
        peach = new Peach(X, Y, WIDTH, HEIGHT, SCALE, blocksHandler, enemiesHandler, powerUpsHandler, scoreboard);
        peach.moveLeft();
        assertEquals(peach.getMoveX(), -SPEED_X);
        peach.moveRight();
        assertEquals(peach.getMoveX(), SPEED_X);
        peach.jump();
        assertTrue(peach.hasJumped());
        assertEquals(peach.getMoveY(), -SPEED_Y);
        peach.fall();
        assertEquals(peach.getMoveY(), FALL_SPEED);
    }
}
