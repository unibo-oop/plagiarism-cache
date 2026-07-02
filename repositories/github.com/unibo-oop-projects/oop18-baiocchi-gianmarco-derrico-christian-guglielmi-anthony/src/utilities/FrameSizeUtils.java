package utilities;

import java.awt.Toolkit;

/**
 *
 *
 */
public final class FrameSizeUtils {

    private static final int EDGE_LENGTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
    /**
     * Gets the number of tiles for any edge.
     */
    public static final int NUM_TILES = 15;

    private FrameSizeUtils() {
    }

    /**
     * Gets the correct edge length according to num of tiles.
     * @return the correct length of the edge
     */
    public static int getEdgeLength() {
        final int rest = EDGE_LENGTH % NUM_TILES;
        if (rest != 0) {
            return EDGE_LENGTH - rest;
        }
        return EDGE_LENGTH;
    }
}
