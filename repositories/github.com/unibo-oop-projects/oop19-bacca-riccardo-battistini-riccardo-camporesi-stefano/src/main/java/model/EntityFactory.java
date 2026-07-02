package model;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import controller.data.RoadData;
import controller.data.TrafficLightData;
import controller.data.VehicleGroupData;
import model.entities.road.Road;
import model.entities.trafficlight.PhaseManager;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.vehicle.Vehicle;
import model.environment.Point;
import model.environment.cell.Cell;
import model.environment.grid.Grid;

/**
 * This class's purpose is to create the entities necessary for the creation of
 * a BasicScenario.
 */
public interface EntityFactory {

    /**
     * this fields are used to verify that the creation is done in the right order.
     */
    void check();

    /**
     *
     * @param gridWidth  the width of the grid
     * @param gridHeight the height of the grid
     * @return the grid
     */
    Grid<Cell> createGrid(int gridWidth, int gridHeight);

    /**
     *
     * @param allRoads all the info of the roads
     * @param grid     grid where road are created
     * @return create all the Roads
     */
     Set<Road> createRoads(Set<RoadData> allRoads, Grid<Cell> grid);

    /**
     *
     * @param roads a set of all roads
     * @return merge all lanes in one graphs
     */
    SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> createGraph(Set<Road> roads);

    /**
     *
     * @param vehicles all the info of the vehicles
     * @param roads    all roads built
     * @return create all vehicles
     */
    Set<Vehicle> createVehicles(Set<VehicleGroupData> vehicles, Set<Road> roads);

    /**
     *
     * @param mergedLane the final Graph Lane
     * @param grid
     * @param vehicle
     * @param carPath
     */
     void setVehiclesPath(Vehicle vehicle, Map<Set<Vehicle>, LinkedList<Point>> carPath, SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> mergedLane, Grid<Cell> grid);

     /**
      * 
      * @param grid
      * @return The point where a crossoroad cell is located.
      */
     Point findCrossRoad(Grid<Cell> grid);

     /**
      * 
      * @param trafficLights
      * @param trafficLightData
      * @return The phase manager that will synchronise traffic lights in the scenario
      */
    PhaseManager<Phases> createPhaseManager(Set<TrafficLight> trafficLights, TrafficLightData trafficLightData);

    /**
     * 
     * @param roads
     * @param grid
     * @return The set of traffic lights to be used in the scenario
     */
    Set<TrafficLight> createTrafficLights(Set<RoadData> roads, Grid<Cell> grid);

}
