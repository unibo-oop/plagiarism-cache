package view.game;

import utility.Position;
/**
 * Interface to manage Slot.
 */
public interface Slot extends Position {

    /**
     * @return Position of Slot
     */
    Position getPosition();
    /**
     * @return Coordinate of X of Slot
     */
    double getXCoord();
    /**
     * @return Coordinate of Y of Slot
     */
    double getYCoord();
    /**
     * @return Rotation of node
     */
    double getRotation();
}
