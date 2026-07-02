package ballblast.model.levels;

import ballblast.model.data.GameDataManager;
import ballblast.model.events.GameEventManager;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.inputs.InputManager;
import ballblast.model.physics.CollisionManager;

/**
 * Represents an abstraction for all level decorators. Every method of the
 * {@link Level} interface is implemented by delegating to the decorated
 * instance of {@link Level}.
 */

public abstract class LevelDecorator implements Level {
    private final Level innerLevel;

    /**
     * Creates a {@link LevelDecorator} instance.
     * 
     * @param level the {@link Level} used like decoration.
     */
    public LevelDecorator(final Level level) {
        this.innerLevel = level;
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public void update(final double elapsed) {
        this.innerLevel.update(elapsed);
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public final GameObjectManager getGameObjectManager() {
        return this.innerLevel.getGameObjectManager();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public final CollisionManager getCollisionManager() {
        return this.innerLevel.getCollisionManager();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public final InputManager getInputManager() {
        return this.innerLevel.getInputManager();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public void start() {
        this.innerLevel.start();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public GameStatus getGameStatus() {
        return this.innerLevel.getGameStatus();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public void setGameStatus(final GameStatus gameStatus) {
        this.innerLevel.setGameStatus(gameStatus);
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public GameDataManager getGameDataManager() {
        return this.innerLevel.getGameDataManager();
    }

    /**
     * Standard implementation delegates to innerLevel.
     */
    @Override
    public GameEventManager getGameEventManager() {
        return this.innerLevel.getGameEventManager();
    }
}
