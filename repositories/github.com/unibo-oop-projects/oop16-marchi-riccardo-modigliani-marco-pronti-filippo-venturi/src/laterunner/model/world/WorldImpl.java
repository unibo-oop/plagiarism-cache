package laterunner.model.world;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import laterunner.core.Dimensions;
import laterunner.model.collisions.BorderBoundingBox;
import laterunner.model.collisions.BorderHitEvent;
import laterunner.model.collisions.ObstacleHitEvent;
import laterunner.model.collisions.WorldEventListener;
import laterunner.model.level.SetLevel;
import laterunner.model.vehicle.Car;
import laterunner.model.vehicle.Obstacle;
import laterunner.model.vehicle.Vehicle;
import laterunner.model.vehicle.Vehicles;

/**
 * The class in witch is implemented the world features.
 *
 */
public class WorldImpl implements World {

    private static final int LOWER_BORDER = 720;
    private static final int BORDER_DAMAGE = 500;

    private List<Obstacle> obstacle;
    private SetLevel setLevel;
    private Car car;
    private BorderBoundingBox mainBBox;
    private WorldEventListener evListener;

    /**
     * 
     * @param bBox
     *          the bounding box to be set
     */
    public WorldImpl(final BorderBoundingBox bBox) {
        obstacle = new LinkedList<Obstacle>();
        setLevel = new SetLevel();
        mainBBox = bBox;
        }

    @Override
    public void setEventListener(final WorldEventListener l) {
        evListener = l;
    }

    @Override
    public void removeObstacle(final Obstacle obj) {
        obstacle.remove(obj);
    }

    @Override
    public List<Vehicle> getSceneEntities() {
        List<Vehicle> entities = new ArrayList<Vehicle>();
        entities.addAll(obstacle);
        entities.add(car);
        return entities;
    }

    @Override
    public void updateState(final int elapsed) {
        for (Obstacle o : obstacle) {
            o.updateState(elapsed);
            if (o.getCurrentPos().getY() > LOWER_BORDER) {
                obstacle.remove(o);
                }
            }
        car.updateState(elapsed);
        checkBoundaries();
        checkCollisions();
    }

   @Override
   public void generateLevel(final int i) {
        this.setCar(new Car());
        this.obstacle.addAll((this.setLevel.getLevel(i)).getLevel());
        }

    @Override
    public Car getCar() {
        return car;
    }

    private void setCar(final Car c) {
            this.car = c;
        }
    private void checkBoundaries() {
        if (this.mainBBox.isCollidingWith(getCar())) {
                evListener.notifyEvent(new BorderHitEvent());
        }
    }

    private void checkCollisions() {
        Optional<Obstacle> found = Optional.empty();
        for (Obstacle obj: obstacle) {
            if (obj.getCurrentPos().getY() + Dimensions.getDimensions().getVehicleHeight(obj.getType()) >= (car.getCurrentPos().getY() + 2)
                    && obj.getCurrentPos().getY() <= car.getCurrentPos().getY() + Dimensions.getDimensions().getVehicleHeight(Vehicles.USER_CAR)) {
                if (obj.getBBox().isCollidingWith(getCar())) {
                    found = Optional.of(obj);
                    break;
                }
            }
        }
        if (found.isPresent()) {
            evListener.notifyEvent(new ObstacleHitEvent(found.get()));
        }
    }

    @Override
    public int getBorderDamage() {
        return BORDER_DAMAGE;
    }

}
