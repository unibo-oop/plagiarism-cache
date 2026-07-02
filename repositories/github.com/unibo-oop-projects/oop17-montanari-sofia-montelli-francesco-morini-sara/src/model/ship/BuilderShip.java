package model.ship;

import java.util.Optional;

import model.basic_component.StaticPoint2D;

/**
 * Interface that provides the methods to build a ship.
 */
public interface BuilderShip {
    /**
     * set the size of the ship.
     * @param size is the size of the ship to build
     * @throws IllegalArgumentException if the input size is 
     *         grater then the maximum permissible size
     * @throws IllegalStateException if the second coordinate is already present
     */
    void setSizeShip(int size) throws IllegalArgumentException, IllegalStateException;

    /**
     * set the first coordinate of the ship in the battleground.
     * @param coord
     *            is the coordinates of the first point of the ship
     * @throws IllegalStateException
     *             if the second coordinate is already present
     */
    void setFirstCoord(StaticPoint2D coord) throws IllegalStateException;

    /**
     * set the last coordinate of the ship in the battleground.
     * @param coord is the coordinates of the last point of the ship
     * @throws IllegalArgumentException if the coordinate is not 
     *         compatible with the first and with the size
     * @throws IllegalStateException if the first coordinate has not yet been set
     */
    void setSecondCoord(StaticPoint2D coord) throws IllegalArgumentException, IllegalStateException;

    /**
     * @return the size of the ship
     */
    Optional<Integer> getSize();

    /**
     * @return the first coordinate of the ship
     */
    Optional<StaticPoint2D> getFirstCoord();

    /**
     * 
     * @return the last coordinate of the ship
     */
    Optional<StaticPoint2D> getSecondCoord();

    /**
     * @return the ship built
     * @throws IllegalStateException
     *             if one of the fields is not set
     */
    Ship build() throws IllegalStateException;

    /**
     * reset the values of all fields.
     */
    void reset();

    /**
     * reset the coordinates.
     */
    void resetCoord();

    /**
     * reset the value of the second coordinate.
     */
    void removeSecondCoord();

    /**
     * @return if the ship can be builded.
     */
    boolean canBuild();
}
