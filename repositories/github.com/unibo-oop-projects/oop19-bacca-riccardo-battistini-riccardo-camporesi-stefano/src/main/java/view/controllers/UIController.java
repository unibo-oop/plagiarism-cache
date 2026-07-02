package view.controllers;

import java.util.Map;
import java.util.Set;

import javafx.fxml.FXML;
import model.entities.trafficlight.PhaseManager;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.vehicle.Vehicle;
import model.environment.cell.Cell;
import model.environment.grid.Grid;
import view.View;
import view.ViewImpl;
import view.mediator.ControllerMediator;
import view.utils.ControllerVariablesNames;
import view.utils.ViewParameters;

/**
 * The Controller of the main scene. It is connected to a set of subControllers,
 * responsible of handling different areas of the GUI.
 *
 * These are the {@link ControlPanelController}, the {@link SimTabController}
 * and the {@link MenuBarController}.
 *
 * The communication among these classes is possible thanks to the
 * {@link ControllerMediator}.
 *
 */
public class UIController {

    private final ViewParameters viewParam = new ViewParameters();

    @FXML
    private ControlPanelController controlController;

    @FXML
    private MenuBarController menuController;

    @FXML
    private SimTabController simTabController;

    private View view;

    /**
     * Initialises this class allowing communication between sub-controllers.
     * 
     * @param viewImpl the reference to the view used to communicate with the
     *                 application
     */
    public void initUIController(final ViewImpl viewImpl) {
        controlController.setViewParam(viewParam);
        simTabController.setViewParam(viewParam);
        ControllerMediator.getInstance().registerMenuBarController(menuController);
        ControllerMediator.getInstance().registerControlPanelController(controlController);
        ControllerMediator.getInstance().registerUIController(this);
        view = viewImpl;
        controlController.resetParameters();
    }

    /**
     * Tells the main scene that the simulation can begin and allows the user to set
     * parameters.
     */
    public void createScenarioInstance() {
        view.loadScenario();
        controlController.getControls().setDisable(false);
    }

    /**
     * Tells the View to start the simulation.
     */
    public void startSimulation() {
        view.startSimulation();
    }

    /**
     * Tells the View to pause the simulation.
     */
    public void pauseSimulation() {
        view.pauseSimulation();
    }

    /**
     * Tells a sub-controller to create the background.
     *
     * @param grid the grid from which the background should be created
     */
    public void loadBackground(final Grid<Cell> grid) {
        simTabController.renderBackground(grid);
    }

    /**
     * @return an instance of the current parameters of the scenario
     */
    public Map<ControllerVariablesNames, Double> getUpdate() {
        return viewParam.getValues();
    }

    /**
     * Tells the SimTab to render the current status of the simulation, called by
     * View.
     *
     * @param vehicles      the set of vehicles of the scenario
     * @param trafficLights the set of traffic lights of the scenario
     */
    public void render(final Set<Vehicle> vehicles, final Set<TrafficLight> trafficLights) {
        simTabController.updateFrame(vehicles, trafficLights);
    }

    /**
     * Tells the View to pause the simulation and reset info gathered.
     */
    public void stopSimulation() {
        view.pauseSimulation();
        simTabController.resetView();
    }

    /**
     * Tells a sub-controller to update the current info of the scenario displayed.
     *
     * @param vehicles the set of vehicles of the scenario
     * @param phMan    the phaseManager of the scenario
     * @param time     the time passed since the last update
     */
    public void updateReport(final Set<Vehicle> vehicles, final PhaseManager<Phases> phMan, final double time) {
        simTabController.updateReport(phMan, vehicles, time);
    }

}
