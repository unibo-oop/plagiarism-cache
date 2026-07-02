package util.rectangle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import util.Coordinates;

/**
 * 
 *An implementation of Rectangle interface.
 * @param <X> the type of the element of the rectangle
 */
public class RectangleImpl<X> implements Rectangle<X> {

    private final Map<Coordinates, X> grid;
    private final int height;
    private final int width;

    /**
     * 
     * @param height the height of the rectangle
     * @param width the width of the rectangle
     * @param base the function that defines the element that will be put in each position of the rectangle
     */
    public RectangleImpl(final int height, final int width, final Function<Coordinates, X> base) {
        this.grid = new HashMap<>();
        this.height = height;
        this.width = width;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Coordinates cords = new Coordinates(i, j);
                this.grid.put(cords, base.apply(cords));
            }
        }
    }

    private boolean coordiantesOutOfRange(final Coordinates position) {
        if (position.getX() >= width || position.getX() < 0 || position.getY() >= height || position.getY() < 0) {
            return true;
        }
        return false;
    }

    /**{@inheritDoc}**/@Override
    public X get(final Coordinates position) {
        if (coordiantesOutOfRange(position)) {
            throw new IllegalArgumentException();
        }
        return this.grid.get(position);
    }

    /**{@inheritDoc}**/@Override
    public void set(final Coordinates position, final X element) {
        if (coordiantesOutOfRange(position)) {
            throw new IllegalArgumentException();
        }
        this.grid.put(position, element);
    }

    /**{@inheritDoc}**/@Override
    public void setSubrectangle(final Coordinates edgePosition, final Rectangle<X> subrectangle) {
        if (coordiantesOutOfRange(edgePosition)
                || coordiantesOutOfRange(new Coordinates(edgePosition.getX() + subrectangle.width(),
                        edgePosition.getY() + subrectangle.height()))) {
            throw new IllegalArgumentException();
        }
        subrectangle.toMap().entrySet().forEach(e -> this.set(Coordinates.sum(e.getKey(), edgePosition), e.getValue()));
    }

    /**{@inheritDoc}**/@Override
    public int height() {
        return this.height;
    }

    /**{@inheritDoc}**/@Override
    public int width() {
        return this.width;
    }

    /**{@inheritDoc}**/@Override
    public Map<Coordinates, X> toMap() {
        return new HashMap<>(this.grid);
    }

    /**{@inheritDoc}**/@Override
    public Rectangle<X> getSubrectangle(final Coordinates edge, final int height, final int width) {
        Rectangle<X> result = new RectangleImpl<X>(height, width, (p) -> this.get(p));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Coordinates cords = new Coordinates(i, j);
                result.set(cords, this.get(Coordinates.sum(cords, edge)));
            }
        }
        return result;
    }

}
