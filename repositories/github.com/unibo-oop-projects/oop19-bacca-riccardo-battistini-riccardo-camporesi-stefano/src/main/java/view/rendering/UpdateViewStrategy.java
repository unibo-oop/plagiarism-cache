package view.rendering;

import java.util.Set;

import model.entities.trafficlight.PhaseManager;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.vehicle.Vehicle;
import model.environment.cell.Cell;
import model.environment.grid.Grid;

/**
 * Interface that states how should be displayed entities.
 */
public interface UpdateViewStrategy {

    /**
     * Creates a background image based on the scenario parameters.
     *
     * @param cellGrid the grid from which the background should be created
     */
    void createBackground(Grid<Cell> cellGrid);

    /**
     * Draws the procedural generated image on the canvas, only if it has been
     * previously created.
     *
     * @throws IllegalStateException if the image is not yet created.
     */
    void renderBackground();

    /**
     * Creates a set of frames with the updated position of each vehicle.
     *
     * @param vehicles      the set of vehicles of the scenario
     * @param trafficLights the set of traffic lights of the scenario
     */
    void composeFrame(Set<Vehicle> vehicles, Set<TrafficLight> trafficLights);

    /**
     * Updates a TextField with information related to the traffic lights of the
     * scenario.
     *
     * @param phMan the phaseManager of the scenario
     * @return A string that represent the remaining time before a new phase change
     *         lights
     */
    String updateTrafficLightWidget(PhaseManager<Phases> phMan);

    /**
     * Updates a TextField with information related to the vehicles of the scenario.
     *
     * @param vehicles the set of vehicles of the scenario
     * @return A string that represent the current number of idle vehicles
     */
    String updateVehiclesWidget(Set<Vehicle> vehicles);

    /**
     * Updates the canvas with the next frame.
     */
    void renderCanvas();

    /**
     * Clears the canvas.
     */
    void clearCanvas();

}
