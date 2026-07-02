package view;

import controller.SimulationController;

/**
 * Interface that manages operation on view.
 */
public interface View {

    /**
     * Start the simulation.
     */
    void startSimulation();

    /**
     * Stop the simulation.
     */
    void stopSimulation();

    /**
     * Set all variable for a new incoming simulation.
     */
    void newSimulation();

    /**
     * Show the result of the simulation.
     * 
     * @param result the result of the simulation
     */
    void simulationResult(boolean result);

    /**
     * Gets the charts.
     * 
     * @return ChartsIml
     */
    ChartsImpl getCharts();

    /**
     * Gets the simulation view.
     * 
     * @return SimulationView
     */
    SimulationView getSimulationView();

    /**
     * Gets the virus setup.
     * 
     * @return VirusSetup
     */
    VirusSetup getVirusSetup();

    /**
     * Sets the controller of the simulation.
     * 
     * @param simController Controller of the simulation
     */
    void setController(SimulationController simController);

}
