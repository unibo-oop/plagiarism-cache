import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;

import it.unibo.tetris.PlayManager;
import it.unibo.tetris.mino.Mino_Impl;
import it.unibo.tetris.mino.api.Block;

/**
 * JUnitTest for the {@link Mino_Impl} class.
 */
public class Mino_Impl_Test {
     private Mino_Impl mino;

    /**
     * BeforeEach test create a new {@link Mino}.
     */
    @BeforeEach
    public void setUp() {
        mino = new Mino_Impl();
        /*
         * Color is not change the result of the tests.
         */
        mino.create(Color.RED);
    }

    /**
     * Test rotations when {@link Mino} collide.
     */
    @Test
    public void testRotationCollisionWithCollision() {
        /*
         * Move the mino to the left so that it collides with the left boundary.
         */
        mino.tempB[0].x = PlayManager.left_x - Block.SIZE;
        mino.tempB[1].x = PlayManager.left_x - Block.SIZE;
        mino.tempB[2].x = PlayManager.left_x - Block.SIZE;
        mino.tempB[3].x = PlayManager.left_x - Block.SIZE;

        /*
         * Perform rotation collision check.
         */
        mino.checkRotationCollision();

        /*
         * Verify that left collision is detected correctly.
         */
        assertTrue(mino.isLeftCollision());
    }

    /**
     * Test rotations when {@link Mino} don't collide.
     */
    @Test
    public void testRotationCollisionNoCollision() {
        /*
         * Move the mino to the left so that it collides with the left boundary.
         */
        mino.tempB[0].x = PlayManager.left_x +  2*Block.SIZE;
        mino.tempB[1].x = PlayManager.left_x +  2*Block.SIZE;
        mino.tempB[2].x = PlayManager.left_x +  2*Block.SIZE;
        mino.tempB[3].x = PlayManager.left_x +  2*Block.SIZE;

        /*
         * Perform rotation collision check.
         */
        mino.checkRotationCollision();

        /*
         * Verify that left collision is detected correctly.
         */
        assertFalse(mino.isLeftCollision());
    }    

    /**
     * Test movements when {@link Mino} collide.
     */
    @Test
    public void testMovementCollisionWithCollision() {
        /*
         * Move the mino to the left so that it collides with the left boundary.
         */
        mino.b[0].x = PlayManager.left_x;
        mino.b[1].x = PlayManager.left_x;
        mino.b[2].x = PlayManager.left_x;
        mino.b[3].x = PlayManager.left_x;

        /*
         * Perform movement collision check.
         */
        mino.checkMovementCollision();

        /*
         * Verify that left collision is detected correctly.
         */
        assertTrue(mino.isLeftCollision());
    }

    /**
     * Test movements when {@link Mino} don't collide.
     */
    @Test
    public void testMovementCollisionNoCollision() {
        /*
         * Move the mino 2 blocks right so it doesn't collide with the left boundary.
         */
        mino.b[0].x = PlayManager.left_x + 2*Block.SIZE;
        mino.b[1].x = PlayManager.left_x + 2*Block.SIZE;
        mino.b[2].x = PlayManager.left_x + 2*Block.SIZE;
        mino.b[3].x = PlayManager.left_x + 2*Block.SIZE;

        /*
         * Perform movement collision check.
         */
        mino.checkMovementCollision();

        /*
         * Verify that left collision is detected correctly.
         */
        assertFalse(mino.isLeftCollision());
    }
}
