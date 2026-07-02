package ballblast.model.levels;

import java.util.Arrays;
import java.util.List;

import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.model.data.GameDataManager;
import ballblast.model.data.SimpleGameDataManager;
import ballblast.model.events.GameEventManager;
import ballblast.model.events.GameEventManagerImpl;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.gameobjects.GameObjectManagerImpl;
import ballblast.model.helpers.GameObjectHelper;
import ballblast.model.helpers.SpawnHelper;
import ballblast.model.inputs.InputManager;
import ballblast.model.inputs.InputManagerImpl;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.SimpleCollisionManager;

/**
 * Generic implementation of the {@link Level} interface. Defines base behavior
 * that all levels share.
 */
public final class BasicLevel implements Level {
    private static final double POWER_SPAWN_TIME = 25.0;

    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    private final InputManager inputManager;
    private final GameDataManager gameDataManager;
    private final GameEventManager gameEventManager;
    private GameStatus gameStatus;
    private double currentSpawnTime;

    /**
     * Class constructor.
     */
    public BasicLevel() {
        this.gameStatus = GameStatus.PAUSE;
        this.gameObjectManager = new GameObjectManagerImpl();
        this.collisionManager = new SimpleCollisionManager();
        this.inputManager = new InputManagerImpl();
        this.gameDataManager = new SimpleGameDataManager();
        this.gameEventManager = new GameEventManagerImpl();
        this.currentSpawnTime = POWER_SPAWN_TIME;
        this.createBoundaries();
    }

    @Override
    public void start() {
        this.gameStatus = GameStatus.RUNNING;
        this.initGameObjectManager();
    }

    @Override
    public void update(final double elapsed) {
        if (this.gameStatus == GameStatus.RUNNING) {
            this.gameDataManager.updateGameTime(elapsed);
            this.gameObjectManager.update(elapsed);
            this.collisionManager.checkLoop();
            this.tryToSpawn(elapsed);
        }
    }

    @Override
    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    @Override
    public void setGameStatus(final GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public GameObjectManager getGameObjectManager() {
        return this.gameObjectManager;
    }

    @Override
    public CollisionManager getCollisionManager() {
        return this.collisionManager;
    }

    @Override
    public InputManager getInputManager() {
        return this.inputManager;
    }

    @Override
    public GameDataManager getGameDataManager() {
        return this.gameDataManager;
    }

    @Override
    public GameEventManager getGameEventManager() {
        return this.gameEventManager;
    }


    private void createBoundaries() {
        final List<GameObject> boundaries = Arrays.stream(Boundary.values())
                .map(this::convertToWall)
                .collect(ImmutableList.toImmutableList());
        this.gameObjectManager.addGameObjects(boundaries);
    }

    private GameObject convertToWall(final Boundary b) {
        return GameObjectHelper.createWall(b.getHeight(), b.getWidth(), 
                b.getPosition(), Vector2D.create(0, 0), this.collisionManager);
    }

    private void initGameObjectManager() {
        this.gameObjectManager.update(0);
        this.gameObjectManager.getGameObjects().forEach(SpawnHelper::activeComponents);
    }

    private void tryToSpawn(final double elapsed) {
        this.currentSpawnTime -= elapsed;
        if (this.currentSpawnTime <= 0) {
            this.spawnPowerUp();
            this.currentSpawnTime = POWER_SPAWN_TIME;
        }
    }

    private void spawnPowerUp() {
        final GameObject power = (GameObject) SpawnHelper
                .spawnRandomPower(Vector2D.create(0, 0), this.collisionManager);
        SpawnHelper.activeComponents(power);
        this.getGameObjectManager().addGameObjects(ImmutableList.of(power));
    }
}
