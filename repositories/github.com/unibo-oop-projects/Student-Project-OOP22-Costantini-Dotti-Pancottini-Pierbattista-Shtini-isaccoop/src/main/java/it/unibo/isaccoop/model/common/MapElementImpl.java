package it.unibo.isaccoop.model.common;
import java.util.Objects;

import it.unibo.isaccoop.graphics.factory.ConcreteRoomGraphicsComponentFactory;

/**
 * Implementation of {@link MapElement}.
 */
public class MapElementImpl extends AbstractMapElement {

    private final int width;
    private final int height;

    /**
     * MapElementImpl constructor.
     * @param width horizontal dimension of this MapElement
     * @param height vertical dimension of this MapElement
     */
    public MapElementImpl(final int width, final int height) {
        super(width, height, new ConcreteRoomGraphicsComponentFactory().getRoomGraphicsComponent());
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.getCoords(), height, width);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MapElementImpl other = (MapElementImpl) obj;
        return Objects.equals(super.getCoords(), other.getCoords()) && height == other.height && width == other.width;
    }

}
