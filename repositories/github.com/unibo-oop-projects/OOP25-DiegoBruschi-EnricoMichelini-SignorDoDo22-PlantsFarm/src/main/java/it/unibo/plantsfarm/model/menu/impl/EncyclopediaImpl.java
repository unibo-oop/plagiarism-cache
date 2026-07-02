package it.unibo.plantsfarm.model.menu.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import it.unibo.plantsfarm.controller.memory.api.DataMemory;
import it.unibo.plantsfarm.controller.memory.impl.DataMemoryImpl;
import it.unibo.plantsfarm.model.menu.api.Encyclopedia;
import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.plant.PlantRegistry;

/**
 * Represents the encyclopedia containing information about all plants in the game.
 * It shows only unlocked plants by the player.
 */
public final class EncyclopediaImpl implements Encyclopedia {

    private static final String FILE_NAME = "encyclopedia.txt";
    private static final String SEPARATOR = ":";
    private static final Logger LOGGER = Logger.getLogger(EncyclopediaImpl.class.getName());

    private final List<PlantImpl> plants;
    private final DataMemory memory;

    /**
     * Creates a new empty Encyclopedia.
     */
    public EncyclopediaImpl() {
        this.plants = new ArrayList<>();
        this.memory = new DataMemoryImpl();
    }

    /**
     * Saves the current unlocked plants list to file.
     */
    @Override
    public void save() {
        final StringBuilder sb = new StringBuilder();
        for (final PlantType type : PlantRegistry.getAll()) {
            if (type.isDiscovered()) {
                sb.append(type.getName()).append(SEPARATOR);
            }
        }
        try {
            this.memory.save(FILE_NAME, sb.toString());
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving encyclopedia", e);
        }
    }

    /**
     * Resets encyclopedia.
     */
    @Override
    public void reset() {
        for (final PlantType type : PlantRegistry.getAll()) {
            type.lock();
        }
        PlantRegistry.CARROT.unlock();
        save();
    }

    /**
     * Gets all plants in the encyclopedia.
     *
     * @return unmodifiable list of plants
     */
    @Override
    public List<PlantImpl> getPlants() {
        return Collections.unmodifiableList(plants);
    }

    /**
     * Adds a plant to the encyclopedia if it's not already present.
     *
     * @param plant The plant to add.
     */
    @Override
    public void addPlant(final PlantImpl plant) {
        if (!plants.contains(plant)) {
            plants.add(plant);
        }
    }

    /**
     * Returns the total number of plants in the encyclopedia.
     *
     * @return The number of plants.
     */
    @Override
    public int numberPlants() {
        return plants.size();
    }

    /**
     * Unlocks all plants in the encyclopedia.
     */
    @Override
    public void unlockAll() {
        for (final PlantType type : PlantRegistry.getAll()) {
            type.unlock();
        }
        save();
    }

    /**
     * Filters and returns only edible plants that have been discovered.
     *
     * @return A list of unlocked edible plants.
     */
    @Override
    public List<PlantImpl> getUnlockedEdiblePlants() {
        final List<PlantImpl> unlockedEdiblePlantsList = new ArrayList<>();
        for (final PlantImpl plant : plants) {
            if (plant.isEdible() && plant.isDiscovered()) {
                unlockedEdiblePlantsList.add(plant);
            }
        }
        return unlockedEdiblePlantsList;
    }

    /**
     * Counts the number of discovered edible plants.
     *
     * @return The number of discovered edible plants.
     */
    @Override
    public int getNumberUnlockedEdiblePlants() {
        int total = 0;
        for (final PlantImpl plant : plants) {
            if (plant.isEdible() && plant.isDiscovered()) {
                total++;
            }
        }
        return total;
    }

    /**
     * Reads the description of a plant from a resource file.
     *
     * @param type The type of the plant.
     *
     * @return The description string or a default message if not found.
     */
    @Override
    public String getPlantDescription(final PlantType type) {
        final String fileName = type.getName().toUpperCase(Locale.ROOT) + ".txt";
        final String path = "encyclopediaFiles/" + fileName;
        final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

        if (inputStream == null) {
            return "Description not available for " + type.getName();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (final IOException e) {
            LOGGER.log(Level.WARNING, "Error reading description for " + type.getName(), e);
            return "Error loading description.";
        }
    }

}
