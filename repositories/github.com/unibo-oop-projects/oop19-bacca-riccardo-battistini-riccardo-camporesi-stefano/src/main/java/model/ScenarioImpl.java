package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import controller.data.ScenarioData;
import model.rules.StandardDriverBehaviour;
import model.rules.sight.DriverSight;
import model.rules.utils.RulesDatabase;
import model.entities.road.Road;
import model.entities.trafficlight.PhaseManager;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.vehicle.Vehicle;
import model.environment.Point;
import model.environment.cell.Cell;
import model.environment.grid.Grid;
import static constraints.GridConstraints.GRID_HEIGHT;
import static constraints.GridConstraints.GRID_WIDTH;

public class ScenarioImpl implements Scenario {

    private VehicleQueue vehicleQueue;
    private RulesDatabase database;
    private ScenarioData infoScenario;
    private Grid<Cell> grid;
    private Set<Road> roads;
    private Set<TrafficLight> trafficLights;
    private Set<Vehicle> vehicles;
    private final EntityFactory factory;
    private SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> mergedLane;
    private Map<Set<Vehicle>, LinkedList<Point>> carPaths;
    private DriverSight sight;
    private StandardDriverBehaviour behaviour;
    private ScenarioStatus status;
    private PhaseManager<Phases> phMan;

    public ScenarioImpl() {
        carPaths = new HashMap<>();
        roads = new HashSet<>();
        vehicles = new HashSet<>();
        mergedLane = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        carPaths = new HashMap<>();
        factory = new EntityFactoryImpl();
        trafficLights = new HashSet<>();
    }

    @Override
    public final void createEntities() {
        factory.check();
        grid = factory.createGrid(GRID_WIDTH, GRID_HEIGHT);
        roads = factory.createRoads(infoScenario.getRoads(), grid);
        mergedLane = factory.createGraph(roads);
        vehicles = factory.createVehicles(infoScenario.getVehicles(), roads);
        vehicles.forEach(vehicle -> factory.setVehiclesPath(vehicle, carPaths, mergedLane, grid));
        setTrafficLights(factory.createTrafficLights(infoScenario.getRoads(), grid));
        phMan = factory.createPhaseManager(trafficLights, infoScenario.getTrafficLight());
        database.modifyScenarioInstance(this);
        vehicleQueue.attachToLane();
    }

    @Override
    public final void createRulesDatabase() {
        database = new RulesDatabase();
        vehicleQueue = new VehicleQueueImpl();
        sight = new DriverSight();
        behaviour = new StandardDriverBehaviour(sight); // qui va passato anche un set di trafficlight
        database.register(behaviour);
        database.register(vehicleQueue);
    }

    /**
     *
     */
    @Override
    public void setInfo(final ScenarioData infoScenario) {
        this.infoScenario = infoScenario;
    }

    /**
     * @return the database
     */
    @Override
    public RulesDatabase getDatabase() {
        return database;
    }

    /**
     * @return the behaviour
     */
    @Override
    public StandardDriverBehaviour getBehaviour() {
        return behaviour;
    }

    /**
     * @return vehicles
     */
    @Override
    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * @param vehicles the vehicles to set
     */
    @Override
    public void setVehicles(final Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
        database.modifyScenarioInstance(this);
    }

    /**
     * @return the carPaths
     */
    @Override
    public Map<Set<Vehicle>, LinkedList<Point>> getCarPaths() {
        return carPaths;
    }

    /**
     * @return the trafficLights
     */
    @Override
    public Set<TrafficLight> getTrafficLights() {
        return trafficLights;
    }

    /**
     * @param trafficLights the trafficLights to set
     */
    @Override
    public void setTrafficLights(final Set<TrafficLight> trafficLights) {
        this.trafficLights = trafficLights;
    }

    /**
     *
     */
    @Override
    public void update() {
        vehicleQueue.flush();
        vehicleQueue.update();
        phMan.update();
        database.modifyScenarioInstance(this);
        behaviour.roadSight();
    }

    @Override
    public final Point getCrossRoad() {
        return factory.findCrossRoad(grid);
    }

    /**
     *
     */
    @Override
    public ScenarioStatus getStatus() {

        return status;
    }

    /**
     *
     */
    @Override
    public void setStatus(final ScenarioStatus status) {
        this.status = status;
    }

    /**
     *
     */
    @Override
    public void initializeScenario() {
        setStatus(ScenarioStatus.SETUP);
    }

    /**
     *
     */
    @Override
    public Grid<Cell> getGrid() {
        return grid;
    }

    /**
     * @return the factory
     */
    public EntityFactory getFactory() {
        return factory;
    }

    /**
     * @return the roads
     */
    public Set<Road> getRoads() {
        return roads;
    }

    /**
     * @return the phase manager
     */
    @Override
    public PhaseManager<Phases> getPhaseManager() {
        return phMan;
    }

}
