package org.hsm.view.enumeration;

import java.util.LinkedList;
import java.util.List;

/**
 * The enum represents all the optimal characteristics of the plant.
 *
 */
public enum PlantModelCharacteristics {

    /**
     * The traditional name of the plant.
     */
    NAME("Name"),
    /**
     * The botanical name of the plant.
     */
    BOTANICAL_NAME("Botanical Name"),
    /**
     * The optimal ph of the plant.
     */
    PH("ph"),
    /**
     * The optimal brightness for the plant.
     */
    BRIGHTNESS("Brightness (lm)"),
    /**
     * The optimal growth time for the plant.
     */
    GROWTH_TIME("Growth Time (days)"),
    /**
     * The days of life of the plant.
     */
    LIFE("Life (days)"),
    /**
     * The plant size.
     */
    SIZE("Size (cm2)"),
    /**
     * The conductivity optimal level for the plant.
     */
    CONDUCTIVITY("Conductivity (cf)"),
    /**
     * The temperature of the plant.
     */
    TEMPERATURE("Temperature (°C)");

    private final String name;

    PlantModelCharacteristics(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Get a list with the names of all the features.
     * 
     * @return a list with the names of all the features
     */
    public static List<String> getNameList() {
        final List<String> list = new LinkedList<>();
        for (final PlantModelCharacteristics elem : PlantModelCharacteristics.values()) {
            list.add(elem.toString());
        }
        return list;
    }

}
