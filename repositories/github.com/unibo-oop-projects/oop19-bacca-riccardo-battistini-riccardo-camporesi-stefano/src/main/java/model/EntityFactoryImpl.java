package model;

import static constraints.VehicleConstraints.AREA_OF_CONTROL;
import static constraints.VehicleConstraints.MIN_VELOCITY;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Random;

import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import constraints.DirOfMovement;
import controller.data.RoadData;
import controller.data.TrafficLightData;
import controller.data.VehicleGroupData;
import model.entities.road.OneWayRoad;
import model.entities.road.Road;
import model.entities.road.TwoWayRoad;
import model.entities.trafficlight.PhaseManager;
import model.entities.trafficlight.PhaseManagerImpl;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.trafficlight.TrafficLightImpl;
import model.entities.vehicle.Vehicle;
import constraints.VehicleConstraints.Status;
import model.entities.vehicle.VehicleBuilder;
import model.environment.Point;
import model.environment.cell.Cell;
import constraints.CellConstraints;
import model.environment.grid.Grid;
import model.environment.grid.GridFactory;
import model.environment.grid.GridFactoryImpl;

/**
 * This class's purpose is to create the entities necessary for the creation of
 * a BasicScenario.
 */
public class EntityFactoryImpl implements EntityFactory {

    private boolean checkGrid;
    private boolean checkRoads;
    private boolean checkGraph;
    private boolean checkVehicles;;

    /**
     * {@inheritDoc}
     */
    public void check() {
        this.checkGrid = false;
        this.checkRoads = false;
        this.checkGraph = false;
        this.checkVehicles = false;
    }

    /**
     * {@inheritDoc}
     */
    public Grid<Cell> createGrid(final int gridWidth, final int gridHeight) {
        final GridFactory factory = new GridFactoryImpl();
        this.checkGrid = true;
        return factory.create(gridWidth, gridHeight);
    }

    /**
     * {@inheritDoc}
     */
    public Set<Road> createRoads(final Set<RoadData> allRoads, final Grid<Cell> grid) {
        final Set<Road> roads = new HashSet<>();
        if (this.checkGrid) {

            for (final RoadData road : allRoads) {
                switch (road.getLanesNumber()) {

                case 1:
                    final Road r1 = new OneWayRoad(road, grid);
                    r1.createRoad();
                    roads.add(r1);
                    break;

                case 2:
                    final Road r2 = new TwoWayRoad(road, grid);
                    r2.createRoad();
                    roads.add(r2);
                    break;

                default:
                    break;
                }
            }
            this.checkRoads = true;
        } else {
            throw new IllegalStateException("Grid has not yet been created!");
        }
        return roads;
    }

    /**
     * {@inheritDoc}
     */
    public SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> createGraph(final Set<Road> roads) {
        final SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> lanes = new SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge>(
                DefaultWeightedEdge.class);
        if (this.checkRoads) {

            roads.stream().flatMap(i -> i.getRoads().entrySet().stream()).flatMap(j -> j.getValue().stream())
                    .forEach(k -> Graphs.addGraph(lanes, k.getLane()));

            this.checkGraph = true;
        } else {
            throw new IllegalStateException("Roads have not yet been created!");
        }

        return lanes;
    }

    /**
     * {@inheritDoc}
     */
    public Set<Vehicle> createVehicles(final Set<VehicleGroupData> vehicles, final Set<Road> roads) {
        final Set<Vehicle> totalVehicles = new HashSet<>();

        Point start;
        final Random r = new Random();

        if (this.checkGraph) {
            for (final VehicleGroupData v : vehicles) {
                List<Point> endPoints = this.findEnd(roads, v.getSense());
                start = this.findStart(v, roads);
                totalVehicles.addAll(this.createSetVehicles(v, start, endPoints.get(r.nextInt(endPoints.size()))));
                endPoints.clear();
            }
            this.checkVehicles = true;
        } else {
            throw new IllegalStateException("Lanes haven't yet been merged!");
        }
        return totalVehicles;
    }

    /**
     *
     * @param vehicles info of a group of vehicles
     * @param roads    all roads built
     * @return a set of vehicle built
     */
    private Set<Vehicle> createSetVehicles(final VehicleGroupData vehicles, final Point start, final Point end) {
        final Set<Vehicle> vehiclesSet = new HashSet<>();
        final Point departure = start;
        final Point destination = end;

        for (int i = 0; i < vehicles.getNumber(); i++) {
            vehiclesSet.add(new VehicleBuilder().setVelocity(MIN_VELOCITY + 1).setAreaOfControl(AREA_OF_CONTROL)
                    .setSense(vehicles.getSense()).setStatus(Status.ENTERING)
                    .setDeparture(departure.getX(), departure.getY())
                    .setDestination(destination.getX(), destination.getY())
                    .setMaxVel(vehicles.getMaxVel())
                    .build());
        }
        return vehiclesSet;
    }

    /**
     * {@inheritDoc}
     */
    public void setVehiclesPath(final Vehicle vehicle, final Map<Set<Vehicle>, LinkedList<Point>> carPath, final SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> mergedLane, final Grid<Cell> grid) {
        final Set<Vehicle> list = new HashSet<>();
        boolean added = false;

        if (this.checkVehicles) {
            if (carPath.isEmpty()) {
                list.add(vehicle);
                carPath.put(list, this.findNewPath(vehicle, mergedLane, grid));
            } else {
                for (final Entry<Set<Vehicle>, LinkedList<Point>> entry : carPath.entrySet()) {
                    if (entry.getValue().getFirst().equals(vehicle.getDeparture())
                            && entry.getValue().getLast().equals(vehicle.getDestination())) {
                        entry.getKey().add(vehicle);
                        added = true;
                    }
                }
                if (!added) {
                    list.add(vehicle);
                    carPath.put(list, new LinkedList<>(this.findNewPath(vehicle, mergedLane, grid)));
                }
            }
        } else {
            throw new IllegalStateException("Vehicles haven't been created!");
        }
    }

    /**
     *
     * @param vehicle    that have been set the Path
     * @param carPath    Map where there's a list of the Vehicle that have the same
     *                   Path
     * @param mergedLane the final Graph Lane
     * @return the Path of the vehicle
     */
    private LinkedList<Point> findNewPath(final Vehicle vehicle,
            final SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> mergedLane, final Grid<Cell> grid) {
        List<DefaultWeightedEdge> edges;
        final LinkedList<Point> vehiclePath = new LinkedList<>();

        edges = DijkstraShortestPath.findPathBetween(mergedLane, grid.getElement(vehicle.getDeparture()),
                grid.getElement(vehicle.getDestination()));
        edges.stream().forEach(edge -> vehiclePath.add(mergedLane.getEdgeSource(edge).getPosition()));
        vehiclePath.add(vehicle.getDestination());

        return vehiclePath;
    }

    /**
     *
     * @param roads
     * @return
     */
    private List<Point> findEnd(final Set<Road> roads, final DirOfMovement sense) {
        final List<Point> endPoints = new LinkedList<>();

        roads.stream().flatMap(i -> i.getRoads().entrySet().stream()).flatMap(j -> j.getValue().stream())
                      .filter(j -> !j.getSense().equals(findOpposite(sense)))
                      .forEach(k -> endPoints.add(k.getEnd()));

        return endPoints;
    }

    private DirOfMovement findOpposite(final DirOfMovement sense) {
        switch (sense) {
        case NORTH_SOUTH:
            return DirOfMovement.SOUTH_NORTH;
        case SOUTH_NORTH:
            return DirOfMovement.NORTH_SOUTH;
        case WEST_EAST:
            return DirOfMovement.EAST_WEST;
        case EAST_WEST:
            return DirOfMovement.WEST_EAST;
        default:
                return null;
        }
    }

    /**
     *
     * @param vehicles
     * @param roads
     * @return
     */
    private Point findStart(final VehicleGroupData vehicles, final Set<Road> roads) {
        final Queue<Point> startPoint = new LinkedList<>();
        roads.stream().flatMap(i -> i.getRoads().entrySet().stream()).flatMap(road -> road.getValue().stream())
                .filter(lanes -> lanes.getSense().equals(vehicles.getSense()))
                .forEach(lane -> startPoint.add(lane.getStart()));

        return startPoint.poll();
    }

    /**
     * {@inheritDoc}
     */
    public final Point findCrossRoad(final Grid<Cell> grid) {
        final Map<Point, Cell> map = grid.getGenericMap();
        return map.entrySet().stream().filter(x -> x.getValue().getType().equals(CellConstraints.CROSSROAD)).findFirst().get()
                .getKey();
    }

    /**
     * {@inheritDoc}
     */
    public final Set<TrafficLight> createTrafficLights(final Set<RoadData> roads, final Grid<Cell> grid) {
        if (this.checkVehicles) {
            final Set<TrafficLight> trafficLights = new HashSet<>();
            final Point crossRoad = this.findCrossRoad(grid);

            for (final RoadData r : roads) {
                switch (r.getLanesNumber()) {
                case 1:
                    trafficLights.addAll(this.oneLaneRoadsForTrafficLights(r, crossRoad));
                    break;

                case 2:
                    trafficLights.addAll(this.twoLaneRoadsForTrafficLights(r, crossRoad));
                    break;
                default:
                    break;
                }
            }
            return trafficLights;
        } else {
            throw new IllegalStateException("Vehicles haven't been created!");
        }
    }

    public final PhaseManager<Phases> createPhaseManager(final Set<TrafficLight> trafficLights, final TrafficLightData trafficLightData) {
        PhaseManager<Phases> phMan = new PhaseManagerImpl<Phases>(trafficLightData.getPhasesList());
        trafficLights.forEach(t -> phMan.attach(t));
        return phMan;
    }

    private Set<TrafficLight> oneLaneRoadsForTrafficLights(final RoadData r, final Point crossRoad) {
        final Set<TrafficLight> trafficLights = new HashSet<>();
        switch (r.getSense()) {
        case NORTH_SOUTH:
            final TrafficLight trafficLightNorth = TrafficLightImpl.createTrafficLight(crossRoad.translateY(1), r.getSense(),
                    Phases.GREEN);
            trafficLights.add(trafficLightNorth);
            break;
        case SOUTH_NORTH:
            final TrafficLight trafficLightSouth = TrafficLightImpl.createTrafficLight(crossRoad.translateY(-1), r.getSense(),
                    Phases.GREEN);
            trafficLights.add(trafficLightSouth);
            break;
        case WEST_EAST:
            final TrafficLight trafficLightWest = TrafficLightImpl.createTrafficLight(crossRoad.translateX(-1), r.getSense(),
                    Phases.RED);
            trafficLights.add(trafficLightWest);
            break;
        case EAST_WEST:
            final TrafficLight trafficLightEast = TrafficLightImpl.createTrafficLight(crossRoad.translateX(1), r.getSense(),
                    Phases.RED);
            trafficLights.add(trafficLightEast);
            break;
        default:
            break;
        }

        return trafficLights;
    }

    private Set<TrafficLight> twoLaneRoadsForTrafficLights(final RoadData r, final Point crossRoad) {
        final Set<TrafficLight> trafficLights = new HashSet<>();
        switch (r.getSense()) {
        case NORTH_SOUTH:
            final TrafficLight trafficLightNorth = TrafficLightImpl.createTrafficLight(crossRoad.translateY(1), r.getSense(),
                    Phases.GREEN);
            trafficLights.add(trafficLightNorth);

            final TrafficLight trafficLightSouth = TrafficLightImpl.createTrafficLight(crossRoad.translateY(-1),
                    DirOfMovement.SOUTH_NORTH, Phases.GREEN);
            trafficLights.add(trafficLightSouth);
            break;

        case SOUTH_NORTH:
            final TrafficLight trafficLightSouth2 = TrafficLightImpl.createTrafficLight(crossRoad.translateY(-1),
                    r.getSense(), Phases.GREEN);
            trafficLights.add(trafficLightSouth2);

            final TrafficLight trafficLightNorth2 = TrafficLightImpl.createTrafficLight(crossRoad.translateY(1),
                    DirOfMovement.NORTH_SOUTH, Phases.GREEN);
            trafficLights.add(trafficLightNorth2);
            break;

        case WEST_EAST:
            final TrafficLight trafficLightWest = TrafficLightImpl.createTrafficLight(crossRoad.translateX(-1), r.getSense(),
                    Phases.RED);
            trafficLights.add(trafficLightWest);

            final TrafficLight trafficLightEast = TrafficLightImpl.createTrafficLight(crossRoad.translateX(1),
                    DirOfMovement.EAST_WEST, Phases.RED);
            trafficLights.add(trafficLightEast);
            break;

        case EAST_WEST:
            final TrafficLight trafficLightEast2 = TrafficLightImpl.createTrafficLight(crossRoad.translateX(1), r.getSense(),
                    Phases.RED);
            trafficLights.add(trafficLightEast2);

            final TrafficLight trafficLightWest2 = TrafficLightImpl.createTrafficLight(crossRoad.translateX(-1),
                    DirOfMovement.WEST_EAST, Phases.RED);
            trafficLights.add(trafficLightWest2);
            break;

        default:
            break;
        }

        return trafficLights;
    }

}
