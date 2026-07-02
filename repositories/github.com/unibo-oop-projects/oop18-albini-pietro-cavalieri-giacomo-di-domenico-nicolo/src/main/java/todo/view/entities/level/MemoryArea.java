package todo.view.entities.level;

import java.util.List;
import java.util.Optional;

import com.badlogic.gdx.math.Vector2;

import todo.view.entities.Entity;

/**
 * This interface represents a memory area: in this rectangle-shaped zone the
 * player can read and write a {@link ValueBox}.
 */
public interface MemoryArea extends Entity {
    /**
     * @return the number of horizontal slots
     */
    int getHorizontalSlotsCount();

    /**
     * @return the number of vertical slots
     */
    int getVerticalSlotsCount();

    /**
     * @return the total number of slots
     */
    int getSlotsCount();

    /**
     * Get the {@link ValueBox} stored in the specified cell, if present.
     *
     * @param x is the X coordinate of the cell, with 0 on the left
     * @param y is the Y coordinate of the cell, with 0 on the top
     * @return the {@link ValueBox} in the specified cell, if present
     */
    Optional<ValueBox> getValueBox(int x, int y);

    /**
     * Get the {@link ValueBox} stored in the specified cell, if present.
     *
     * @param index is the index of the cell, where 0 is on the top-left corner and
     *            numbers go left to right, top to bottom.
     * @return the {@link ValueBox} in the specified cell, if present
     */
    Optional<ValueBox> getValueBox(int index);

    /**
     * @return the value boxes in the memory area
     */
    List<ValueBox> getValueBoxes();

    /**
     * Set the specified cell to the specified {@link ValueBox}.
     *
     * @param x is the X coordinate of the cell, with 0 on the left
     * @param y is the Y coordinate of the cell, with 0 on the top
     * @param valueBox is the {@link ValueBox} that will be put in the specified
     *            cell
     */
    void setValueBox(int x, int y, ValueBox valueBox);

    /**
     * Set the specified cell to the specified {@link ValueBox}.
     *
     * @param index is the index of the cell, where 0 is on the top-left corner and
     *            numbers go left to right, top to bottom.
     * @param valueBox is the {@link ValueBox} that will be put in the specified
     *            cell
     */
    void setValueBox(int index, ValueBox valueBox);

    /**
     * Empty the specified cell.
     *
     * @param x is the X coordinate of the cell, with 0 on the left
     * @param y is the Y coordinate of the cell, with 0 on the top
     */
    void removeValueBox(int x, int y);

    /**
     * Empty the specified cell.
     *
     * @param index is the index of the cell, where 0 is on the top-left corner and
     *            numbers go left to right, top to bottom
     */
    void removeValueBox(int index);

    /**
     * Get the position of the specified cell.
     *
     * @param x is the X coordinate of the cell, with 0 on the left
     * @param y is the Y coordinate of the cell, with 0 on the top
     * @return the position of the cell
     */
    Vector2 getCellPosition(int x, int y);

    /**
     * Get the position of the specified cell.
     *
     * @param index is the index of the cell, where 0 is on the top-left corner and
     *            numbers go left to right, top to bottom
     * @return the position of the cell
     */
    Vector2 getCellPosition(int index);
}
