package ballblast.model.gameobjects;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * Concrete {@link GameObjectManager} implementation.
 */
public class GameObjectManagerImpl implements GameObjectManager {
    private List<GameObject> gameObjects;
    private List<GameObject> toBeAdded;

    /**
     * Creates a new instance of GameObjectManager.
     */
    public GameObjectManagerImpl() {
        this.gameObjects = Collections.emptyList();
        this.toBeAdded = Collections.emptyList();
    }

    @Override
    public final void update(final double elapsed) {
        this.gameObjects.forEach(o -> o.update(elapsed));
        this.joinGameObjects();
        this.removeDestoyedObjects();
    }

    @Override
    public final void addGameObjects(final List<GameObject> gameObjects) {
        this.toBeAdded = ImmutableList.<GameObject>builder()
                .addAll(toBeAdded)
                .addAll(gameObjects)
                .build();
    }

    @Override
    public final List<GameObject> getGameObjects() {
        return ImmutableList.copyOf(this.gameObjects);
    }

    /*
     * Concatenates the main {@link GameObject} {@link List} with the {@link
     * GameObject} {@link List} to be added.
     */
    private void joinGameObjects() {
        this.gameObjects = ImmutableList.<GameObject>builder()
                .addAll(gameObjects)
                .addAll(toBeAdded)
                .build();
       this.emptyList();
    }

    private void removeDestoyedObjects() {
        this.gameObjects = gameObjects.stream()
                .filter(g -> !g.isDestroyed())
                .collect(ImmutableList.toImmutableList());
    }

    private void emptyList() {
        this.toBeAdded = Collections.emptyList();
    }
}
