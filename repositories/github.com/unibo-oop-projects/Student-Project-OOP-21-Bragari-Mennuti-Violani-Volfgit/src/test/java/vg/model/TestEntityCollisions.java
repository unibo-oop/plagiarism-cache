package vg.model;

import org.junit.jupiter.api.Test;
import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEntityCollisions {
    private class DynamicEntityImpl extends DynamicEntity{

        public DynamicEntityImpl(V2D position, V2D speed, int radius, Shape shape, MassTier massTier) {
            super(position, speed, radius, shape, massTier);
        }
    }

    /**
     * position and speed of the point.
     */
    V2D pp = new V2D(0,0);
    V2D ps = new V2D(1,2);
    /**
     * position, speed and radius of the circle.
     *
     */
    V2D cp = new V2D(0,0);
    V2D cs = new V2D(0,0);
    int cr = 3;
    /**
     * position,speed and radius (half-width and half-height) of the square
     */
    V2D sp = new V2D(0,0);
    V2D ss = new V2D(0,0);
    int sr = 5;
    DynamicEntity p = new DynamicEntityImpl(pp,ps,0,Shape.CIRCLE, MassTier.HIGH);
    DynamicEntity c = new DynamicEntityImpl(cp,cs,cr,Shape.CIRCLE, MassTier.MEDIUM);
    DynamicEntity s = new DynamicEntityImpl(sp,ss,sr,Shape.SQUARE, MassTier.LOW);

    @Test
    void pointVPoint(){
        assertTrue(p.isInShape(p));
        assertTrue(p.isInShape(new V2D(0,0)));
    }
    @Test
    void pointVCircle(){
        assertTrue(p.isInShape(c));
        p.move();
        assertTrue(p.isInShape(p));
        p.move();
        assertFalse(p.isInShape(c));
    }
    @Test
    void circleVPoint(){
        assertTrue(c.isInShape(p));
        c.setSpeed(new V2D(3,5));
        c.move();
        assertFalse(c.isInShape(p));
        p.move();
        assertFalse(c.isInShape(p));
        p.move();
        assertTrue(c.isInShape(p));
    }
    @Test
    void squareVPoint(){
        assertTrue(s.isInShape(p));
        s.setSpeed(new V2D(7,9));
        s.move();
        p.setPosition(new V2D(0,0));
        // s.getPostion() == (6,7), speed = (1,2) so the next will be 7,9
        assertFalse(s.isInShape(p));
        p.move();
        assertFalse(s.isInShape(p));
        p.move();
        assertTrue(s.isInShape(p));
        p.move();
        assertTrue(s.isInShape(p));
    }
    @Test
    void pointVSquare(){
        s.setSpeed( new V2D(5,5));
        s.move();
        assertTrue(p.isInShape(s));
        p.move();
        assertTrue(p.isInShape(s));
        p.setSpeed(new V2D(-1,-1));
        p.move();
        assertTrue(p.isInShape(s));
        p.setSpeed(new V2D(0,-1));
        p.move();
        assertTrue(p.isInShape(s));
        p.move();
        assertFalse(p.isInShape(s));
        p.setSpeed(new V2D(1,1));
        p.move();
        assertTrue(p.isInShape(s));

    }
    @Test
    void circleVSquare(){
        assertTrue(c.isInShape(s));
        c.setSpeed(new V2D(-3,-3));
        c.move();
        assertTrue(c.isInShape(s));
        c.move();
        assertTrue(c.isInShape(s));
        c.move();
        assertFalse(c.isInShape(s));

    }
    @Test
    void squareVCircle(){
        var tc = new DynamicEntityImpl(sp,new V2D(3,3),2, Shape.CIRCLE, MassTier.HIGH);
        tc.move();
        s.setPosition(new V2D(0,0));
        assertTrue(s.isInShape(tc));
        tc.move();
        assertTrue(s.isInShape(tc));
        tc.move();
        assertFalse(s.isInShape(tc));
    }
    @Test
    void squareVSquare(){
        var ts = new DynamicEntityImpl(sp,new V2D(3,0),2,Shape.SQUARE,MassTier.LOW);
        ts.move();
        assertTrue(s.isInShape(ts));
        ts.move();
        assertTrue(s.isInShape(ts));
        ts.move();
        assertFalse(s.isInShape(ts));
    }
}
