package it.unibo.superpeach.gameentities.enemies;

import org.junit.jupiter.api.Test;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameentities.blocks.BlockType;
import it.unibo.superpeach.gameentities.blocks.BlocksHandler;
import it.unibo.superpeach.gameentities.blocks.MapFixedBlock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Enemy generation testing class.
 * 
 * @author Eraldo Tabaku
 */
final class EnemyTest {

    private static final int X1 = 12;
    private static final int Y1 = 13;
    private static final int X2 = 22;
    private static final int Y2 = 23;
    private static final int X3 = 32;
    private static final int Y3 = 33;
    private static final int WIDTH = 16;
    private static final int KOOPA_HEIGHT = 23;
    private static final int GOOMBA_HEIGHT = 16;
    private static final int SCALE1 = 1;
    private static final int SCALE2 = 2;

    private static final BlocksHandler BLOCKS_HANDLER = new BlocksHandler();

    @Test
    void testEnemy() {

        new Game().init();

        // Creating an enemy for each type
        final Enemy koopa = new KoopaTroopa(X1, Y1, WIDTH, KOOPA_HEIGHT, SCALE1, BLOCKS_HANDLER);
        final Enemy goomba = new Goomba(X2, Y2, WIDTH, GOOMBA_HEIGHT, SCALE2, BLOCKS_HANDLER);
        final Enemy fkoopa = new FlyingKoopa(X3, Y3, WIDTH, KOOPA_HEIGHT, SCALE2, BLOCKS_HANDLER);

        // Checking if the enemies got correctly created
        assertNotNull(koopa);
        assertNotNull(goomba);
        assertNotNull(fkoopa);

        // Checking the field values of the enemies that have been created
        assertEquals(X1, koopa.getX());
        assertEquals(Y1, koopa.getY());
        assertEquals(WIDTH, koopa.getWidth());
        assertEquals(KOOPA_HEIGHT, koopa.getHeight());
        assertEquals(SCALE1, koopa.getScale());
        assertEquals(BLOCKS_HANDLER, koopa.getBlocksHandler());

        // Checking if the scales operations on the coordinates are done correcly
        assertNotEquals(X2, goomba.getX());
        assertEquals(X2 * SCALE2, goomba.getX());
        assertNotEquals(X3, fkoopa.getX());
        assertEquals(X3 * SCALE2, fkoopa.getX());

        // Checking direction changes
        assertFalse(fkoopa.isDirectionLeft());
        fkoopa.changeDirection();
        assertTrue(fkoopa.isDirectionLeft());

        // Checking enemy "alive" status
        assertTrue(goomba.isAlive());
        goomba.die();
        assertFalse(goomba.isAlive());

    }

    @Test
    void testEnemyDeathCollision() {

        new Game().init();

        // Creating an enemy and a BlocksHandler containing a DEATH_BLOCK with the same
        // coords of the enemy
        // to check if enemies correctly die after collision with it
        final BlocksHandler bh = new BlocksHandler();
        bh.addFixedBlock(new MapFixedBlock(X1 + 8, Y1 + 8, WIDTH, GOOMBA_HEIGHT, SCALE1, BlockType.DEATH_BLOCK));

        final Enemy goomba = new Goomba(X1, Y1, WIDTH, GOOMBA_HEIGHT, SCALE1, bh);
        final Enemy koopa = new KoopaTroopa(X1, Y1, WIDTH, KOOPA_HEIGHT, SCALE1, bh);
        final Enemy fkoopa = new FlyingKoopa(X1, Y1, WIDTH, KOOPA_HEIGHT, SCALE1, bh);

        assertTrue(goomba.isAlive());
        goomba.collision();
        // Checking if the enemy is dead after the collision
        assertFalse(goomba.isAlive());

        assertTrue(koopa.isAlive());
        koopa.collision();
        // Checking if the enemy is dead after the collision
        assertFalse(koopa.isAlive());

        assertTrue(fkoopa.isAlive());
        fkoopa.collision();
        // Checking if the enemy is dead after the collision
        assertFalse(fkoopa.isAlive());

    }

    @Test
    void testCollisions() {

        new Game().init();

        final BlocksHandler bh1 = new BlocksHandler();
        bh1.addFixedBlock(new MapFixedBlock(X2, Y2, WIDTH, GOOMBA_HEIGHT, SCALE1, BlockType.BRICK));

        final Enemy goomba = new Goomba(X2, Y2, WIDTH, GOOMBA_HEIGHT, SCALE1, bh1);

        assertEquals(X2, goomba.getX());
        assertEquals(Y2, goomba.getY());
        assertFalse(goomba.isDirectionLeft());

        goomba.collision();

        assertEquals(X2 + WIDTH, goomba.getX());
        assertEquals(Y2, goomba.getY());

    }
}
