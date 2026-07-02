package org.hsm.view.gui;

import java.util.Optional;

import javax.swing.JFrame;

/**
 * This interface contains all the most important action that an user can do
 * with this application.
 */
public interface View extends VisibleComponent {

    /**
     * Get the JFrame of the MainFrame.
     * 
     * @return the JFrame
     */
    JFrame getFrame();

    /**
     * Set the GUI status.
     * 
     * @param status
     *            the status of the GUI
     */
    void setActive(final boolean status);

    /**
     * Insert a greenhouse in the view.
     * 
     * @param name
     *            the greenhouse name
     * @param size
     *            the greenhouse size
     * @param cost
     *            the greenhouse cost
     * @param typology
     *            the greenhouse typology
     * @param freeSpace
     *            the greenhouse free space
     * @param occupiedSpace
     *            the greenhouse occupied space
     * @param numberOfPlants
     *            the number of plants of the greenhouse
     * @param overCost
     *            the overall cost of the greenhouse
     */
    void insertGreenhouse(final String name, final double size, final double cost, final String typology,
            final double freeSpace, final double occupiedSpace, final int numberOfPlants, final double overCost);

    /**
     * Insert a plant into Greenhouse view changing the status of the
     * greenhouse.
     * 
     * @param id
     *            the id of the plant
     * @param name
     *            the name of the plant
     * @param cost
     *            the cost of the plant
     * @param ph
     *            the ph of the plant
     * @param brightness
     *            the brightness of the plant
     * @param conductivity
     *            the conductivity of the plant
     * @param temperature
     *            the temperature of the plant
     */
    void insertNewPlant(final int id, final String name, final double cost, final double ph, final double brightness,
            final double conductivity, final double temperature);

    /**
     * Insert a plant into Greenhouse view whithout changing the status of the
     * greenhouse.
     * 
     * @param id
     *            the id of the plant
     * @param name
     *            the name of the plant
     * @param cost
     *            the cost of the plant
     * @param ph
     *            the ph of the plant
     * @param brightness
     *            the brightness of the plant
     * @param conductivity
     *            the conductivity of the plant
     * @param temperature
     *            the temperature of the plant
     */
    void insertPlant(final int id, final String name, final double cost, final double ph, final double brightness,
            final double conductivity, final double temperature);

    /**
     * Remove the selected plant into the table.
     */
    void removeSelectedPlant();

    /**
     * Insert a model plant into Database view.
     * 
     * @param name
     *            the name of the plant
     * @param botanicalName
     *            the botanical name of the plant
     * @param ph
     *            the optimal ph of the plant
     * @param brightness
     *            the optimal brightness level for the plant
     * @param conductivity
     *            the optimal conductivity level for the plant
     * @param optimalGrowthTime
     *            the optimal growth time for the plant
     * @param temperature
     *            the optimal temperature for the plant
     * @param life
     *            the life of the plant
     * @param size
     *            the size of the plant
     */
    void insertModelPlant(final String name, final String botanicalName, final double ph, final double brightness,
            final int optimalGrowthTime, final int life, final double size, final double conductivity,
            final double temperature);

    /**
     * Remove the selected model plant into the database table.
     */
    void removeSelectedModelPlant();

    /**
     * Clean all the data in the View about Greenhouse.
     */
    void cleanGreenhousePlants();

    /**
     * Clean all the data in the View about Database.
     */
    void cleanDatabase();

    /**
     * Show the dialog to save the Greenhouse into a file.
     * 
     * @return the path
     */
    Optional<String> saveGreenhouseDialog();

    /**
     * Show the dialog to load the Greenhouse from a file.
     * 
     * @return the path
     */
    Optional<String> loadGreenhouseDialog();

    /**
     * Show the dialog to save the Database into a file.
     * 
     * @return the path
     */
    Optional<String> exportDatabaseDialog();

    /**
     * Show the dialog to load the Database from a file.
     * 
     * @return the path
     */
    Optional<String> importDatabaseDialog();

    /**
     * Get the botanical name of the selected row.
     * 
     * @return the botanical name selected
     * @throws IllegalStateException
     *             no row is selected
     */
    String getSelectedBotanicalName() throws IllegalStateException;

    /**
     * Get the ID of the selected row.
     * 
     * @return the ID of the plant
     * @throws IllegalStateException
     *             no row is selected
     */
    int getSelectedIDPlant() throws IllegalStateException;

}
