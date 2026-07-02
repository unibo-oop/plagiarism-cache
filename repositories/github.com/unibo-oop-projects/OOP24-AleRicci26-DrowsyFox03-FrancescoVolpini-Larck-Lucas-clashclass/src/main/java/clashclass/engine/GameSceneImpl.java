package clashclass.engine;

import clashclass.ecs.Component;
import clashclass.ecs.GameObject;
import clashclass.ecs.UpdateProvider;


import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Represents a GameScene implementation.
 */
public class GameSceneImpl implements GameScene {
    private final Set<GameObject> gameObjects;
    private final Set<UpdateProvider> componentsToUpdate;

    /**
     * Constructs the game scene.
     */
    public GameSceneImpl() {
        this.gameObjects = new HashSet<>();
        this.componentsToUpdate = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final synchronized void traverseGameObjects(final Consumer<GameObject> consumer) {
        this.gameObjects.forEach(consumer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final synchronized void updateGameObjects(final float deltaTime) {
        this.componentsToUpdate.forEach(gameObject -> gameObject.update(deltaTime));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void checkForDestroyedGameObjects() {
        this.gameObjects.stream()
                .filter(GameObject::isMarkedAsDestroyed)
                .filter(gameObject -> gameObject instanceof UpdateProvider)
                .forEach(gameObject -> this.componentsToUpdate.remove((UpdateProvider) gameObject));
        this.gameObjects.removeIf(GameObject::isMarkedAsDestroyed);
        this.componentsToUpdate.removeIf(c -> ((Component) c).getGameObject().isMarkedAsDestroyed());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final synchronized void addGameObject(final GameObject gameObject) {
        this.gameObjects.add(gameObject);
        gameObject.setScene(this);

        gameObject.getComponents().stream()
                .filter(c -> c instanceof UpdateProvider)
                .forEach(c -> this.componentsToUpdate.add((UpdateProvider) c));
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public synchronized Set<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Set<GameObject> getGameObjectsCopy() {
        return new HashSet<>(this.getGameObjects());
    }
}
