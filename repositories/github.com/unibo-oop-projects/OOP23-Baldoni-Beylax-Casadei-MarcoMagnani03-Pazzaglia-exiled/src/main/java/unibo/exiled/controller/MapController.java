package unibo.exiled.controller;

import unibo.exiled.model.map.CellType;
import unibo.exiled.utilities.Direction;
import unibo.exiled.utilities.Position;

/**
 * Controller for managing the game map.
 */
public interface MapController {
    /**
     * Gets the size of the game map.
     *
     * @return An integer representing the size of the game map.
     */
    int getMapSize();

    /**
     * Gets the cell type at the specified position.
     *
     * @param position The position to check.
     * @return The cell type at the specified position.
     */
    CellType getCellType(Position position);

    /**
     * Checks if there is an enemy in the specified cell.
     *
     * @param position The position to check.
     * @return True if there is an enemy in the cell, false otherwise.
     */
    boolean isEnemyInCell(Position position);

    /**
     * Gets the name of the character in the specified position.
     *
     * @param position The position to check.
     * @return The name of the character in the specified position.
     */
    String getNameOfCharacterInPosition(Position position);

    /**
     * Gets the last direction of the character in the given position.
     *
     * @param position The position where the character is.
     * @return The last direction of the character in the given position.
     */
    Direction getLastDirectionOfCharacterInPosition(Position position);
}
