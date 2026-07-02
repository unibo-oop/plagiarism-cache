package view.controllers;

import view.rendering.UpdateViewStrategy;
import view.rendering.UpdateViewStrategyImpl;
import view.utils.ViewParameters;

import java.util.Set;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import model.entities.trafficlight.PhaseManager;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.vehicle.Vehicle;
import model.environment.cell.Cell;
import model.environment.grid.Grid;

public class SimTabController {

    @FXML
    private TextField elapsedTime;

    @FXML
    private TextField currentIdleVehicles;

    @FXML
    private TextField phaseDuration;

    @FXML
    private Canvas canvas;

    private ViewParameters viewParam;

    private UpdateViewStrategy updateStrategy;

    private double totalTime;

    /**
     * @return the viewParam
     */
    public ViewParameters getViewParam() {
        return viewParam;
    }

    /**
     * @param viewParam the viewParam to set
     */
    public void setViewParam(final ViewParameters viewParam) {
        this.viewParam = viewParam;
    }

    /**
     * Clears the canvas and the textFields that display information about the
     * scenario.
     */
    public void resetView() {
        updateStrategy.clearCanvas();
        totalTime = 0;
        elapsedTime.setText("0");
        currentIdleVehicles.setText("0");
        phaseDuration.setText("0");
    }

    /**
     * Updates the info displayed on the GUI by the textFields.
     *
     * @param phMan
     * @param vehicles
     * @param time
     */
    public void updateReport(final PhaseManager<Phases> phMan, final Set<Vehicle> vehicles, final double time) {
        Platform.runLater(() -> {
            totalTime += time;
            elapsedTime.setText(String.format("%.1f", totalTime));
            currentIdleVehicles.setText(updateStrategy.updateVehiclesWidget(vehicles));
            phaseDuration.setText(updateStrategy.updateTrafficLightWidget(phMan));
        });
    }

    /**
     * Updates the current visualisation of the simulation.
     *
     * @param vehicles      the set of vehicles of the scenario
     * @param trafficLights the set of traffic lights of the scenario
     */
    public void updateFrame(final Set<Vehicle> vehicles, final Set<TrafficLight> trafficLights) {
        Platform.runLater(() -> {
            updateStrategy.composeFrame(vehicles, trafficLights);
            updateStrategy.renderCanvas();
        });
    }

    /**
     *
     * @param grid
     */
    public void renderBackground(final Grid<Cell> grid) {
        Platform.runLater(() -> {
            updateStrategy = new UpdateViewStrategyImpl(canvas, grid);
            updateStrategy.createBackground(grid);
            updateStrategy.renderBackground();
        });
    }

}
