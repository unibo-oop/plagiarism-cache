/**
 * 
 */
package model.entities;

import model.utilities.TerrainType;

/**
 * Enumeration that represents how the hero moves.
 */
public enum MovementType {
    /**
     * Represents an Entity that cannot move.
     */
    NONE(Constants.DEFAULT_NONE_TEXT, Constants.DEFAULT_NONE_VALUE),
    /**
     * Represents a flying hero.
     */
    FLYING(Constants.DEFAULT_FLYING_TEXT, Constants.DEFAULT_FLYING_VALUE),
    /**
     * Represents a hero that walks by feet.
     */
    FEET(Constants.DEFAULT_FEET_TEXT, Constants.DEFAULT_FEET_VALUE);

    private final String text;
    private final int range;

    MovementType(final String text, final int range) {
        this.text = text;
        this.range = range;
    }

    /**
     * Returns the enumerator corresponding to the integer passed.
     * 
     * @param x
     *            the integer passed
     * @return the enumerator value associated to the integer value
     */
    public static MovementType fromInteger(final int x) {
        switch (x) {
        case Constants.DEFAULT_NONE_VALUE:
            return MovementType.NONE;
        case Constants.DEFAULT_FLYING_VALUE:
            return MovementType.FLYING;
        case Constants.DEFAULT_FEET_VALUE:
            return MovementType.FEET;
        default:
            return MovementType.FEET;
        }
    }

    /**
     * 
     * @param terrainType
     *            the terrain type
     * @return can step on terrain type
     */
    public boolean canStepOn(final TerrainType terrainType) {
        if (this.equals(MovementType.FLYING)) {
            return true;
        } else if (this.equals(MovementType.FEET)) {
            return terrainType.equals(TerrainType.EARTH);
        }
        return false;

    }

    @Override
    public String toString() {
        return this.text + "(" + Integer.toString(this.range) + ")";
    }

    /**
     * Getter for the movement range.
     * 
     * @return the range
     */
    public int getRange() {
        return this.range;
    }

    public static class Constants {
        /**
         * 
         */
        public static final int DEFAULT_NONE_VALUE = 0;
        /**
         * 
         */
        public static final int DEFAULT_FLYING_VALUE = 2;
        /**
         * 
         */
        public static final int DEFAULT_FEET_VALUE = 4;
        /**
         * 
         */
        public static final String DEFAULT_NONE_TEXT = "None";
        /**
         * 
         */
        public static final String DEFAULT_FLYING_TEXT = "Flying";
        /**
         * 
         */
        public static final String DEFAULT_FEET_TEXT = "Feet";
    }
}
