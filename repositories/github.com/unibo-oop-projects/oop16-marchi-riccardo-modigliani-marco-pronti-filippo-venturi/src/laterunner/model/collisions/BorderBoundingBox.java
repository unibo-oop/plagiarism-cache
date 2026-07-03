package laterunner.model.collisions;

import java.util.Optional;

import laterunner.core.Dimensions;
import laterunner.model.vehicle.Car;
import laterunner.model.vehicle.Vehicles;
import laterunner.physics.P2d;

/**
 * Square in which the car and obstacles can appear.
 */
public final class BorderBoundingBox implements BoundingBox {

    private static final P2d UPPER_LEFT = new P2d(172, 0);
    private static final P2d BELOW_RIGHT = new P2d(773, 511);
    private static Optional<BorderBoundingBox> singleton = Optional.empty();

    /**
     * Istantiates the street bounding box.
     */
    private BorderBoundingBox() { }

    /**
     * Returns the only class' instance.
     * 
     * @return
     *          border bounding box
     */
    public static BorderBoundingBox getBorderBoundingBox() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new BorderBoundingBox());
        }
        return singleton.get();
    }

    @Override
    public boolean isCollidingWith(final Car car) {
        boolean collision = false;
        if (car.getCurrentPos().getX()
                + Dimensions.getDimensions().getVehicleWidth(Vehicles.USER_CAR)
                > BELOW_RIGHT.getX()) {
            car.setPos(new P2d(BELOW_RIGHT.getX()
                    - Dimensions.getDimensions().getVehicleWidth(Vehicles.USER_CAR) - 1,
                    car.getCurrentPos().getY()));
            collision = true;
        } else if (car.getCurrentPos().getX() < UPPER_LEFT.getX()) {
            car.setPos(new P2d(UPPER_LEFT.getX() + 1, car.getCurrentPos().getY()));
            collision = true;
        }
        return collision;
    }

    /**
     * Returns box' upper left corner.
     * 
     * @return
     *          box' upper left corner.
     */
    public P2d getUpperLeft() {
        return UPPER_LEFT;
    }

    /**
     * Returns box' below right corner.
     * 
     * @return
     *          box' below right corner.
     */
    public P2d getBelowRight() {
        return BELOW_RIGHT;
    }
}
