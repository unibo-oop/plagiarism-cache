package it.unibo.modularcheckers.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Interface for the Chessboard class.
 */

public interface Chessboard extends Serializable {

    /**
     * Get the Block in the coordinate.
     * 
     * @param coordinate the coordinate where get the block.
     * @return the block in the coordinate.
     */
    Block getBlock(Coordinate coordinate);

    /**
     * Get the size of the chessboard.
     * 
     * @return a pair with the x and y components of the chessboard size.
     */
    Pair<Integer, Integer> getSize();

    /**
     * Get all the blocks of the chessboard.
     * 
     * @return the unmodifiable map of the blocks.
     */
    Map<Coordinate, Block> getBlocks();

    /**
     * Remove all the pieces of the chessboard.
     */
    void reset();
}
