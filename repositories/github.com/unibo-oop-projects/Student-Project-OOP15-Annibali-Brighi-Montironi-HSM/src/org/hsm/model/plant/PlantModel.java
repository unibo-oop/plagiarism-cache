package org.hsm.model.plant;

/**
 * Plant interface.
 */

public interface PlantModel {

    /**
     * @return return the name of the plant
     */
    String getName();

    /**
     * @return return the botanical name of the plant
     */
    String getBotanicalName();

    /**
     * @return return the optimal PH for the plant
     */
    double getPH();

    /**
     * @return return the light needed by the plant
     */
    double getBrightness();

    /**
     * @return return the growth time of the plant
     */
    int getOptimalGrowthTime();

    /**
     * @return return
     */
    int getLife();

    /**
     * @return return the maximum size of the plant expressed in cm²
     */
    double getSize();

    /**
     * @return return the conductivity needed by the plant
     */
    double getConductivity();

    /**
     * @return return the optimal temperature for the plant
     */
    double getOptimalTemperature();
}
