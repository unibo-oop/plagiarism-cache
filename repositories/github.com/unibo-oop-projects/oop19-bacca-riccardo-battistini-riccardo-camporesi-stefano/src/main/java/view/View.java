package view;

import java.util.Map;
import java.util.Set;

import controller.Controller;
import model.entities.trafficlight.PhaseManager;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.vehicle.Vehicle;
import model.environment.cell.Cell;
import model.environment.grid.Grid;
import view.loader.stagefactory.StageFactory;
import view.utils.ControllerVariablesNames;

/**
 * Interface describing the View of the application. Tells the
 * {@link Controller} about changes in the parameters and is told by the
 * controller to render the simulation.
 */
public interface View {

    /**
     * Loads the main scene.
     *
     * @param controller the controller of the application
     */
    void launch(Controller controller);

    /**
     * Tells the controller to load the scenario from the current available
     * parameters.
     */
    void loadScenario();

    /**
     * Tells the view to create the background.
     *
     * @param cellGrid the grid from which the background should be created
     */
    void loadBackground(Grid<Cell> cellGrid);

    /**
     * Tells the view to draw the current state of the game.
     *
     * @param vehicles      the set of vehicles of the scenario
     * @param trafficLights the set of traffic lights of the scenario
     */
    void render(Set<Vehicle> vehicles, Set<TrafficLight> trafficLights);

    /**
     * @return the list of the View's parameters
     */
    Map<ControllerVariablesNames, Double> getParameters();

    /**
     * Loads new stages on request.
     *
     * @return The sceneFactory
     */
    StageFactory getStageFactory();

    /**
     * Tells the controller to start the simulation.
     */
    void startSimulation();

    /**
     * Tells the controller to pause the simulation.
     */
    void pauseSimulation();

    /**
     * Tells the controller of the view to update information related to the
     * scenario.
     *
     * @param vehicles the set of vehicles of the scenario
     * @param phMan    the phaseManager used to handle traffic light syncronization
     * @param time     the time passed since the last update by the simulation loop
     */
    void updateReport(Set<Vehicle> vehicles, PhaseManager<Phases> phMan, double time);

}
