package model;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import controller.data.ScenarioData;
import model.entities.trafficlight.PhaseManager;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.vehicle.Vehicle;
import model.environment.Point;
import model.environment.cell.Cell;
import model.environment.grid.Grid;
import model.rules.StandardDriverBehaviour;
import model.rules.utils.RulesDatabase;

public interface Scenario {

    void createEntities();

    void createRulesDatabase();

    void setInfo(ScenarioData infoScenario);

    RulesDatabase getDatabase();

    void update();

    StandardDriverBehaviour getBehaviour();

    Set<Vehicle> getVehicles();

    void setVehicles(Set<Vehicle> vehicles);

    Map<Set<Vehicle>, LinkedList<Point>> getCarPaths();

    Set<TrafficLight> getTrafficLights();

    void setTrafficLights(Set<TrafficLight> trafficLights);

    ScenarioStatus getStatus();

    void setStatus(ScenarioStatus status);

    Point getCrossRoad();

    void initializeScenario();

    Grid<Cell> getGrid();

    PhaseManager<Phases> getPhaseManager();
}
