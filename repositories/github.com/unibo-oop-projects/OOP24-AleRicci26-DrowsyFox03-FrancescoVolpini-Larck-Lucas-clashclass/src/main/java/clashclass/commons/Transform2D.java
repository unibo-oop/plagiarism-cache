package clashclass.commons;

import clashclass.ecs.AbstractComponent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents a Transform in a two-dimensional space.
 */
public class Transform2D extends AbstractComponent implements PositionChangeObservable {
    private Vector2D position;
    private Vector2D rotation;
    private Vector2D scale;
    private final List<PositionChangeObserver> positionObservers = new CopyOnWriteArrayList<>();

    /**
     * Constructs the Transform.
     *
     * @param position the initial position
     * @param rotation the initial rotation
     * @param scale the initial scale
     */
    public Transform2D(final Vector2D position, final Vector2D rotation, final Vector2D scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    /**
     * Constructs the Transform with a position and default rotation and scale.
     *
     * @param position the initial position
     */
    public Transform2D(final Vector2D position) {
        this(position, Vector2D.zero(), Vector2D.one());
    }

    /**
     * Constructs a default Transform.
     */
    public Transform2D() {
        this(Vector2D.zero(), Vector2D.one(), Vector2D.zero());
    }

    /**
     * Gets the position component.
     *
     * @return the position
     */
    public Vector2D getPosition() {
        return this.position;
    }

    /**
     * Gets the rotation component.
     *
     * @return the rotation
     */
    public Vector2D getRotation() {
        return this.rotation;
    }

    /**
     * Gets the scale component.
     *
     * @return the scale
     */
    public Vector2D getScale() {
        return this.scale;
    }

    /**
     * Sets the position component.
     *
     * @param position the new position value
     */
    public void setPosition(final Vector2D position) {
        final Vector2D oldPosition = this.position;
        this.position = position;
        notifyPositionChange(oldPosition, position);
    }

    /**
     * Sets the rotation component.
     *
     * @param rotation the new rotation value
     */
    public void setRotation(final Vector2D rotation) {
        this.rotation = rotation;
    }

    /**
     * Sets the scale component.
     *
     * @param scale the new scale value
     */
    public void setScale(final Vector2D scale) {
        this.scale = scale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPositionChangeObserver(final PositionChangeObserver observer) {
        positionObservers.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePositionChangeObserver(final PositionChangeObserver observer) {
        positionObservers.remove(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyPositionChange(final Vector2D oldPosition, final Vector2D newPosition) {
        for (final PositionChangeObserver observer : positionObservers) {
            observer.onPositionChanged(getGameObject(), oldPosition, newPosition);
        }
    }
}
