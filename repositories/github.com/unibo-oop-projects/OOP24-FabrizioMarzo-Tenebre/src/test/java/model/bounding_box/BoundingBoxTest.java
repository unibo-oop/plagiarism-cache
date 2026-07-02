package model.bounding_box;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.lang3.tuple.Pair;

public class BoundingBoxTest {

    private RectBoundingBox box;

    @BeforeEach
    void setUp() {
        Pair<Double, Double> ul = Pair.of(0.0, 10.0);
        Pair<Double, Double> br = Pair.of(5.0, 0.0);
        box = new RectBoundingBox(ul, br);
    }

    @Test
    void testGetCorners() {
        assertEquals(Pair.of(0.0, 10.0), box.getULcorner());
        assertEquals(Pair.of(5.0, 0.0), box.getBRcorner());
    }

    @Test
    void testIsCollidingWhenOverlapping() {
        BoundingBox other = new RectBoundingBox(Pair.of(3.0, 8.0), Pair.of(8.0, 2.0));
        assertTrue(box.isColliding(other));
        assertTrue(other.isColliding(box));
    }

    @Test
    void testIsNotCollidingWhenApart() {
        BoundingBox other = new RectBoundingBox(Pair.of(6.0, 10.0), Pair.of(10.0, 0.0));
        assertFalse(box.isColliding(other));
    }

    @Test
    void testIsNotCollidingWhenTouchingEdges() {
        BoundingBox other = new RectBoundingBox(Pair.of(5.0, 10.0), Pair.of(10.0, 0.0)); 
        assertFalse(box.isColliding(other));
    }

    @Test
    void testUpdateBBox() {
        Pair<Double, Double> newPos = Pair.of(10.0, 20.0);
        box.updateBBox(newPos);

        Pair<Double, Double> expectedUL = Pair.of(10.0, 30.0); 
        Pair<Double, Double> expectedBR = Pair.of(15.0, 20.0);

        assertEquals(expectedUL, box.getULcorner());
        assertEquals(expectedBR, box.getBRcorner());
    }
}
