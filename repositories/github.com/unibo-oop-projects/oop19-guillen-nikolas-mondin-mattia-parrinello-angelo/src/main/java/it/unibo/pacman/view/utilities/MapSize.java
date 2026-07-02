package it.unibo.pacman.view.utilities;

/**
 * Map size options for the editor.
 *
 */
public enum MapSize {
    /**
     * Standard map size.
     */
    STANDARD("19x17", 19, 17),
    /**
     * Small map size.
     */
    SMALL("15x17", 15, 17),
    /**
     * Large map size.
     */
    LARGE("15x29", 15, 29);

    private String size;
    private int height;
    private int width;

    MapSize(final String size, final int height, final int width) {
        this.size = size;
        this.height = height;
        this.width = width;
    }

    /**
     * 
     * @return the ROWSxCOLUMNS size of the map
     */
    public String getSize() {
        return this.size;
    }

    /**
     * 
     * @return the height of the map
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * 
     * @return the width of the map
     */
    public int getWidth() {
        return this.width;
    }

    public static MapSize getEnumBySize(final String size) {
        if (size.equalsIgnoreCase(SMALL.toString())) {
            return SMALL;
        } else if (size.equalsIgnoreCase(LARGE.toString())) {
            return LARGE;
        } else {
            return STANDARD;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.size;
    }

}
