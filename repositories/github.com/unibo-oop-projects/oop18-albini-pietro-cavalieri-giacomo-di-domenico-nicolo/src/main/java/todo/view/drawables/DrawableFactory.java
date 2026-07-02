package todo.view.drawables;

import todo.view.entities.Entity;

/**
 * This interface represents a factory that takes a generic {@link Entity} and
 * creates its corresponding {@link Drawable}.
 */
public interface DrawableFactory {
    /**
     * Create a {@link Drawable} from the given {@link Entity}.
     *
     * @param entity is the entity to be converted to a {@link Drawable}
     * @return the required {@link Drawable}
     */
    Drawable<? extends Entity> fromEntity(Entity entity);
}
