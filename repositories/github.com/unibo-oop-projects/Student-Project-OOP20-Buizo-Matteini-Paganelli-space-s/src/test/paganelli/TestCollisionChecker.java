package test.paganelli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.awt.geom.AffineTransform;
import org.junit.Test;
import spacesurvival.model.collision.CollisionChecker;
import spacesurvival.model.collision.bounding.CircleBoundingBox;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.common.P2d;
import spacesurvival.utilities.dimension.AffineUtils;

public class TestCollisionChecker {

    /**
     * Test RectBoundingBox to RectBoundingBox.
     */
    @Test 
    public void testRectToRect1() {

        final RectBoundingBox rbb1 = new RectBoundingBox(new P2d(0, 0), new P2d(10, 10), new AffineTransform());
        final RectBoundingBox rbb2 = new RectBoundingBox(new P2d(5, 5), new P2d(15, 15), new AffineTransform());

        final CollisionChecker checker = new CollisionChecker();

        assertTrue(checker.rectangleToRectangle(rbb1, rbb2));

    }

    /**
     * Test RectBoundingBox to RectBoundingBox.
     */
    @Test 
    public void testRectToRect2() {

        final int xTranslation = 11;

        final RectBoundingBox rbb1 = new RectBoundingBox(new P2d(0, 0), new P2d(10, 10), new AffineTransform());

        final AffineTransform cbbTransform = new AffineTransform();
        cbbTransform.translate(xTranslation, 0);
        final RectBoundingBox rbb2 = new RectBoundingBox(new P2d(11, 0), new P2d(20, 10), cbbTransform);
        final CollisionChecker checker = new CollisionChecker();

        assertFalse(checker.rectangleToRectangle(rbb1, rbb2));

    }

    /**
     * Test RectBoundingBox to rotated RectBoundingBox.
     */
    @Test 
    public void testRectToRotatedRect() {

        final int xTranslation = 13;
        final int angle = 45;
        final RectBoundingBox rbb1 = new RectBoundingBox(new P2d(0, 0), new P2d(10, 10), new AffineTransform());

        final AffineTransform cbbTransform = new AffineTransform();
        cbbTransform.translate(xTranslation, 0);
        cbbTransform.rotate(Math.toRadians(angle));
        final RectBoundingBox rbb2 = new RectBoundingBox(new P2d(xTranslation, 0), new P2d(20, 10), cbbTransform);
        rbb2.setTransform(cbbTransform);

        final CollisionChecker checker = new CollisionChecker();

        assertTrue(checker.rectangleToRectangle(rbb1, rbb2));
    }

    /**
     * Test RectBoundingBox to rotated CircleBoundingBox.
     */
    @Test 
    public void testRectToCircle1() {

        final int xTranslation = 5;
        final int yTranslation = 5;

        final RectBoundingBox rbb1 = new RectBoundingBox(new P2d(0, 0), new P2d(10, 10), new AffineTransform());
        final AffineTransform cbbTransform = new AffineTransform();
        cbbTransform.translate(xTranslation, yTranslation);

        final CircleBoundingBox cbb1 = new CircleBoundingBox(new P2d(xTranslation, yTranslation), 10, new AffineTransform());
        final CollisionChecker checker = new CollisionChecker();

        assertTrue(checker.rectangleToCircle(rbb1, cbb1));
    }

    /**
     * Test RectBoundingBox to rotated CircleBoundingBox.
     */
    @Test 
    public void testRectToCircle2() {
        final int angle = 45;
        final int xTranslation = 50;
        final int yTranslation = 50;

        final RectBoundingBox rbb1 = new RectBoundingBox(new P2d(0, 0), new P2d(10, 10), new AffineTransform());

        final AffineTransform cbbTransform = new AffineTransform();
        cbbTransform.translate(xTranslation, yTranslation);
        cbbTransform.rotate(Math.toRadians(angle));
        final CircleBoundingBox cbb2 = new CircleBoundingBox(new P2d(xTranslation, yTranslation), 10, cbbTransform);

        final CollisionChecker checker = new CollisionChecker();

        assertFalse(checker.rectangleToCircle(rbb1, cbb2));
    }


    /**
     * Test getRotationAngleInDegrees from an AffineTransform.
     */
    @Test 
    public void testGetAngleDegreesFromAffineTransform() {

        final int angle = 45;
        final AffineTransform cbbTransform = new AffineTransform();
        cbbTransform.rotate(Math.toRadians(angle));

        final Double degrees = AffineUtils.getRotationAngleInDegrees(cbbTransform);

        assertEquals(Double.valueOf(angle), degrees);
    }

    /**
     * Test getRotationAngleInRadiants from an AffineTransform.
     */
    @Test 
    public void testGetAngleRadiantFromAffineTransform() {
        final int angle = 5;
        final AffineTransform cbbTransform = new AffineTransform();
        cbbTransform.rotate(Math.toRadians(angle));

        final Double radiants = AffineUtils.getRotationAngleInDegrees(cbbTransform);

        assertEquals(Double.valueOf(angle), radiants);
    }

}
