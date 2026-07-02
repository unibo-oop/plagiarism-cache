package unibo.exiled.model.map;

import unibo.exiled.utilities.Position;

import java.util.Map;

/**
 * Represents a game map with cells of various types.
 * The map has specific dimensions, height, and width.
 */
public interface GameMap {
    /**
     * Returns the size of the map.
     *
     * @return An Integer representing the size of the map.
     */
    int getSize();

    /**
     * Gets the cells of the map.
     *
     * @return An unmodifiable map of positions and CellTypes to be used from the model.
     */
    Map<Position, CellType> getCellStates();

    /**
     * Gets the corner of the map of the selected type.
     *
     * @param type The desired corner cell type.
     * @return The position of the Cell in the corner of the selected type.
     */
    Position getCornerPositionOfElement(CellType type);
}
