package ballblast.model.gameobjects;

import java.util.Collections;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;
import ballblast.model.components.Component;
import ballblast.model.components.ComponentType;
import ballblast.model.physics.Collidable;
import ballblast.model.physics.CollisionHandler;

/**
 * Generic implementation of the {@link GameObject} interface. Defines base
 * behavior that all {@link GameObject}s share.
 *
 */
public abstract class AbstractGameObject implements GameObject {
    private static final Coordinate ZERO = new Coordinate(0, 0);
    private final GameObjectType type;
    private Coordinate position;
    private boolean isAvailable;
    private List<Component> components;
    private double width;
    private double height;
    private Vector2D velocity;
    private CollisionHandler collisionHandler;

    /**
     * Class constructor.
     * 
     * @param type the type of {@link GameObject}.
     */
    protected AbstractGameObject(final GameObjectType type) {
        this.type = type;
        this.isAvailable = true;
        this.components = Collections.emptyList();
        this.position = ZERO;
    }

    @Override
    public final double getWidth() {
        return this.width;
    }

    @Override
    public final double getHeight() {
        return this.height;
    }

    @Override
    public final Vector2D getVelocity() {
        return this.velocity;
    }

    @Override
    public final void setVelocity(final Vector2D velocity) {
        this.velocity = velocity;
    }

    @Override
    public final Coordinate getPosition() {
        return this.position.copy();
    }

    @Override
    public final void setPosition(final Coordinate position) {
        this.position = position;
    }

    @Override
    public final boolean isDestroyed() {
        return !this.isAvailable;
    }

    @Override
    public final void destroy() {
        this.isAvailable = false;
        this.components.forEach(Component::disable);
    }

    /** {@inheritDoc} */
    @Override
    public void update(final double elapsed) {
        components.forEach(c -> c.update(elapsed));
    }

    @Override
    public final void addComponent(final Component component) {
        component.setParent(this);
        this.components = ImmutableList.<Component>builder()
                .addAll(this.components)
                .add(component)
                .build();
    }

    @Override
    public final void removeComponent(final ComponentType type) {
        this.components = this.components.stream()
                .filter(c -> c.getType() != type)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public final List<Component> getComponents() {
        return this.components;
    }

    @Override
    public final GameObjectType getType() {
        return this.type;
    }

    @Override
    public final void handleCollision(final Collidable collidable) {
        this.collisionHandler.execute(collidable, this);
    }

    /**
     * Sets the {@link GameObject}'s height.
     * 
     * @param height the height of the {@link GameObject}.
     */
    protected void setHeight(final double height) {
        this.height = height;
    }

    /**
     * Sets the {@link GameObject}'s width.
     * 
     * @param width the width of the {@link GameObject}.
     */
    protected void setWidth(final double width) {
        this.width = width;
    }

    /**
     * Sets the {@link CollisionHandler}.
     * 
     * @param handler the delegate {@link CollisionHandler} used to handle the
     *                {@link Collision}s.
     */
    protected final void setCollisionHandler(final CollisionHandler handler) {
        this.collisionHandler = handler;
    }

    /**
     * Generic builder class for {@link GameObject} creation.
     * 
     * @param <A> the {@link GameObject} to be built.
     * @param <B> the concrete {@link AbstractBuilder} to be used.
     */
    protected abstract static class AbstractBuilder<A extends AbstractGameObject, B extends AbstractBuilder<?, ?>> {
        /**
         * The initial {@link GameObject} to be set.
         */
        private final A gameObject;
        private final B builder;

        /**
         * Class constructor.
         */
        protected AbstractBuilder() {
            this.gameObject = initGameObject();
            this.builder = getBuilder();
        }

        /**
         * Gets the {@link GameObject} to be set.
         * 
         * @return the {@link GameObject} to be set.
         */
        protected A getGameObject() {
            return this.gameObject;
        }

        /**
         * Gets the initial {@link GameObject} to be set.
         * 
         * @return the initial {@link GameObject} to be set.
         */
        protected abstract A initGameObject();

        /**
         * Gets the concrete {@link AbstractBuilder}.
         * 
         * @return the concrete {@link AbstractBuilder}.
         */
        protected abstract B getBuilder();

        /**
         * Gets the set {@link GameObject}.
         * 
         * @return the set {@link GameObject}.
         */
        protected abstract A build();

        /**
         * Sets the {@link GameObject}'s velocity.
         * 
         * @param velocity the {@link GameObject}'s velocity.
         * @return the concrete {@link AbstractBuilder}.
         */
        public B setVelocity(final Vector2D velocity) {
            this.gameObject.setVelocity(velocity);
            return this.builder;
        }

        /**
         * Sets the {@link GameObject}'s position.
         * 
         * @param position the {@link GameObject}'s position.
         * @return the concrete {@link AbstractBuilder}.
         */
        public B setPosition(final Coordinate position) {
            this.gameObject.setPosition(position);
            return this.builder;
        }

        /**
         * Adds a {@link Component} inside the {@link GameObject}.
         * 
         * @param component the {@link Component} to be added.
         * @return the concrete {@link AbstractBuilder}.
         */
        public B addComponent(final Component component) {
            this.gameObject.addComponent(component);
            return this.builder;
        }

        /**
         * Sets the {@link CollisionHandler}.
         * 
         * @param handler the delegate {@link CollisionHandler} used to handle the
         *                {@link Collision}s.
         * @return the concrete {@link AbstractBuilder}.
         */
        public B setCollisionHandler(final CollisionHandler handler) {
            this.getGameObject().setCollisionHandler(handler);
            return this.builder;
        }
    }
}
