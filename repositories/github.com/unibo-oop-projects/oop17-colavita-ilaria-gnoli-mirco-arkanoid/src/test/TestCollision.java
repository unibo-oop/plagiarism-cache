package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entities.Ball;
import model.entities.BallBuilder;
import model.entities.Brick;
import model.entities.IEntity;
import utility.CollisionUtility;

/**
 * Classe di test, necessaria per il controllo delle collisioni, gestite con {@link CollisionUtility}.
 * @author Gnoli Mirco
 *
 */
class TestCollision {
    private IEntity e1, e2, e3, e4, e5, e6, e7;

    @BeforeEach
    public void createData() {
        e1 = new Brick(0, 2, 3, 2);
        e2 = new BallBuilder().position(5, 1).radius(1).build();
        e3 = new Brick(2, 1, 5, 2);
        e4 = new BallBuilder().position(5, 4).radius(2).build();
        e5 = new BallBuilder().position(4, 4).radius(1).build();
        e6 = new Brick(6, 0, 3, 2);
        e7 = new Brick(7, 2, 2, 4);
        
        
    }

    @Test
    void testIntersects() {
        //e1
        assertFalse(CollisionUtility.intersects(e1, e2));
        assertTrue(CollisionUtility.intersects(e1, e3));
        assertTrue(CollisionUtility.intersects(e1, e4));
        assertTrue(CollisionUtility.intersects(e1, e5));
        assertFalse(CollisionUtility.intersects(e1, e6));
        assertFalse(CollisionUtility.intersects(e1, e7));
        //e2
        assertTrue(CollisionUtility.intersects(e2, e3));
        assertTrue(CollisionUtility.intersects(e2, e4));
        assertFalse(CollisionUtility.intersects(e2, e5));
        assertTrue(CollisionUtility.intersects(e2, e6));
        assertTrue(CollisionUtility.intersects(e2, e6));
        assertFalse(CollisionUtility.intersects(e2, e7));
        //e3
        assertTrue(CollisionUtility.intersects(e3, e4));
        assertTrue(CollisionUtility.intersects(e3, e5));
        assertTrue(CollisionUtility.intersects(e3, e6)); //non collidono i lati, ma uno è interno all'altro
        assertTrue(CollisionUtility.intersects(e3, e6));
        assertTrue(CollisionUtility.intersects(e3, e7));
        //e4
        assertTrue(CollisionUtility.intersects(e4, e5));
        assertTrue(CollisionUtility.intersects(e4, e6));
        assertTrue(CollisionUtility.intersects(e4, e6));
        assertTrue(CollisionUtility.intersects(e4, e7));
        //e5
        assertFalse(CollisionUtility.intersects(e5, e6));
        assertFalse(CollisionUtility.intersects(e5, e6));
        assertFalse(CollisionUtility.intersects(e5, e7));
        //e6
        assertTrue(CollisionUtility.intersects(e6, e7));
    }

    @Test
    void testCollidedWithLowerBound() {
        assertFalse(CollisionUtility.firstCollidedWithLowerHorizontalBound(e2, e4));
        assertTrue(CollisionUtility.firstCollidedWithLowerHorizontalBound(e4, e2));

        assertFalse(CollisionUtility.firstCollidedWithLowerHorizontalBound(e3, e5));
        assertTrue(CollisionUtility.firstCollidedWithLowerHorizontalBound(e5, e3));
    }

    @Test
    void testCollidedWithUpperBound() {
        assertTrue(CollisionUtility.firstCollidedWithTopHorizontalBound(e2, e4));
        assertFalse(CollisionUtility.firstCollidedWithTopHorizontalBound(e4, e2));

        assertTrue(CollisionUtility.firstCollidedWithTopHorizontalBound(e3, e5));
        assertFalse(CollisionUtility.firstCollidedWithTopHorizontalBound(e5, e3));
    }

    @Test
    void testCollidedWithRightBound() {

        assertFalse(CollisionUtility.firstCollidedWithRightestVerticalBound(e1, e4));
        assertTrue(CollisionUtility.firstCollidedWithRightestVerticalBound(e4, e1));

        assertFalse(CollisionUtility.firstCollidedWithRightestVerticalBound(e2, e6));
        assertTrue(CollisionUtility.firstCollidedWithRightestVerticalBound(e6, e2));

        assertFalse(CollisionUtility.firstCollidedWithRightestVerticalBound(e1, e5));
        assertTrue(CollisionUtility.firstCollidedWithRightestVerticalBound(e5, e1));

        assertFalse(CollisionUtility.firstCollidedWithRightestVerticalBound(e4, e7));
        assertTrue(CollisionUtility.firstCollidedWithRightestVerticalBound(e7, e4));
    }

    @Test
    void testCollidedWithLeftBound() {
        assertTrue(CollisionUtility.firstCollidedWithLeftestVerticalBound(e1, e4));
        assertFalse(CollisionUtility.firstCollidedWithLeftestVerticalBound(e4, e1));

        assertTrue(CollisionUtility.firstCollidedWithLeftestVerticalBound(e2, e6));
        assertFalse(CollisionUtility.firstCollidedWithLeftestVerticalBound(e6, e2));

        assertTrue(CollisionUtility.firstCollidedWithLeftestVerticalBound(e1, e5));
        assertFalse(CollisionUtility.firstCollidedWithLeftestVerticalBound(e5, e1));

        assertTrue(CollisionUtility.firstCollidedWithLeftestVerticalBound(e4, e7));
        assertFalse(CollisionUtility.firstCollidedWithLeftestVerticalBound(e7, e4));
    }
}
