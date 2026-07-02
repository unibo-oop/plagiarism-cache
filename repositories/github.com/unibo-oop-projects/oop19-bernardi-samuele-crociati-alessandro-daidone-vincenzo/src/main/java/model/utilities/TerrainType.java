package model.utilities;

public enum TerrainType {
    /**
     * Defines EARTH.
     */
    EARTH(Constants.DEFAULT_EARTH_TEXT, Constants.DEFAULT_EARTH_VALUE),
    /**
     * Defines MOUNTAIN.
     */
    MOUNTAIN(Constants.DEFAULT_MOUNTAIN_TEXT, Constants.DEFAULT_MOUNTAIN_VALUE),
    /**
     * Defines WATER.
     */
    WATER(Constants.DEFAULT_WATER_TEXT, Constants.DEFAULT_WATER_VALUE);

    private final int terrainCode;
    private final String text;

    TerrainType(final String text, final int i) {
        this.text = text;
        this.terrainCode = i;
    }

    /**
     * Returns the enumerator corresponding to the integer passed.
     * 
     * @param x
     *            the integer passed
     * @return the enumerator value associated to the integer value
     */
    public static TerrainType fromInteger(final int x) {
        switch (x) {
        case Constants.DEFAULT_EARTH_VALUE:
            return TerrainType.EARTH;
        case Constants.DEFAULT_MOUNTAIN_VALUE:
            return TerrainType.MOUNTAIN;
        case Constants.DEFAULT_WATER_VALUE:
            return TerrainType.WATER;
        default:
            return TerrainType.EARTH;
        }
    }

    /**
     * @return the terrainCode
     */
    public int getTerrainCode() {
        return this.terrainCode;
    }

    /**
     * Returns a string representation of the enumerator.
     * 
     * @return the string representing the enumerator.
     */
    @Override
    public String toString() {
        return this.text;
    }

    private static class Constants {
        public static final int DEFAULT_EARTH_VALUE = 0;
        public static final int DEFAULT_MOUNTAIN_VALUE = 1;
        public static final int DEFAULT_WATER_VALUE = 2;
        public static final String DEFAULT_EARTH_TEXT = "Earth";
        public static final String DEFAULT_MOUNTAIN_TEXT = "Mountain";
        public static final String DEFAULT_WATER_TEXT = "Water";
    }
}
