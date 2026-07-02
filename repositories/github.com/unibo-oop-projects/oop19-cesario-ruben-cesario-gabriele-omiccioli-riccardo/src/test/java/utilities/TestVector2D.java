package utilities;

import org.junit.jupiter.api.Test;
import utilities.math.Point2DImpl;
import utilities.math.Vector2DImpl;

public class TestVector2D {

    /**
     * Testing X and Y components.
     */
    @Test
    public void testComponents() {
        final Vector2DImpl vector = new Vector2DImpl(new Point2DImpl(1, 2));
        assert vector.components().getX() == 1 && vector.components().getY() == 2;
    }

    /**
     * Testing module.
     */
    @Test
    public void testModule() {
        final Vector2DImpl vector = new Vector2DImpl(new Point2DImpl(1, 1));
        assert vector.module() == Math.sqrt(2);
    }

    /**
     * Testing opposite.
     */
    @Test
    public void testOpposite() {
        final Vector2DImpl vector = new Vector2DImpl(new Point2DImpl(1, 2));
        assert vector.opposite().components().getX() == -1 && vector.opposite().components().getY() == -2;
    }

    /**
     * Testing add.
     */
    @Test
    public void testAdd() {
        final Vector2DImpl vectorA = new Vector2DImpl(new Point2DImpl(1, 2));
        final Vector2DImpl vectorB = new Vector2DImpl(new Point2DImpl(3, 4));
        assert vectorA.add(vectorB).components().getX() == 4 && vectorA.add(vectorB).components().getY() == 6;
    }

    /**
     * Testing sub.
     */
    @Test
    public void testSub() {
        final Vector2DImpl vectorA = new Vector2DImpl(new Point2DImpl(1, 2));
        final Vector2DImpl vectorB = new Vector2DImpl(new Point2DImpl(3, 4));
        assert vectorA.sub(vectorB).components().getX() == -2 && vectorA.sub(vectorB).components().getY() == -2;
    }

    /**
     * Testing multiplyByScalar.
     */
    @Test
    public void testMultiplyByScalar() {
        final Vector2DImpl vector = new Vector2DImpl(new Point2DImpl(1, 2));
        final Double scalar = (double) 2;
        assert vector.multiplyByScalar(scalar).components().getX() == 2 && vector.multiplyByScalar(scalar).components().getY() == 4;
    }

    /**
     * Testing multiply.
     */
    @Test
    public void testMultiply() {
        final Vector2DImpl vectorA = new Vector2DImpl(new Point2DImpl(1, 2));
        final Vector2DImpl vectorB = new Vector2DImpl(new Point2DImpl(3, 4));
        assert vectorA.multiply(vectorB) == 11;
    }

    /**
     * Testing distance.
     */
    @Test
    public void testDistance() {
        final Vector2DImpl vector = new Vector2DImpl();
        final Point2DImpl pointA = new Point2DImpl(1, 1);
        final Point2DImpl pointB = new Point2DImpl(2, 3);
        vector.distance(pointA, pointB);
        assert vector.components().getX() == 1 && vector.components().getY() == 2;
    }

    /**
     * Testing angle.
     */
    @Test
    public void testAngle() {
        final Vector2DImpl vectorA = new Vector2DImpl(new Point2DImpl(1, 1));
        final Vector2DImpl vectorB = new Vector2DImpl(new Point2DImpl(-1, 1));
        assert vectorA.angle(vectorB) == Math.PI / 2;
    }

    /**
     * Testing angleOfThisVector.
     */
    @Test
    public void testAngleOfThisVector() {
        final Vector2DImpl vector = new Vector2DImpl(new Point2DImpl(1, 1));
        assert vector.angleOfThisVector() == Math.PI / 4;
    }

}
