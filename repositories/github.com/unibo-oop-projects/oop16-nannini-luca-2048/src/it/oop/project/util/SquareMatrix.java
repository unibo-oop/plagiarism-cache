package it.oop.project.util;

import java.awt.Point;
import java.util.List;

import com.google.common.base.Optional;

/**
 * Represents a square matrix of Object type. Cells can be empty.
 * 
 */
public interface SquareMatrix {

    /**
     * Writes an element in the specified position in this matrix.
     * 
     * @param coordinates
     * @param element
     * @throws IndexOutOfBoundsException
     *             if coordinates are out of bounds.
     */
    void write(final Point coordinates, final Optional<Object> element)
            throws IndexOutOfBoundsException;

    /**
     * Writes row values in the specified position in this matrix.
     * 
     * @param row
     *            row index
     * @param rowValues
     * @throws IndexOutOfBoundsException
     *             if row index is out of bounds.
     */
    void writeRow(final int row, final List<Optional<Object>> rowValues)
            throws IndexOutOfBoundsException;

    /**
     * Returns object at the specified position in this matrix.
     * 
     * @param coordinates
     * @return object in coordinates
     * @throws IndexOutOfBoundsException
     *             if coordinates are out of bounds.
     */
    Optional<Object> get(final Point coordinates)
            throws IndexOutOfBoundsException;

    /**
     * Returns the row at the specified position in this matrix.
     * 
     * @param row
     *            row index
     * @return list of row values
     * @throws IndexOutOfBoundsException
     *             if row index is out of bounds.
     */
    List<Optional<Object>> getRow(final int row)
            throws IndexOutOfBoundsException;

    /**
     * Returns the size of this matrix.
     * 
     * @return size
     */
    int getSize();

    /**
     * Returns a copy of this matrix.
     * 
     * @return a clone of this matrix.
     * @throws CloneNotSupportedException
     */
    SquareMatrix clone() throws CloneNotSupportedException;

    /**
     * Indicates if the cell at specified position in this matrix is empty.
     * 
     * @param coordinates
     * @return true if the cell is empty; false otherwise.
     * @throws IndexOutOfBoundsException
     *             if coordinates are out of bounds.
     */
    boolean isEmpty(final Point coordinates) throws IndexOutOfBoundsException;

    /**
     * Indicates if the specified row of this matrix is empty.
     * 
     * @param row
     *            row index
     * @return true if the row is empty; false otherwise.
     * @throws IndexOutOfBoundsException
     *             if row index is out of bounds.
     */
    boolean isEmptyRow(final int row) throws IndexOutOfBoundsException;

    /**
     * Rotates this matrix in the specified direction by 90 degrees.
     * 
     * @param direction
     * @throws IllegalArgumentException
     *             if direction UP or DOWN.
     */
    void rotate90Degrees(final Direction direction)
            throws IllegalArgumentException;

    /**
     * Rotates this matrix by 180 degrees.
     * 
     */
    void rotate180Degrees();

}
