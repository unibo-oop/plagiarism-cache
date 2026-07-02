package org.hsm.model;

/**
 *
 * ENUM Greenhouse types.
 *
 */
public enum GreenHouseType {
    /**
     * Type of disposition of the plants in the greenhouse.
     *
     */
    LINEAR("Linear"), GRID("Grid"), CIRCULAR("Circular"), PYRAMIDAL("Pyramidal");

    private final String name;

    GreenHouseType(final String n) {
        this.name = n;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
