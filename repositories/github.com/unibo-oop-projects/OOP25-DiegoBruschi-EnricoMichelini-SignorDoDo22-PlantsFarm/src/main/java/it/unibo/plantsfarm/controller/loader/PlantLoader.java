package it.unibo.plantsfarm.controller.loader;

import it.unibo.plantsfarm.controller.memory.api.DataMemory;
import it.unibo.plantsfarm.controller.memory.impl.DataMemoryImpl;
import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.plant.PlantRegistry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the loading of the initial plant set from persistent data.
 */
public class PlantLoader {

    private static final String FILE_NAME = "encyclopedia.txt";
    private static final String SEPARATOR = ":";
    private static final Logger LOGGER = Logger.getLogger(PlantLoader.class.getName());

    /**
     * Loads all plants, restoring their discovery status from a save file if available.
     *
     * @return A list of initialized plants.
     */
    public List<PlantImpl> loadPlants() {
        final DataMemory memory = new DataMemoryImpl();
        String loadedData = null;

        try {
            loadedData = memory.load(FILE_NAME);
        } catch (final IOException e) {
            LOGGER.log(Level.WARNING, "Couldn't load encyclopedia file", e);
        }

        if (loadedData != null && !loadedData.isEmpty()) {
            loadFromSave(loadedData);
        } else {
            loadDefaults();
            saveCurrentState(memory);
        }

        final List<PlantImpl> plants = new ArrayList<>();
        for (final PlantType type : PlantRegistry.getAll()) {
            plants.add(new PlantImpl(type));
        }

        return plants;
    }

/**
 * Loads and unlocks plant types if discovered in previous sessions.
 * 
 * @param data The string data loaded from the save file, containing unlocked plant names separated by SEPARATOR.
 */
private void loadFromSave(final String data) {
        final String[] unlockedNames = data.split(SEPARATOR);
        for (final String name : unlockedNames) {
            boolean found = false;
            for (final PlantType type : PlantRegistry.getAll()) {
                if (type.getName().equals(name)) {
                    type.unlock();
                    found = true;
                    break;
                }
            }
            if (!found) {
                LOGGER.log(Level.WARNING, "Invalid plant name: " + name);
            }
        }
    }

    /**
     * Sets the initial discovery state.
     */
    private void loadDefaults() {
        PlantRegistry.CARROT.unlock();
    }

    /**
     * Saves the current discovered status to the persistent file.
     * 
     * @param memory The DataMemory instance used to save the data.
     */
    private void saveCurrentState(final DataMemory memory) {
        final StringBuilder sb = new StringBuilder();
        for (final PlantType type : PlantRegistry.getAll()) {
            if (type.isDiscovered()) {
                sb.append(type.getName()).append(SEPARATOR);
            }
        }
        try {
            memory.save(FILE_NAME, sb.toString());
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Couldn't save encyclopedia file", e);
        }
    }
}
