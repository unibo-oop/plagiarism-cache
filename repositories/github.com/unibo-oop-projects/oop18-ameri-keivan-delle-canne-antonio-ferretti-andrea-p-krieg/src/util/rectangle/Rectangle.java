package util.rectangle;

import java.util.Map;

import util.Coordinates;

/**
 * This is a rectangle with in it generic elements instead of points, it's a grid
 * on which each coordinate has an associated value.
 * 
 * @param <X> the type of the elements of the rectangle
 */
public interface Rectangle<X> {
    /**
     * 
     * @param position the position of the element to get
     * @return the element with the specified coordinates
     * @throws IllegalArgumentException if the position is out of range
     */
    X get(Coordinates position);

    /**
     * 
     * @param position the position in which to set the element
     * @param element  the element to set
     * @throws IllegalArgumentException if the position is out of bound
     */
    void set(Coordinates position, X element);

    /**
     * @param edgePosition the position of the up left corner of the subrectangle in
     *                     the rectangle
     * @param subrectangle the subrectangle to set on the rectangle
     * @throws IllegalArgumentException if the subrectangle doesn't fit in the
     *                                  passed position
     */
    void setSubrectangle(Coordinates edgePosition, Rectangle<X> subrectangle);

    /**
     * @param width  the width of the subrectangle to return
     * @param height the height of the subrectangle to return
     * @param edge   the position of the up left corner
     * @return the subrectangle in the specified position
     * @throws IllegalArgumentException if the edges are out of bound
     */
    Rectangle<X> getSubrectangle(Coordinates edge, int height, int width);

    /**
     * 
     * @return the height of the rectangle
     */
    int height();

    /**
     * 
     * @return the width of the rectangle
     */
    int width();

    /**
     * 
     * @return the map transposition of the rectangle
     */
    Map<Coordinates, X> toMap();
}
