package org.hsm.model.db;

import java.util.Map;

import org.hsm.model.plant.PlantModel;

/**
 * Interface for DBplants.
 */
public interface Database {

    /**
     * Add a plant into database.
     *
     * @param name
     *            name plant
     * @param botanicalName
     *            botanical name plant
     * @param ph
     *            optimal ph value for the plant
     * @param brightness
     *            light needed by the plant
     * @param optimalGrowthTime
     *            time growth
     * @param life
     *            time life
     * @param size
     *            max size for the plant expressed in cm³
     * @param conductivity
     *            conductivity
     * @param optimalTemperature
     *            optimal temperature for the plant
     */
    void addPlantModel(final String name, final String botanicalName, final int ph, final int brightness,
            final int optimalGrowthTime, final int life, final int size, final int conductivity,
            final int optimalTemperature);

    /**
     *
     * @param botanicalName
     *            botanical name plant
     */
    void removePlantModel(final String botanicalName);

    /**
     *
     * @param str
     *            botanical name
     * @return the PlantModel
     */
    PlantModel getPlantModel(final String str);

    /**
     *
     * @return Database map
     */
    Map<String, PlantModel> getDb();

    /**
     *
     * @return true if Database id empty
     */
    boolean isEmpty();
}
