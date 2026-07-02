package it.unibo.modularcheckers.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Chessboard implementation.
 */

public class ChessboardImpl implements Chessboard {

    /**
     * 
     */
    private static final long serialVersionUID = -9101326225025439206L;

    /**
     * Default width of the Chessboard.
     */
    public static final int DEFAULT_WIDTH = 8;

    /**
     * Default height of the Chessboard.
     */
    public static final int DEFAULT_HEIGHT = 8;

    private final Map<Coordinate, Block> map = new HashMap<>();
    private final int width;
    private final int height;

    /**
     * Initialize the map with the parameterised dimension.
     * 
     * @param width  width of the Chessboard
     * @param height height of the Chessboard
     */
    public ChessboardImpl(final int width, final int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Cannot create a Chessboard with width or height <= 0!");
        }

        this.width = width;
        this.height = height;

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                final Coordinate key = new Coordinate(w, h);
                final Block value = new BlockImpl();
                this.map.put(key, value);
            }

        }
    }

    /**
     * Initialize the map with the parameterised dimension.
     */
    public ChessboardImpl() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Block getBlock(final Coordinate coordinate) {
        if (this.map.containsKey(coordinate)) {
            return this.map.get(coordinate);
        }
        throw new IllegalArgumentException("Block not found");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getSize() {
        return new Pair<>(this.width, this.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Coordinate, Block> getBlocks() {
        return Collections.unmodifiableMap(this.map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        for (final Block b : map.values()) {
            b.removePiece();
        }
    }
}
