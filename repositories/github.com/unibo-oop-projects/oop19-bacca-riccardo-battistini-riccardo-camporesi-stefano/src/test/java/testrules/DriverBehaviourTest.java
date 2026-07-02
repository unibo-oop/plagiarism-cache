package testrules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constraints.DirOfMovement;
import constraints.TrafficLightConstraints;
import controller.data.RoadData;
import controller.data.ScenarioData;
import controller.data.TrafficLightData;
import controller.data.VehicleGroupData;
import model.Scenario;
import model.ScenarioImpl;
import model.entities.trafficlight.TrafficLight;
import static constraints.TrafficLightConstraints.DEFAULT_PHASE_DURATION;

/**
 * contains test about entities behavior. more specifically about: moving
 * vehicles, setting vehicle's velocity also with traffic light respect.
 */
class DriverBehaviourTest {

    private final int simCounter = 5;
    private Scenario scen;
    private ScenarioData data;
    private static final int MAXVEL = 7;
    private Set<RoadData> roads;
    private Set<VehicleGroupData> vehicles;
    private TrafficLightData trafficLights;

    /**
     * Initialize tests.
     */
    @BeforeEach
    public final void initialize() {
        scen = new ScenarioImpl();
        data = new ScenarioData();

        trafficLights = new TrafficLightData(DEFAULT_PHASE_DURATION);
        trafficLights.setPhasesList();

        roads = new HashSet<RoadData>();
        final RoadData road = new RoadData("nord_sud", 2, DirOfMovement.NORTH_SOUTH);
        final RoadData road2 = new RoadData("west_east", 2, DirOfMovement.WEST_EAST);
        roads.add(road);
        roads.add(road2);

        vehicles = new HashSet<VehicleGroupData>();
        final VehicleGroupData vehicle = new VehicleGroupData(DirOfMovement.NORTH_SOUTH, 1, MAXVEL);
        final VehicleGroupData vehicle2 = new VehicleGroupData(DirOfMovement.WEST_EAST, 1, MAXVEL);
        vehicles.add(vehicle);
        vehicles.add(vehicle2);

        data.setTrafficLight(trafficLights);
        data.setRoads(roads);
        data.setVehicles(vehicles);

        scen.setInfo(data);
        scen.createRulesDatabase();
        scen.createEntities();
    }

    /**
     * Test the vehicle setVelocity rules based on trafficLight state (red, green,
     * yellow) and other vehicles. Those tests, will implicitly test update class,
     * that will modify scenarioImpl structures when reclamed from driver behavior.
     */
    @Test
    public void testVelocityOnTrafficLightRed() {
        /**
         * tests that traffic light will change color (status) when time is spent.
         */
        final int expectedTrafficLightGreen = 0;
        long actualGreenLights = 0;
        // System.out.println(scen.getPhaseManager().getCurrentPhase());
        for (int i = 0; i < TrafficLightConstraints.DEFAULT_PHASE_DURATION; i++) {
            scen.getTrafficLights().forEach(x -> x.updatePhase());
        }
        // System.out.println(scen.getPhaseManager().getCurrentPhase());
        actualGreenLights = scen.getTrafficLights().stream()
                .filter(x -> x.getCurrentPhase().equals(TrafficLight.Phases.GREEN)).count();
        assertEquals(expectedTrafficLightGreen, actualGreenLights);

        /**
         * tests that all cars will be stopped at the red light, after 100 times called
         * rules.
         */
        for (int i = 0; i < 100; i++) {
            scen.getBehaviour().roadSight();
        }
        final int actualCarsExpected = 0;
        final Long actualCarsWithVel = scen.getVehicles().stream().filter(x -> x.getVelocity() != 0).count();
        assertEquals(actualCarsExpected, actualCarsWithVel);

        /**
         * tests that traffic light will change color (status) when time is spent.
         */
        final int expectedTrafficLightRed = 2;
        long actualRedLights = 0;
        for (int i = 0; i < TrafficLightConstraints.DEFAULT_PHASE_DURATION; i++) {
            scen.getTrafficLights().forEach(x -> x.updatePhase());
        }
        actualRedLights = scen.getTrafficLights().stream()
                .filter(x -> x.getCurrentPhase().equals(TrafficLight.Phases.RED)).count();
        assertEquals(expectedTrafficLightRed, actualRedLights);
    }

    @Test
    public void testVelocityOnTrafficLightGreen() {
        /**
         * tests that traffic light will change color (status) when time is spent.
         */
        final int expectedTrafficLightRed = 0;
        long actualRedLights = 0;
        for (int i = 0; i < TrafficLightConstraints.DEFAULT_PHASE_DURATION; i++) {
            scen.getTrafficLights().forEach(x -> x.updatePhase());
        }
        actualRedLights = scen.getTrafficLights().stream()
                .filter(x -> x.getCurrentPhase().equals(TrafficLight.Phases.RED)).count();
        assertEquals(expectedTrafficLightRed, actualRedLights);

        /**
         * tests that all cars will be stopped at the red light
         */
        for (int i = 0; i < 100; i++) {
            scen.getBehaviour().roadSight();
        }
        final int actualCarsExpected = 2;
        final Long actualCarsWithVel = scen.getVehicles().stream().filter(x -> x.getVelocity() == 0).count();
        assertEquals(actualCarsExpected, actualCarsWithVel);

    }

    @Test
    public void testVehicleVelocity() {
        initialize();
        final long expected = scen.getVehicles().stream().count();
        for (int i = 0; i < simCounter; i++) {
            scen.getBehaviour().roadSight();
        }
        final long actual = scen.getVehicles().stream().filter(x -> x.getVelocity() != 0).count();
        assertEquals(expected, actual);
    }

    @Test
    public void testVehiclePositions() {
        final int expected = 0;
        for (int i = 0; i < simCounter; i++) {
            scen.getBehaviour().roadSight();
        }
        final long actual = scen.getVehicles().stream().filter(x -> x.getPosition().equals(x.getDeparture())).count();
        assertEquals(expected, actual);
    }

}
