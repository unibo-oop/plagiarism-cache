package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constraints.CellConstraints;
import constraints.DirOfMovement;
import constraints.VehicleConstraints;
import constraints.VehicleConstraints.Status;
import controller.ParametersHolder;
import controller.data.ScenarioData;
import model.EntityFactory;
import model.EntityFactoryImpl;
import model.entities.road.Road;
import model.entities.vehicle.Vehicle;
import model.entities.vehicle.VehicleBuilder;
import model.entities.vehicle.VehicleImpl;
import model.environment.Point;
import model.environment.cell.Cell;
import model.environment.grid.Grid;
import model.environment.grid.GridFactoryImpl;
import view.utils.ControllerVariablesNames;

import static constraints.GridConstraints.GRID_HEIGHT;
import static constraints.GridConstraints.GRID_WIDTH;
import static constraints.VehicleConstraints.AREA_OF_CONTROL;
import static constraints.VehicleConstraints.MIN_VELOCITY;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 
 * Test for class EntityFacotry.
 *
 */
public class TestEntityFactory {

  private Map<ControllerVariablesNames, Double> parameters;
  private ParametersHolder param;
  private ScenarioData scenario;
  private EntityFactory factory;
  private Grid<Cell> grid;
  private Set<Road> roads;
  private SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> mergedLane;
  private Set<Vehicle> vehicles;
  private Map<Set<Vehicle>, LinkedList<Point>> vehiclePath;
  private Vehicle vehicle1;

 /**
  * parameters initialization.
  */
  @BeforeEach
  public final void initialize() {

      this.parameters = new HashMap<>();
      this.scenario = new ScenarioData();
      this.factory = new EntityFactoryImpl();
      this.roads = new HashSet<>();
      this.mergedLane = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
      this.vehicles = new HashSet<>();
      this.vehiclePath = new HashMap<>();

      this.parameters.put(ControllerVariablesNames.NORTH, 3.0);
      this.parameters.put(ControllerVariablesNames.SOUTH, 5.0);
      this.parameters.put(ControllerVariablesNames.WEST, 0.0);
      this.parameters.put(ControllerVariablesNames.EAST, 2.0);
      this.parameters.put(ControllerVariablesNames.NLANES, 2.0);
      this.parameters.put(ControllerVariablesNames.MAXVEL, 6.0);
      this.parameters.put(ControllerVariablesNames.GREENLIGHT, 5.0);

      this.param = new ParametersHolder(parameters);
      this.param.createScenario();
      this.scenario = param.getScenarioData();

      this.vehicle1 = new VehicleBuilder().setVelocity(MIN_VELOCITY + 1).setAreaOfControl(AREA_OF_CONTROL)
                                          .setSense(DirOfMovement.NORTH_SOUTH).setStatus(Status.ENTERING)
                                          .setDeparture(39, 77)
                                          .setDestination(77, 39)
                                          .setMaxVel(4)
                                          .build();
  }


  /**
   * check if the creation is done in an orderly way.
   */
  @Test()
  public void orderConstruction() {
      this.factory.check();

      Assertions.assertThrows(IllegalStateException.class, () -> factory.createRoads(scenario.getRoads(), grid));
      Assertions.assertThrows(IllegalStateException.class, () -> factory.setVehiclesPath(vehicle1, vehiclePath, mergedLane, grid));
      Assertions.assertThrows(IllegalStateException.class, () -> factory.createGraph(roads));
      Assertions.assertThrows(IllegalStateException.class, () -> factory.createVehicles(scenario.getVehicles(), roads));
  }

  /**
   * Test for entity creation.
   * Creating all the entities and check that the construction is right
   */
  @Test
  public void testFactory() {
      this.factory.check();

      this.grid = factory.createGrid(GRID_WIDTH, GRID_HEIGHT);
      assertEquals(new GridFactoryImpl().create(GRID_WIDTH, GRID_WIDTH).getGenericMap(), grid.getGenericMap());

      this.roads = factory.createRoads(scenario.getRoads(), grid);
      assertEquals(CellConstraints.CROSSROAD, grid.getGenericMap().get(Point.of(39, 39)).getType());
      assertEquals(CellConstraints.CROSSROAD, grid.getGenericMap().get(Point.of(39, 40)).getType());
      assertEquals(CellConstraints.CROSSROAD, grid.getGenericMap().get(Point.of(40, 39)).getType());
      assertEquals(CellConstraints.CROSSROAD, grid.getGenericMap().get(Point.of(40, 40)).getType());
      assertEquals(2, roads.size());

      this.mergedLane = factory.createGraph(roads);
      assertEquals(true, mergedLane.containsEdge(grid.getElement(Point.of(39, 40)),grid.getElement(Point.of(39, 39)))); 
      assertEquals(true, mergedLane.containsEdge(grid.getElement(Point.of(40, 39)),grid.getElement(Point.of(40, 40))));
      assertEquals(false, mergedLane.containsEdge(grid.getElement(Point.of(40, 40)),grid.getElement(Point.of(40, 39))));
      assertEquals(false, mergedLane.containsEdge(grid.getElement(Point.of(39, 39)),grid.getElement(Point.of(39, 40))));

      this.vehicles = factory.createVehicles(scenario.getVehicles(), roads);
      for (Vehicle vehicle : vehicles) {
          assertEquals(VehicleConstraints.Status.ENTERING, vehicle.getStatus());
          assertEquals(CellConstraints.START, grid.getElement(vehicle.getDeparture()).getType());
          assertEquals(CellConstraints.END, grid.getElement(vehicle.getDestination()).getType());
      }
  }
}
