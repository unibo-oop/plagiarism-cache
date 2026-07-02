package todo.view.entities;

import java.util.Objects;
import java.util.Optional;

import com.badlogic.gdx.math.Vector2;

import todo.utils.UniqueId;
import todo.utils.UniqueIdGenerator;

/**
 * This class represents the base skeleton for an entity.
 */
public abstract class BaseEntity implements Entity {
    private Vector2 position;
    private float rotation;
    private Vector2 scale;
    private Optional<Entity> parent;
    private final UniqueId id;

    protected BaseEntity() {
        this.id = UniqueIdGenerator.getInstance().next();
        this.parent = Optional.empty();
        this.position = Vector2.Zero;
        this.rotation = 0f;
        this.scale = new Vector2(1f, 1f);
    }

    @Override
    public final Vector2 getPosition() {
        return this.position.cpy();
    }

    @Override
    public final void setPosition(final Vector2 position) {
        this.position = Objects.requireNonNull(position).cpy();
    }

    @Override
    public final float getRotation() {
        return this.rotation;
    }

    @Override
    public final void setRotation(final float radians) {
        this.rotation = radians;
    }

    @Override
    public final Vector2 getScale() {
        return this.scale.cpy();
    }

    @Override
    public final void setScale(final Vector2 scale) {
        this.scale = Objects.requireNonNull(scale).cpy();
    }

    @Override
    public final Vector2 getRelativePosition() {
        return this.parent.isPresent() ? this.position.cpy().sub(this.parent.get().getPosition()) : getPosition();
    }

    @Override
    public final void setRelativePosition(final Vector2 relativePosition) {
        if (this.parent.isPresent()) {
            setPosition(this.parent.get().getPosition().add(relativePosition));
        } else {
            setPosition(relativePosition);
        }
    }

    @Override
    public final float getRelativeRotation() {
        return this.parent.isPresent() ? this.rotation - this.parent.get().getRotation() : getRotation();
    }

    @Override
    public final void setRelativeRotation(final float relativeRadians) {
        if (this.parent.isPresent()) {
            this.rotation = this.parent.get().getRotation() + relativeRadians;
        } else {
            setRotation(relativeRadians);
        }
    }

    @Override
    public final Vector2 getRelativeScale() {
        if (this.parent.isPresent()) {
            final Vector2 parentScale = this.parent.get().getScale();
            return !parentScale.isZero() ? new Vector2(this.scale.x / parentScale.x, this.scale.y / parentScale.y)
                    : Vector2.Zero;
        } else {
            return getScale();
        }
    }

    @Override
    public final void setRelativeScale(final Vector2 relativeScale) {
        if (this.parent.isPresent()) {
            this.scale = this.parent.get().getScale().scl(relativeScale);
        }
    }

    @Override
    public final Optional<Entity> getParent() {
        return this.parent;
    }

    @Override
    public final void setParent(final Entity parentEntity) {
        // Null-checking is done by Optional.of
        this.parent = Optional.of(parentEntity);
    }

    @Override
    public final void removeParent() {
        this.parent = Optional.empty();
    }

    @Override
    public void update(final float deltaTime) {
        // Do nothing
    }

    @Override
    public UniqueId getId() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof BaseEntity && ((BaseEntity) obj).id.equals(this.id);
    }

    /**
     * This abstract class is used to build a specific type of entity.
     *
     * @param <S> is the self type
     * @param <E> is the entity type to be spawned
     */
    public abstract static class Builder<S extends Builder<? extends S, E>, E extends Entity>
            implements EntityBuilder<S, E> {
        private static final Vector2 ONE = new Vector2(1, 1);

        private Vector2 currentPosition = Vector2.Zero;
        private boolean isPositionRelative;
        private float currentRotation;
        private boolean isRotationRelative;
        private Vector2 currentScale = ONE;
        private boolean isScaleRelative;
        private Optional<Entity> currentParent = Optional.empty();

        @Override
        @SuppressWarnings(value = "unchecked")
        public final S position(final Vector2 position) {
            this.currentPosition = position;
            this.isPositionRelative = false;
            return (S) this;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        public final S rotation(final float radians) {
            this.currentRotation = radians;
            this.isRotationRelative = false;
            return (S) this;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        public final S scale(final Vector2 scale) {
            this.currentScale = scale;
            this.isScaleRelative = false;
            return (S) this;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        public final S relativePosition(final Vector2 position) {
            this.currentPosition = position;
            this.isPositionRelative = true;
            return (S) this;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        public final S relativeRotation(final float radians) {
            this.currentRotation = radians;
            this.isRotationRelative = true;
            return (S) this;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        public final S relativeScale(final Vector2 scale) {
            this.currentScale = scale;
            this.isScaleRelative = true;
            return (S) this;
        }

        @Override
        @SuppressWarnings(value = "unchecked")
        public final S parent(final Entity parent) {
            this.currentParent = Optional.of(parent);
            return (S) this;
        }

        @Override
        public E build() {
            E built = callConstructor();
            if (this.currentParent.isPresent()) {
                built.setParent(this.currentParent.get());
            }
            if (this.isPositionRelative) {
                built.setRelativePosition(this.currentPosition);
            } else {
                built.setPosition(this.currentPosition);
            }
            if (this.isRotationRelative) {
                built.setRelativeRotation(this.currentRotation);
            } else {
                built.setRotation(this.currentRotation);
            }
            if (this.isScaleRelative) {
                built.setRelativeScale(this.currentScale);
            } else {
                built.setScale(this.currentScale);
            }
            built = additionalBuild(built);
            return built;
        }

        /**
         * This abstract method must call the desired entity type constructor, to
         * overcome the limitations of type erasure.
         *
         * @return the desired entity instance
         */
        protected abstract E callConstructor();

        /**
         * This method can be overridden to add additional steps after the base build
         * process. If not overridden, this method will not change the entity's
         * properties.
         *
         * @param entity is the input entity
         * @return is the modified entity
         */
        protected E additionalBuild(final E entity) {
            // Do nothing
            return entity;
        }
    }
}
