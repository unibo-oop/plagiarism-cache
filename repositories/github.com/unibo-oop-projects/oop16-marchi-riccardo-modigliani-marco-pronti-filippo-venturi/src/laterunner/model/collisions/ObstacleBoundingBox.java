package laterunner.model.collisions;

import laterunner.core.Dimensions;
import laterunner.model.vehicle.Car;
import laterunner.model.vehicle.Vehicles;

/**
 * Horizontal segment of the obstacles' occupied space.
 */
public class ObstacleBoundingBox implements BoundingBox {

    private double xPos;
    private double xPosSim;

    /**
     * Instantiates the horizontal segment of the obstacle's occupied space.
     * 
     * @param posX
     *          left side of the obstacle
     * @param obstacle
     *          type of the obstacle
     */
    public ObstacleBoundingBox(final double posX, final Vehicles obstacle) {
        this.xPos = posX;
        this.xPosSim = this.xPos + (double) Dimensions.getDimensions().getVehicleWidth(obstacle);
    }

    @Override
    public boolean isCollidingWith(final Car car) {
        return car.getCurrentPos().getX() <= xPosSim
                && car.getCurrentPos().getX()
                + Dimensions.getDimensions().getVehicleWidth(Vehicles.USER_CAR)
                >= this.xPos;
    }

}
