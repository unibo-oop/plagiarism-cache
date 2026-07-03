package laterunner.model.vehicle;

import laterunner.physics.P2d;
import laterunner.physics.S2d;

/**
 * User's Car.
 */
public class Car extends Vehicle {

    private static final int X_POS = 435;
    private static final int Y_POS = 510;

    /**
     * Instantiates a new Car.
     */
    public Car() {
        super(new P2d(X_POS, Y_POS), new S2d(0, 0), Vehicles.USER_CAR);
    }

    @Override
    public void setSpd(final S2d speed) {
        super.setCheckedSpeed(new S2d(speed.getX(), 0));
    }
}
