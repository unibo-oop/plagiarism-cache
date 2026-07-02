package tmw.model.objects;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.common.Rec2D;

/**
 * This Interface is the base for every object in the game and is implemented by
 * any of this.
 * 
 * Any object in the game has a position and a dimension which indicates how
 * many space it takes in the world; through this interface is possible to get
 * or set this informations
 *
 */
public interface GameObject {

    /**
     * This method returns the current position of the object.
     * 
     * @return a {@link P2d} which represents the current position of the object
     */
    P2d getCurrentPos();

    /**
     * This method is used to set a new position to the object, indicates by a
     * {@link P2d}.
     * 
     * @param position - the {@link P2d} which indicates the new location of the
     *                 object
     */
    void setPos(P2d position);

    /**
     * This method returns the boundary of the object using a {@link Rectangle2D}.
     * 
     * @return a {@link Rectangle2D} which represents the boundary of the object
     */
    Rec2D getBoundary();

    /**
     * This method returns the central point of the boundary of the object.
     * 
     * @return a {@link P2d} which represents the central position of the object
     */
    P2d getCentralPosition();

    /**
     * This method returns the dimension of the object.
     * 
     * @return a {@link Dimension2D} which represents the dimension of the object
     */
    Dim2D getDimension();

    /**
     * Setter for the dimension.
     * 
     * @param dimension the new dimension
     */
    void setDimension(Dim2D dimension);

    /**
     * This method sets a new dimension of the object.
     * 
     * @param dimension a {@link Dimension2D} which represents the dimension of the
     *                  object
     */
    void resetDefaultDimension(Dim2D dimension);

    /**
     * This method is used to check if the object intersects the one passed as
     * parameter.
     * 
     * @param object - the object to check the intersection with
     * @return true if the two object intersect each other, false otherwise
     */
    boolean intersect(GameObject object);
}
