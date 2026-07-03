package it.oop.project.util;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;

/**
 * {@inheritDoc}
 */
public class SquareMatrixImpl implements SquareMatrix, Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
    private int size;
    private List<List<Optional<Object>>> matrix;

    /**
     * Constructs an empty SquareMatrix with specified size.
     * 
     * @param size
     *            size of the matrix.
     */
    public SquareMatrixImpl(final int size) {
        this.size = size;
        this.reset();
    }

    private void reset() {
        this.matrix = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            List<Optional<Object>> row = new ArrayList<>();
            for (int j = 0; j < this.size; j++) {
                row.add(Optional.absent());
            }
            this.matrix.add(row);
        }
    }

    private boolean checkCoordinates(Point coordinates) {
        double x = coordinates.getX();
        double y = coordinates.getY();
        if ((x >= this.size) || (x < 0)) {
            return false;
        } else if ((y >= this.size) || (y < 0)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkDirection(final Direction direction) {
        return (!direction.equals(Direction.UP))
                && (!direction.equals(Direction.DOWN));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(final Point coordinates, final Optional<Object> element)
            throws IndexOutOfBoundsException {
        if (this.checkCoordinates(coordinates)) {
            this.matrix.get((int) coordinates.getX())
                    .set((int) coordinates.getY(), element);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeRow(final int row, final List<Optional<Object>> rowValues)
            throws IndexOutOfBoundsException {
        if (this.checkCoordinates(new Point(row, 0))) {
            for (int col = 0; col < this.size; col++) {
                this.write(new Point(row, col), rowValues.get(col));
            }
        } else {
            throw new IndexOutOfBoundsException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Object> get(final Point coordinates)
            throws IndexOutOfBoundsException {
        if (this.checkCoordinates(coordinates)) {
            return this.matrix.get((int) coordinates.getX())
                    .get((int) coordinates.getY());
        } else {
            throw new IndexOutOfBoundsException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Optional<Object>> getRow(final int row)
            throws IndexOutOfBoundsException {
        if (this.checkCoordinates(new Point(row, 0))) {
            return new ArrayList<>(this.matrix.get(row));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SquareMatrix clone() throws CloneNotSupportedException {
        return (SquareMatrixImpl) super.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty(final Point coordinates)
            throws IndexOutOfBoundsException {
        if (this.checkCoordinates(coordinates)) {
            return !this.get(coordinates).isPresent();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmptyRow(final int row) throws IndexOutOfBoundsException {
        if (this.checkCoordinates(new Point(row, 0))) {
            for (int i = 0; i < this.size; i++) {
                if (!this.isEmpty(new Point(row, i))) {
                    return false;
                }
            }
            return true;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotate90Degrees(final Direction direction)
            throws IllegalArgumentException {
        if (checkDirection(direction)) {

            SquareMatrixImpl rotationMatrix = new SquareMatrixImpl(this.size);
            for (int row = 0; row < this.size; row++) {
                for (int col = 0; col < this.size; col++) {
                    Point coordinates = new Point(row, col);
                    if (direction.equals(Direction.LEFT)) {
                        rotationMatrix.write(rotateCoordinates(Direction.LEFT,
                                coordinates, this.size), this.get(coordinates));
                    } else {
                        rotationMatrix.write(rotateCoordinates(Direction.RIGHT,
                                coordinates, this.size), this.get(coordinates));
                    }
                }
            }
            this.matrix = rotationMatrix.matrix;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotate180Degrees() {
        this.rotate90Degrees(Direction.LEFT);
        this.rotate90Degrees(Direction.LEFT);
    }

    /**
     * Returns rotated coordinates in the specified direction in a square matrix
     * of specified size length.
     * 
     * @param direction
     *            rotation direction.
     * @param coordinates
     *            coordinates to rotate.
     * @param size
     *            size of square matrix.
     * @return rotated coordinates.
     * @throws IllegalArgumentException
     *             if direction UP or DOWN.
     */
    public static Point rotateCoordinates(final Direction direction,
            final Point coordinates, final int size)
            throws IllegalArgumentException {
        if (checkDirection(direction)) {
            if (direction.equals(Direction.LEFT)) {
                return new Point((int) (size - coordinates.getY() - 1),
                        (int) coordinates.getX());
            } else {
                return new Point((int) coordinates.getY(),
                        (int) (size - coordinates.getX() - 1));
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns rotated list of coordinates in the specified direction in a
     * square matrix of specified size length.
     * 
     * @param direction
     *            rotation direction.
     * @param coordinates
     *            list of coordinates to rotate.
     * @param size
     *            size of square matrix.
     * @return rotated coordinates.
     * @throws IllegalArgumentException
     *             if direction UP or DOWN.
     */
    public static List<Point> rotateCoordinatesList(Direction direction,
            List<Point> list, int size) throws IllegalArgumentException {
        if (direction.equals(Direction.LEFT)) {
            for (int i = 0; i < list.size(); i++) {
                list.set(i,
                        rotateCoordinates(Direction.LEFT, list.get(i), size));
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                list.set(i,
                        rotateCoordinates(Direction.RIGHT, list.get(i), size));
            }
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matrix == null) ? 0 : matrix.hashCode());
        result = prime * result + size;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SquareMatrixImpl other = (SquareMatrixImpl) obj;
        if (matrix == null) {
            if (other.matrix != null)
                return false;
        } else if (!matrix.equals(other.matrix))
            return false;
        if (size != other.size)
            return false;
        return true;
    }

}
