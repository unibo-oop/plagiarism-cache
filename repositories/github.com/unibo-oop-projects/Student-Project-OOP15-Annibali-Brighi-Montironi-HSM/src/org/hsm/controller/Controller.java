package org.hsm.controller;

import java.util.List;

import org.hsm.model.Greenhouse;
import org.hsm.model.db.Database;
import org.hsm.model.plant.PlantModel;
import org.hsm.view.gui.View;

/**
 * Interface for controller operations.
 *
 */

public interface Controller {
    /**
     * Create a new Greenhouse.
     *
     * @param name
     *            the name for Greenhouse
     * @param greenhouseType
     *            the type of Greenhouse you want (watch enumeration)
     * @param cost
     *            the cost of Greenhouse
     * @param size
     *            the size of Greenhouse
     */
    void createGreenhouse(String name, String greenhouseType, int cost, int size);

    /**
     * Get the atcually loaded Greenhouse.
     *
     * @return the actual Greenhouse
     */
    Greenhouse getGreenhouse();

    /**
     * Get the Database.
     *
     * @return the Plants Database
     */
    Database getDatabase();

    /**
     * Get the View.
     *
     * @return the view object
     */
    View getView();

    /**
     * Delete the Greenhouse actually loaded.
     */
    void deleteGreenhouse();

    /**
     * Delete the Database actually loaded.
     */
    void newDatabase();

    /**
     * Add n plants to loaded Greenhouse.
     *
     * @param plant
     *            the type of plant to insert
     * @param cost
     *            the cost in euro of the plant
     * @param n
     *            number of platns to add
     *
     */
    void addPlants(PlantModel plant, int cost, int n);

    /**
     * Delete the selected plant in the greenhouse.
     */
    void delPlant();

    /**
     * Delete all plants of the same type provided in input(view).
     *
     */
    void delPLants();

    /**
     * Start the auto updater thread for update plants values every time
     * seconds.
     *
     * @param time
     *            update rate in seconds
     */
    void autoUpdate(int time);

    /**
     * Stop the autoUpdate.
     */
    void stopUpdate();

    /**
     * Create a new type of plant for the database.
     *
     * @param name
     *            the name of the plant
     * @param botanicalName
     *            the scientific name for the plant
     * @param ph
     *            optimal ph for the plant
     * @param brightness
     *            optimal brightness for the plant
     * @param conductivity
     *            optimal terrain conductibility for the plant
     * @param optimalGrowthTime
     *            optimal Growth time for the plant
     * @param temperature
     *            optimal temperature for the plant
     * @param life
     *            the life of the plant
     * @param size
     *            the space occupied from the plant in the greenhouse
     */
    void createNewPlant(String name, String botanicalName, int ph, int brightness, int conductivity,
            int optimalGrowthTime, int temperature, int life, int size);

    /**
     * Delete the selected plant in database.
     */
    void deleteDbPlant();

    /**
     * @return true if Database is empty
     */
    boolean isDbEmpty();

    /**
     * @return true if a Greenhouse is currently load.
     */
    boolean isGhLoad();

    /**
     * Save on file the current Greenhouse opened in the program.
     */
    void saveGreenhouse();

    /**
     * Load a saved Greenhouse in the program.
     */
    void loadGreenhouse();

    /**
     * Save the current Database on file.
     */
    void saveDatabase();

    /**
     * Load a saved Database.
     */
    void loadDatabase();

    /**
     * Close the App.
     */
    void exit();

    /**
     * Show Brightness Bar Chart to compare values of the selected plant.
     */
    void showBrightnessBarChart();

    /**
     * Show Brightness Line Chart to see the trend of brightness values of the
     * selected plant.
     */
    void showBrightnessLineChart();

    /**
     * Show Ph Bar Chart to compare values of the selected plant.
     */
    void showPhBarChart();

    /**
     * Show Ph Line Chart to see the trend of ph values of the selected plant.
     */
    void showPhLineChart();

    /**
     * Show Temperature Bar Chart to compare values of the selected plant.
     */
    void showTemperatureBarChart();

    /**
     * Show Temperature Line Chart to see the trend of temperature values of the
     * selected plant.
     */
    void showTemperatureLineChart();

    /**
     * Show Conductivity Bar Chart to compare values of the selected plant.
     */
    void showConductivityBarChart();

    /**
     * Show Conductivity Line Chart to see the trend of conductivity values of
     * the selected plant.
     */
    void showConductivityLineChart();

    /**
     * Get a set with all the greenhouse possible types.
     *
     * @return a set whith all the greenhouse possible types
     */
    List<String> getGreenhouseTypes();

    /**
     * Run a configuration to test the app.
     */
    void applicationTest();

}
