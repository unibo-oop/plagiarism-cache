package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import it.unibo.controller.ControllerStorage;
import it.unibo.controller.LevelManager;
import it.unibo.model.ModelStorage;
import it.unibo.model.Point2D;
import it.unibo.model.Point2DI;
import it.unibo.model.Rectangle;
import it.unibo.model.Scale;

class UtilityTest {
    @Test
    void testScale() {
        int s = 10;
        Scale scale = new Scale(s);
        assertEquals(s, scale.getScale());
    }

    @Test
    void testDefaultScale() {
        Scale scale = new Scale();
        assertEquals(700, scale.getScale());
    }

    @Test
    void testPoint2D() {
        Point2D x = new Point2D(1, 2);
        Point2D y = new Point2D(4, 3);
        Point2D sum = Point2D.add(x, y);
        assertEquals(5, sum.x());
        assertEquals(5, sum.y());
        Point2D neg = Point2D.neg(x);
        assertEquals(-1, neg.x());
        assertEquals(-2, neg.y());
        Point2D diff = Point2D.sub(x, y);
        assertEquals(-3, diff.x());
        assertEquals(-1, diff.y());
        Point2D prod = Point2D.mul(x, 2);
        assertEquals(2, prod.x());
        assertEquals(4, prod.y());
        Point2D quot = Point2D.div(x, 2);
        assertEquals(0.5, quot.x());
        assertEquals(1, quot.y());
    }

    @Test
    void testPoint2DI() {
        Point2DI x = new Point2DI(1, 2);
        Point2DI y = new Point2DI(4, 3);
        Point2DI sum = Point2DI.add(x, y);
        assertEquals(5, sum.x());
        assertEquals(5, sum.y());
        Point2DI neg = Point2DI.neg(x);
        assertEquals(-1, neg.x());
        assertEquals(-2, neg.y());
        Point2DI diff = Point2DI.sub(x, y);
        assertEquals(-3, diff.x());
        assertEquals(-1, diff.y());
        Point2DI prod = Point2DI.mul(x, 2);
        assertEquals(2, prod.x());
        assertEquals(4, prod.y());
        Point2DI quot = Point2DI.div(x, 2);
        assertEquals(0, quot.x());
        assertEquals(1, quot.y());
        Point2D copy = Point2DI.toPoint2D(x);
        assertEquals(1, copy.x());
        assertEquals(2, copy.y());
        assertEquals(x.hashCode(), 10009);
        assertTrue(x.equals(x));
        assertFalse(x.equals(null));
        assertFalse(x.equals((Object) copy));
        assertTrue(x.equals(new Point2DI(x.x(), x.y())));
        assertFalse(x.equals(new Point2DI(x.x(), x.y() + 1)));
        assertFalse(x.equals(new Point2DI(x.x() + 1, x.y())));
    }

    @Test
    void testRectangle() {
        Point2DI upperLeft = new Point2DI(0, 0);
        Point2DI lowerRight = new Point2DI(10, 10);
        Rectangle r = new Rectangle(upperLeft, lowerRight);
        assertTrue(r.isInside(new Point2DI(5, 5)));
        assertFalse(r.isInside(new Point2DI(-1, -1)));
        assertFalse(r.isInside(new Point2DI(0, -1)));
        assertFalse(r.isInside(new Point2DI(-1, 0)));
        assertFalse(r.isInside(new Point2DI(0, 11)));
        assertFalse(r.isInside(new Point2DI(11, 0)));
    }

    @Test
    void testStorages() {
        Scale scale = new Scale();
        ModelStorage modelStorage = new ModelStorage(scale);
        assertEquals(scale.getScale(), modelStorage.scale.getScale());
        ControllerStorage controllerStorage = new ControllerStorage(
                modelStorage,
                mock(GameListener.class),
                mock(LevelManager.LevelConfig.class));
        controllerStorage.start();
    }
}
