package org.hsm.controller.simulator;

import java.util.List;

import org.hsm.model.plant.Plant;

/**
 *
 * Interface for Greenhouse simulator.
 *
 */

public interface Simulator {

    /**
     *
     * @param plant
     *            the plant to simulate
     * @return the Optimal Ph (pH)
     */
    double getOptimalPh(Plant plant);

    /**
     *
     * @param plant
     *            the plant to simulate
     * @return the Optimal Brightness (lumen)
     */
    double getOptimalBrightness(Plant plant);

    /**
     *
     * @param plant
     *            the plant to simulate
     * @return the Optimal Conductibility (cF)
     */
    double getOptimalConductibility(Plant plant);

    /**
     *
     * @param plant
     *            the plant to simulate
     * @return the Optimal Temperature (°C)
     */
    double getOptimalTemperature(Plant plant);

    /**
     *
     * @param plant
     *            the plant to simulate
     * @return the simulated Ph (pH)
     */
    double getSimulatedPh(Plant plant);

    /**
     *
     * @param plant
     *            the plant to simulate
     * @return the simulated Brightness (lumen)
     */
    double getSimulatedBrightness(Plant plant);

    /**
     *
     * @param plant
     *            the plant to simulate
     * @return the simulated Conductibility (cF)
     */
    double getSimulatedConductibility(Plant plant);

    /**
     *
     * @param plant
     *            the plant to simulate
     * @return the simulated Temperature (�C)
     */
    double getSimulatedTemperature(Plant plant);

    /**
     * Provide a list of water comsuption of the greenhouse.
     *
     * @return the list of simulated water comsuption
     */
    List<Double> getSimulatedWaterConsuption();

    /**
     * Provide a list of plants grown.
     *
     * @return the list of simulated plant grow
     */
    List<Double> getSimulatedPlantGrow();

    /**
    *
    * @param plant
    *            the plant to simulate
    * @return the real simulated Ph (pH)
    */
   double getRealPh(Plant plant);

   /**
    *
    * @param plant
    *            the plant to simulate
    * @return the real simulated Brightness (lumen)
    */
   double getRealBrightness(Plant plant);

   /**
    *
    * @param plant
    *            the plant to simulate
    * @return the real simulated Conductibility (cF)
    */
   double getRealConductibility(Plant plant);

   /**
    *
    * @param plant
    *            the plant to simulate
    * @return the real simulated Temperature (°C)
    */
   double getRealTemperature(Plant plant);

   /**
    * Provide a list of water comsuption of the traditional coltivation.
    *
    * @return the list of water comsuption
    */
   List<Double> getRealWaterConsuption();

   /**
    * Provide a list of plants grown in traditional coltivation.
    *
    * @return the list of plant grow
    */
   List<Double> getRealPlantGrow();

}
