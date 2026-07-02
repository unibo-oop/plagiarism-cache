package cubevisual;

import javafx.scene.paint.Color;
/**
 * This enumerator contains centers coordinates (row, column, depth) and colors of 3X3 Rubik Cube.
 */
public enum CentersCoords3X3 implements CentersCoords {

    /**
     * Green face, left.
     */
    GREEN(1, 0, 1, Color.GREEN),
    /**
     * White face, top.
     */
    WHITE(0, 1, 1, Color.WHITE),
    /**
     * Blue face, right. 
     */
    BLUE(1, 2, 1, Color.BLUE),
    /**
     * Red face, front.
     */
    RED(1, 1, 0, Color.RED),
    /**
     * Yellow face, bottom. 
     */
    YELLOW(2, 1, 1, Color.YELLOW),
    /**
     * Orange face, back. 
     */
    ORANGE(1, 1, 2, Color.ORANGE);

    private final int rows;
    private final int cols;
    private final int depth;
    private final Color color;

    CentersCoords3X3(final int rows, final int cols, final int depth, final Color color) {
        this.rows = rows;
        this.cols = cols;
        this.depth = depth;
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRows() {
        return this.rows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCols() {
        return this.cols;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDepth() {
        return this.depth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return this.color;
    }
}
