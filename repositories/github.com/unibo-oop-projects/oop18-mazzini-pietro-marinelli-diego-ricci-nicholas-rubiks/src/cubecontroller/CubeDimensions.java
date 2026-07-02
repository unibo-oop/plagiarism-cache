package cubecontroller;

/**
 * Enumerator containing cube dimensions (3X3, 4X4...); this makes the code more extendible.
 */
public enum CubeDimensions {

    /**
     * Cube dimension 3X3X3.
     */
    Cube3X3(3);

    private final int dimension;

    CubeDimensions(final int dimension) {
        this.dimension = dimension;
    }

    /**
     * Dimension getter.
     * @return Cube dimension.
     */
    public int getDimension() {
        return this.dimension;
    }
}
