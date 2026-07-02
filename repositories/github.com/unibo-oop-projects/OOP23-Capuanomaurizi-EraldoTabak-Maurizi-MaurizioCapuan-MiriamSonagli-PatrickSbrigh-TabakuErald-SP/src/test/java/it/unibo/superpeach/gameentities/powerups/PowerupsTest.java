package it.unibo.superpeach.gameentities.powerups;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameentities.blocks.BlockType;
import it.unibo.superpeach.gameentities.blocks.BlocksHandler;
import it.unibo.superpeach.gameentities.blocks.MapFixedBlock;

/**
 * Powerup testing class.
 * 
 * @author Eraldo Tabaku
 */
class PowerupsTest {

    private static final int X1 = 10;
    private static final int Y1 = 10;
    private static final int X2 = 12;
    private static final int Y2 = 12;
    private static final int X3 = 16;
    private static final int Y3 = 16;
    private static final int X4 = 24;
    private static final int Y4 = 24;

    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;

    private static final int SCALE1 = 1;
    private static final int SCALE2 = 2;
    private static final int SCALE3 = 3;
    private static final int SCALE4 = 4;

    private static final BlocksHandler BLOCKSHANDLER = new BlocksHandler();

    private final Game game = new Game();

    @Test
    void powerupsTest() {

        game.init();

        // CREO UN POWERUP PER OGNI TIPO
        final PowerUp redMushroom = new RedMushroom(X1, Y1, WIDTH, HEIGHT, SCALE1, BLOCKSHANDLER);
        final PowerUp lifeMushroom = new LifeMushroom(X2, Y2, WIDTH, HEIGHT, SCALE2, BLOCKSHANDLER);
        final PowerUp star = new Star(X3, Y3, WIDTH, HEIGHT, SCALE3, BLOCKSHANDLER);
        final PowerUp coin = new RedMushroom(X4, Y4, WIDTH, HEIGHT, SCALE4, BLOCKSHANDLER);

        // VERIFICO SE I POWERUPS SONO STATI CREATI CORRETTAMENTE
        assertNotNull(redMushroom);
        assertNotNull(lifeMushroom);
        assertNotNull(star);
        assertNotNull(coin);

        // VERIFICO SE LE DIMENSIONI SONO GIUSTE
        assertEquals(X1, redMushroom.getX());
        assertEquals(Y1, redMushroom.getY());
        assertEquals(X2 * SCALE2, lifeMushroom.getX());
        assertEquals(Y2 * SCALE2, lifeMushroom.getY());
        assertEquals(X3 * SCALE3, star.getX());
        assertEquals(Y3 * SCALE3, star.getY());
        assertEquals(X4 * SCALE4, coin.getX());
        assertEquals(Y4 * SCALE4, coin.getY());

        assertEquals(WIDTH, redMushroom.getWidth());
        assertEquals(HEIGHT, redMushroom.getHeight());
        assertEquals(WIDTH * SCALE2, lifeMushroom.getWidth());
        assertEquals(HEIGHT * SCALE2, lifeMushroom.getHeight());
        assertEquals(WIDTH * SCALE3, star.getWidth());
        assertEquals(HEIGHT * SCALE3, star.getHeight());
        assertEquals(WIDTH * SCALE4, coin.getWidth());
        assertEquals(HEIGHT * SCALE4, coin.getHeight());

        assertEquals(SCALE1, redMushroom.getScale());
        assertEquals(SCALE2, lifeMushroom.getScale());
        assertEquals(SCALE3, star.getScale());
        assertEquals(SCALE4, coin.getScale());

        // VERIFICO SE IL CAMBIO DIREZIONE VIENE GESTITO CORRETTAMENTE
        assertFalse(redMushroom.isDirectionLeft());
        redMushroom.changeDirection();
        assertTrue(redMushroom.isDirectionLeft());
        assertFalse(lifeMushroom.isDirectionLeft());
        lifeMushroom.changeDirection();
        assertTrue(lifeMushroom.isDirectionLeft());
        assertFalse(star.isDirectionLeft());
        star.changeDirection();
        assertTrue(star.isDirectionLeft());

        // VERIFICO CHE IL POWERUP MUOIA CORRETTAMENTE
        assertTrue(redMushroom.isAlive());
        redMushroom.die();
        assertFalse(redMushroom.isAlive());
        assertTrue(lifeMushroom.isAlive());
        lifeMushroom.die();
        assertFalse(lifeMushroom.isAlive());
        assertTrue(star.isAlive());
        star.die();
        assertFalse(star.isAlive());

    }

    @Test
    void testCollisions() {

        new Game().init();

        final BlocksHandler bh1 = new BlocksHandler();
        bh1.addFixedBlock(new MapFixedBlock(X2, Y2, WIDTH, HEIGHT, SCALE1, BlockType.PIPE_LEFT));

        final PowerUp rm = new RedMushroom(X2, Y2, WIDTH, HEIGHT, SCALE1, bh1);

        assertEquals(X2, rm.getX());
        assertEquals(Y2, rm.getY());
        assertFalse(rm.isDirectionLeft());

        rm.collisions();

        assertEquals(X2 + WIDTH, rm.getX());
        assertEquals(Y2, rm.getY());

    }

    @Test
    void testEnemyDeathCollision() {

        new Game().init();

        final BlocksHandler bh = new BlocksHandler();
        bh.addFixedBlock(new MapFixedBlock(X1 + 8, Y1 + 8, WIDTH, HEIGHT, SCALE1, BlockType.DEATH_BLOCK));

        final PowerUp star = new Star(X1, Y1, WIDTH, HEIGHT, SCALE1, bh);

        assertTrue(star.isAlive());
        star.collisions();
        assertFalse(star.isAlive());

    }

}
