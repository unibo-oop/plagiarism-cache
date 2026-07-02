package testrules;

import static constraints.VehicleConstraints.AREA_OF_CONTROL;
import static constraints.VehicleConstraints.MIN_VELOCITY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import constraints.DirOfMovement;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.trafficlight.TrafficLightImpl;
import model.entities.vehicle.Vehicle;
import model.entities.vehicle.VehicleBuilder;
import model.environment.Point;
import model.rules.sight.DriverSight;
import model.rules.sight.TurningLeft;
import model.rules.StandardDriverBehaviour;

/**
 * contains test about vehicles sight. more specifically about: visibility of
 * other vehicles and traffic light, distance calculation from scenario's
 * obstacles.
 */
class DriverSightTests {

    /**
     * static variables used to initialize the scenario and set destination and
     * departure of two separate cars.
     */
    private static final int GRIDMAXLENGHT = 99;
    private static final int GRIDMIDHEIGTH = 50;
    private static final int INTERSECTION = 39;

    private Set<Vehicle> vehicles;
    private Set<TrafficLight> trafficLights;

    private DriverSight sight;
    private StandardDriverBehaviour beavior;

    private TurningLeft turn;
    private Map<Set<Vehicle>, LinkedList<Point>> carPaths;

    /**
     * Initialize tests.
     */
    @BeforeEach
    public final void initialize() {
        vehicles = new HashSet<>();
        trafficLights = new HashSet<>();
        sight = new DriverSight();
        beavior = new StandardDriverBehaviour(sight);
        turn = new TurningLeft();
        carPaths = new HashMap<>();

        vehicles.addAll(List.of(
                new VehicleBuilder().setVelocity(MIN_VELOCITY + 1).setAreaOfControl(AREA_OF_CONTROL)
                        .setSense(DirOfMovement.WEST_EAST).setDeparture(1, GRIDMIDHEIGTH)
                        .setDestination(GRIDMAXLENGHT, GRIDMIDHEIGTH).build(),

                new VehicleBuilder().setVelocity(MIN_VELOCITY + 1).setAreaOfControl(AREA_OF_CONTROL)
                        .setSense(DirOfMovement.WEST_EAST).setDeparture(3, GRIDMIDHEIGTH)
                        .setDestination(GRIDMAXLENGHT, GRIDMIDHEIGTH).build()));

        trafficLights.add(TrafficLightImpl.createTrafficLight(Point.of(GRIDMIDHEIGTH, GRIDMIDHEIGTH),
                DirOfMovement.WEST_EAST, Phases.GREEN));

        /**
         * to set vehicles path, manually to do test.
         */
        final LinkedList<Point> list = new LinkedList<>();
        for (int i = 0; i < GRIDMAXLENGHT; i++) {
            list.add(Point.of(GRIDMIDHEIGTH, i));
        }
        carPaths.put(new HashSet<>(vehicles), list);
        turn.setCarPaths(carPaths);
    }

    /**
     * test driverSight method to calculate vehicle distance from another Vehicle
     * (if in the areaOfControl of the driver).
     */
    @Test
    public void testDriverSightVehiclesDistance() {
        final int expected = 1;
        final Optional<Integer> opt = sight.getObstacle(
                vehicles.stream().filter(x -> x.getDeparture().getX() == 1).findFirst().get(), vehicles,
                Point.of(INTERSECTION, INTERSECTION), turn);
        assertEquals(expected, opt.get());
    }

    /**
     * Tests driverSight method to calculate vehicle distance from a traffic light
     * (if in the areo of control of the driver).
     */
    @Test
    public void testDriverSightTrafficLightDistance() {
        final Optional<Integer> expected = Optional.empty();
        final Optional<Integer> opt = beavior.getTrafficLightDistance(
                sight.getTrafficLightObstacle(vehicles.stream().findFirst().get(), trafficLights));
        assertEquals(expected, opt);
    }
}
