package vg.model;

import org.junit.jupiter.api.Test;
import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

import static org.junit.jupiter.api.Assertions.*;

class DynamicEntityTest {

    private class DynamicEntityImpl extends DynamicEntity{

        public DynamicEntityImpl(V2D position, V2D speed, int radius, Shape shape, MassTier massTier) {
            super(position, speed, radius, shape, massTier);
        }
    }
    DynamicEntity de1 = new DynamicEntityImpl(new V2D(0,0), new V2D(1,0), 2 ,Shape.CIRCLE, MassTier.MEDIUM);

    DynamicEntity de2 = new DynamicEntityImpl(new V2D(6,0), new V2D(-1,4), 2 ,Shape.SQUARE, MassTier.MEDIUM);
    @Test
    void move() {
        assertTrue(de1.getPosition().equals(new V2D(0,0)) && de1.getSpeed().equals(new V2D(1,0)));
        de1.move();
        assertTrue(de1.getPosition().equals(new V2D(1,0)));
        de1.setSpeed(new V2D(5,5));
        assertTrue(de1.getSpeed().equals(new V2D(5,5)));
        de1.move();
        assertTrue(de1.getPosition().equals(new V2D(6,5)));
    }

    @Test
    void getSpeed() {
        assertTrue(de2.getSpeed().equals(new V2D(-1,4)));
    }

    @Test
    void afterCollisionAction() {
        assertTrue(de2.getPosition().equals(new V2D(6,0)));
        assertTrue(de2.getSpeed().equals(new V2D(-1,4)));

    }
}