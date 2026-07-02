package util.rectangle;

import java.util.function.Function;

import util.Coordinates;

/**
 * 
 * @param <X> the type of the generated Rectangle
 */
public interface RectangleFactory<X> {
    /**
     * generates a Rectangle of type X.
     * @param height the height
     * @param width the width
     * @param base the function that defines the element that will be put in each position of the rectangle
     * @return the rectangle with the defined characteristics
     */
    Rectangle<X> getFromElement(int height, int width, Function<Coordinates, X> base);
}
