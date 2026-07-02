package unibo.exiled.model.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.utilities.Position;

/**
 * Implementation of the GameMap interface representing a game map with cells of
 * various types.
 * The map has specific dimensions, height, and width.
 */
public final class GameMapImpl implements GameMap {
    private final int size;
    private final Map<Position, CellType> cellStates;
    private final Map<CellType, Position> cellCorners;

    /**
     * Constructs a GameMapImpl with a specified size.
     *
     * @param size The size of the map, should be an even number.
     * @throws IllegalArgumentException if the size of the map is not an even
     *                                  number.
     */
    public GameMapImpl(final int size) {
        if (size % 2 == 0) {
            this.size = size;
            this.cellStates = new HashMap<>();
            //The corners are always 4
            this.cellCorners = new HashMap<>(4);
            final int startingSize = size / 2 - ConstantsAndResourceLoader.STARTING_SAFEZONE_SIZE;
            this.fillCells(startingSize);
        } else {
            throw new IllegalArgumentException("The size of the map should be an even number.");
        }
    }

    /**
     * Fills the specified cell range with the given cell type.
     *
     * @param type  The type of cell to fill the range with.
     * @param fromY The starting Y coordinate of the range.
     * @param toY   The ending Y coordinate of the range.
     * @param fromX The starting X coordinate of the range.
     * @param toX   The ending X coordinate of the range.
     */
    private void fillCellRange(final CellType type, final int fromY, final int toY, final int fromX, final int toX) {
        for (int i = fromY; i < toY; i++) {
            for (int j = fromX; j < toX; j++) {
                final Position pos = new Position(j, i);
                if (type.equals(CellType.PLAINS)) {
                    this.cellStates.replace(pos, type);
                } else {
                    this.cellStates.put(pos, type);
                }
            }
        }
    }

    /**
     * Fills the cells of the map with different cell types based on a starting area
     * size.
     *
     * @param startingArea The size of the starting area where CellType.PLAINS is
     *                     filled.
     */
    private void fillCells(final int startingArea) {
        // Random generation of the game map
        final List<CellType> cellTypes = new ArrayList<>(List.of(CellType.values()));
        cellTypes.remove(CellType.PLAINS); // Removed CellType.PLAINS from cellTypes because it's the starting player
        // spawn area.
        Collections.shuffle(cellTypes);

        this.fillCellRange(cellTypes.get(0), 0, this.size / 2, 0, this.size / 2);
        this.cellCorners.put(cellTypes.get(0), new Position(0, 0));
        this.fillCellRange(cellTypes.get(1), this.size / 2, this.size, 0, this.size / 2);
        this.cellCorners.put(cellTypes.get(1), new Position(0, this.size - 1));
        this.fillCellRange(cellTypes.get(2), 0, this.size / 2, this.size / 2, this.size);
        this.cellCorners.put(cellTypes.get(2), new Position(this.size - 1, 0));
        this.fillCellRange(cellTypes.get(3), this.size / 2, this.size, this.size / 2, this.size);
        this.cellCorners.put(cellTypes.get(3), new Position(this.size - 1, this.size - 1));
        this.fillCellRange(CellType.PLAINS, startingArea, this.size - startingArea, startingArea,
                this.size - startingArea);
    }

    @Override
    public Map<Position, CellType> getCellStates() {
        return Collections.unmodifiableMap(this.cellStates);
    }

    @Override
    public Position getCornerPositionOfElement(final CellType type) {
        return this.cellCorners.get(type);
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
