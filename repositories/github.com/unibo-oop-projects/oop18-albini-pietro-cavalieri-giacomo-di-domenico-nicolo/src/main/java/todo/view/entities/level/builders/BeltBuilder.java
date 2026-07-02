package todo.view.entities.level.builders;

import com.badlogic.gdx.math.Vector2;

import todo.view.entities.EntityBuilder;
import todo.view.entities.level.Belt;
import todo.view.rooms.AnimationsSpeed;

/**
 * This interface represents a builder to create {@link Belt} objects.
 *
 * @param <S> is the self type
 * @param <E> is the type of belt to be built
 */
public interface BeltBuilder<S extends BeltBuilder<? extends S, E>, E extends Belt> extends EntityBuilder<S, E> {
    /**
     * Set the end position of the belt, where the value boxes will stop.
     *
     * @param endPosition is the end position of the belt
     * @return the builder
     */
    S endPosition(Vector2 endPosition);

    /**
     * Set the boundaries between the box and the belt, and between two boxes.
     *
     * @param boxToBeltHorizontalMargin is the distance between the left border of
     *            the box and the left border of the belt
     * @param boxToBoxVerticalMargin is the distance between the lower border of the
     *            box and the lower border of the next one
     * @return the builder
     */
    S valueBoxBoundaries(float boxToBeltHorizontalMargin, float boxToBoxVerticalMargin);

    /**
     * @param speed the animation speed to use for this belt
     * @return the builder
     */
    S animationsSpeed(AnimationsSpeed speed);
}
