package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.BulletModel;
import it.unibo.model.Point2D;

class BulletModelTest {
    private BulletModel bulletModel;

    @BeforeEach
    void setUp() {
        bulletModel = new BulletModel();
    }

    @Test
    void testNotActive() {
        assertFalse(bulletModel.isActive());
        assertFalse(bulletModel.updatePosition());
    }

    @Test
    void testShoot() {
        Point2D source = new Point2D(0, 0);
        Point2D target = new Point2D(1, 1);
        bulletModel.shootBullet(source, target);
        assertTrue(bulletModel.isActive());
        assertFalse(bulletModel.targetReached());
        for (int i = 0; i < 15; i++) {
            assertFalse(bulletModel.targetReached());
            assertTrue(bulletModel.updatePosition());
        }
        var position = bulletModel.getCurrentPosition();
        assertEquals(position.x(), target.x());
        assertEquals(position.y(), target.y());
        assertFalse(bulletModel.updatePosition());
        assertTrue(bulletModel.targetReached());
        assertFalse(bulletModel.isActive());
    }
}
