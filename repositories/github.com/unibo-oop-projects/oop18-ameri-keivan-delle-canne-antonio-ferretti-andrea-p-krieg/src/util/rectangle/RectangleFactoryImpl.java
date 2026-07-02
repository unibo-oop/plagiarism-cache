package util.rectangle;

import java.util.function.Function;

import util.Coordinates;

/**
 *Implementation of RectangleFactory.
 *
 * @param <X> the type of the generated Rectangle
 */
public class RectangleFactoryImpl<X> implements RectangleFactory<X> {
    /**{@inheritDoc}**/@Override
    public Rectangle<X> getFromElement(final int height, final int width, final Function<Coordinates, X> base) {
        return new RectangleImpl<X>(height, width, base);
    }
}
