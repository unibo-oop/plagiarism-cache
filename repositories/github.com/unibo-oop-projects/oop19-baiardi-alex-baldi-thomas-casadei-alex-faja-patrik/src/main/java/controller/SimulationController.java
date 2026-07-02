package controller;

import view.InitialSettings;

/**
 * Interface that manages the simulation.
 *
 */
public interface SimulationController {

    /**
     * Method to start the simulation.
     */
    void start();

    /**
     * Method to stop the simulation.
     */
    void stop();

    /**
     * Method to start a new simulation.
     * 
     * @param initS Initial settings
     */
    void newSimulation(InitialSettings initS);

    /**
     * Method to set the alert state.
     * 
     * @param homeTendency Percentage increment of time that people spend at home
     */
    void notifyAlert(double homeTendency);

    /**
     * Method to unset the alert state.
     */
    void notifyAlertOff();

    /**
     * Method to change the simulation speed.
     * 
     * @param speed Simulation speed
     */
    void setSimulationSpeed(int speed);

    /**
     * Method that gets virus manager.
     * @return virus manager
     */
    VirusManager getVirusManager();

    /**
     * Method for return the size of the map.
     * @return mapSize
     */
    double getMapSize();
}
