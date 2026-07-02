package todo.view.entities.level.builders;

import todo.view.entities.EntityBuilder;
import todo.view.entities.level.Floor;

/**
 * This interface represents a builder to create {@link Floor} objects.
 *
 * @param <S> is the self type
 */
public interface FloorBuilder<S extends FloorBuilder<? extends S>> extends EntityBuilder<S, Floor> {
    /**
     * Set the size of the memory area.
     * 
     * @param width is the width of the memory area
     * @param height is the height of the memory area
     * @return the builder
     */
    S size(float width, float height);
}
