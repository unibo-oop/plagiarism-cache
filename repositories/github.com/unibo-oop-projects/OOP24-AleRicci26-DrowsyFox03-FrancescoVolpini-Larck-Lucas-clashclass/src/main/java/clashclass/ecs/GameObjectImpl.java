package clashclass.ecs;

import clashclass.engine.GameScene;


import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Represents an implementation of GameObject.
 */
public class GameObjectImpl implements GameObject {
    private static final AtomicInteger COUNTER = new AtomicInteger();

    private final int uniqueId;
    private final Set<Component> components;
    private GameScene scene;
    private boolean destroyedFlag;

    /**
     * Constructs the GameObject.
     */
    public GameObjectImpl() {
        this.uniqueId = COUNTER.getAndIncrement();
        this.components = new LinkedHashSet<>();
        this.destroyedFlag = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getUniqueId() {
        return this.uniqueId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void setScene(final GameScene scene) {
        this.scene = scene;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addComponent(final Component component) {
        component.setGameObject(this);
        this.components.add(component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <T extends Component> Optional<T> getComponentOfType(final Class<T> componentType) {
        return this.components.stream()
                .filter(componentType::isInstance)
                .map(componentType::cast)
                .findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Component> Set<T> getComponentsOfType(final Class<T> componentType) {
        return this.components.stream()
                .filter(componentType::isInstance)
                .map(componentType::cast)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Gets an immutable representation of all the components.
     *
     * @return an immutable representation of all the components
     */
    @Override
    public final Set<Component> getComponents() {
        return Collections.unmodifiableSet(this.components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public GameScene getScene() {
        return this.scene;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public final void destroy() {
        this.destroyedFlag = true;
    }

    @Override
    public final synchronized boolean isMarkedAsDestroyed() {
        return this.destroyedFlag;
    }

    /**
     * Represents a GameObject.Builder implementation.
     */
    public static class BuilderImpl implements Builder {
        private final Set<Component> components;

        /**
         * Constructs the Builder.
         */
        public BuilderImpl() {
            this.components = new LinkedHashSet<>();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Builder addComponent(final Component component) {
            this.components.add(component);
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public GameObject build() {
            if (this.components.isEmpty()) {
                throw new IllegalStateException("There must be at least one component");
            }

            final var gameObject = new GameObjectImpl();
            this.components.forEach(gameObject::addComponent);

            return gameObject;
        }
    }
}
