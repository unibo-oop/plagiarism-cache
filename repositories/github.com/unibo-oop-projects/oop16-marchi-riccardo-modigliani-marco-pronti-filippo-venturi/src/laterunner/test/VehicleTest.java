package laterunner.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import laterunner.model.vehicle.Car;
import laterunner.model.vehicle.Obstacle;
import laterunner.model.vehicle.Vehicles;
import laterunner.physics.P2d;
import laterunner.physics.S2d;

/**
 * Tests Vehicle's method.
 */
public class VehicleTest {

    private static final int X_POS = 435;
    private static final int Y_POS = 510;
    private static final int BUS_MAL = 3000;

    /**
     * Tests Vehicle's initial fields.
     */
    @Test
    public void testInitialFields() {
        Car car = new Car();
        P2d pos = new P2d(X_POS, Y_POS);
        assertTrue(car.getCurrentPos().getX() == (pos.getX())
                && car.getCurrentPos().getY() == pos.getY());
        Obstacle ob = new Obstacle(Vehicles.BUS, new P2d(X_POS, 1),
                new S2d(0, 1));
        assertEquals(ob.getLifeDamage(), 3);
        assertEquals(ob.getMalus(), BUS_MAL);
        car.setSpd(new S2d(1, 1));
        assertTrue(car.getCurrentSpd().getX() == 1
                && car.getCurrentSpd().getY() == 0);
        car.setSpd(new S2d(1, 1));
        assertTrue(ob.getCurrentSpd().getX() == 0
                && ob.getCurrentSpd().getY() == 1);
        assertEquals(car.getType(), Vehicles.USER_CAR);
        assertEquals(ob.getType(), Vehicles.BUS);
    }
}
