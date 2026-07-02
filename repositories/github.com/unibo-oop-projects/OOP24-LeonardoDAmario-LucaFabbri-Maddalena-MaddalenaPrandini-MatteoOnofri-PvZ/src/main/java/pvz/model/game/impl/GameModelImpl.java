package pvz.model.game.impl;

import pvz.model.bullets.api.Bullet;
import pvz.model.collisions.impl.HitBoxFactory;
import pvz.model.game.api.EntitiesManager;
import pvz.model.entities.api.Entity;
import pvz.model.plants.impl.PlantFactoryImpl;
import pvz.utilities.EntityType;
import pvz.utilities.GameEntity;
import pvz.model.game.api.Difficulty;
import pvz.model.game.api.GameModel;
import pvz.model.game.api.GameStatus;
import pvz.model.lawnmower.api.LawnMower;
import pvz.model.lawnmower.impl.LawnMowerImpl;
import pvz.model.plants.api.Plant;
import pvz.utilities.PlantType;
import pvz.model.zombies.api.Zombie;
import pvz.model.zombies.impl.ZombieSpawnUtil;
import pvz.utilities.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Concrete implementation of {@link GameModel}.
 * <p>
 * Manages the core game logic including entity lifecycle, state updates, plant placement,
 * and determining game status such as victory or defeat.
 */
public class GameModelImpl implements GameModel {
    private static final int ROWS = 5;
    private static final int COLS = 9;
    private static final int SPAWN_RATE = 5000;

    private static final Map<Difficulty, Integer> KILL_TO_WIN = Map.of(
            Difficulty.EASY, 20,
            Difficulty.NORMAL, 35,
            Difficulty.HARD, 45
    );

    private final EntitiesManager entitiesManager;
    private GameStatus status;
    private final Difficulty difficulty;
    private final List<Boolean> usedMower = new ArrayList<>(Collections.nCopies(ROWS, false));
    private long zombieLastSpawnTime;

    /**
     * Constructs the game model with the specified difficulty.
     *
     * @param difficulty the game difficulty level.
     */
    public GameModelImpl(final Difficulty difficulty) {
        this.difficulty = difficulty;
        this.entitiesManager = new EntitiesManagerImpl();
        this.status = GameStatus.IN_PROGRESS;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isGameOver() {
        return status != GameStatus.IN_PROGRESS;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isVictory() {
        return status == GameStatus.WON;
    }

    /** {@inheritDoc}
     * <p>
     * Updates all entities, spawns zombies as necessary, checks for lawnmower activation,
     * removes out-of-frame objects, and updates the game status accordingly.
     */
    @Override
    public void update(final long deltaTime) {
        if (status != GameStatus.IN_PROGRESS) {
            return;
        }

        spawnZombie(deltaTime);
        updateEntities(deltaTime);
        checkZombiesForMower();
        removeOutOfFrameObjects();
        checkGameStatus();
    }

    /** {@inheritDoc} */
    @Override
    public Set<GameEntity> getGameEntities() {
        return entitiesManager.getEntities().stream()
                .map(e -> new GameEntity(getEntityTypeFromEntity(e), e.getPosition()))
                .collect(Collectors.toSet());
    }

    /** {@inheritDoc} */
    @Override
    public int getSunCount() {
        return entitiesManager.getSunCount();
    }

    /** {@inheritDoc} */
    @Override
    public int getKillCount() {
        return entitiesManager.getKillCount();
    }

    /** {@inheritDoc}
     * <p>
     * Attempts to place a plant of the given type at the specified position,
     * checking sun resources before adding the plant to entitiesManager.
     */
    @Override
    public void placePlant(final EntityType type, final Position position) {
        final PlantFactoryImpl plantFactory = new PlantFactoryImpl();
        final PlantType plantType = getPlantTypeFromEntityType(type);
        final Plant plant = switch (plantType) {
            case PEASHOOTER -> plantFactory.createPeashooter(position);
            case SUNFLOWER -> plantFactory.createSunflower(position);
            case WALLNUT -> plantFactory.createWallnut(position);
        };
        if (entitiesManager.decreaseSun(plantType.getPrice())) {
            entitiesManager.addEntity(plant);
        }
    }

    /**
     * Spawns a new zombie if enough time has passed since the last spawn,
     * based on the selected difficulty level.
     *
     * @param deltaTime time elapsed since the last update (in milliseconds).
     */
    private void spawnZombie(final long deltaTime) {
        zombieLastSpawnTime += deltaTime;
        if (zombieLastSpawnTime >= SPAWN_RATE) {
            zombieLastSpawnTime = 0;
            final Zombie zombie = ZombieSpawnUtil.generateRandomZombie(difficulty, ROWS);
            entitiesManager.addEntity(zombie);
        }
    }

    /**
     * Updates all active entities in the game by calling their {@code update} method.
     *
     * @param deltaTime time elapsed since the last update (in milliseconds).
     */
    private void updateEntities(final long deltaTime) {
        entitiesManager.getEntities().forEach(e -> e.update(deltaTime, entitiesManager));
    }

    /**
     * Checks if any zombie has reached the start of a row. If so, it triggers
     * the lawn mower or ends the game if the mower has already been used.
     */
    private void checkZombiesForMower() {
        entitiesManager.getEntities().stream()
                .filter(Zombie.class::isInstance)
                .map(Zombie.class::cast)
                .forEach(this::handleZombieAtRowStart);
    }

    /**
     * Handles a zombie that has reached the beginning of a row:
     * activates the lawn mower if available, or ends the game otherwise.
     *
     * @param zombie the zombie that has reached the row start.
     */
    private void handleZombieAtRowStart(final Zombie zombie) {
        final int row = (int) Math.round(zombie.getPosition().y());
        if (zombie.getPosition().x() <= 0) {
            if (usedMower.get(row)) {
                status = GameStatus.LOST;
            } else {
                addLawnMower(row);
            }
        }
    }

    /**
     * Adds a lawn mower to the given row and activates it immediately.
     *
     * @param row the row index where the lawn mower should be added.
     */
    private void addLawnMower(final int row) {
        usedMower.set(row, true);
        final LawnMower lawnMower = new LawnMowerImpl(new Position(0, row), HitBoxFactory.HitBoxType.LAWNMOWER);
        entitiesManager.addEntity(lawnMower);
        lawnMower.update(0, entitiesManager);
    }

    /**
     * Removes out-of-frame objects such as bullets or lawn mowers that
     * have exited the game area.
     */
    private void removeOutOfFrameObjects() {
        entitiesManager.getEntities().stream()
                .filter(e -> (e instanceof Bullet || e instanceof LawnMower) && e.getPosition().x() > COLS + 2)
                .collect(Collectors.toList())
                .forEach(entitiesManager::removeEntity);
    }

    /**
     * Checks if the player has reached the number of kills required to win,
     * based on the difficulty level. If so, sets the game status to WON.
     */
    private void checkGameStatus() {
        if (status == GameStatus.IN_PROGRESS && getKillCount() >= KILL_TO_WIN.get(difficulty)) {
            status = GameStatus.WON;
        }
    }

    /**
     * Converts a generic entity into its corresponding {@link EntityType},
     * used for mapping internal game logic to external representations.
     *
     * @param entity the entity to convert.
     * @return the corresponding {@link EntityType}.
     * @throws IllegalArgumentException if the entity type is unknown.
     */
    private static EntityType getEntityTypeFromEntity(final Entity entity) {
        return switch (entity) {
            case Plant plant -> switch (plant.mapToEntityType()) {
                case PEASHOOTER -> EntityType.PEASHOOTER;
                case SUNFLOWER -> EntityType.SUNFLOWER;
                case WALLNUT -> EntityType.WALLNUT;
            };
            case Zombie zombie -> switch (zombie.getType()) {
                case BASICZOMBIE -> EntityType.BASICZOMBIE;
                case FASTZOMBIE -> EntityType.FASTZOMBIE;
                case STRONGZOMBIE -> EntityType.STRONGZOMBIE;
                case BEASTZOMBIE -> EntityType.BEASTZOMBIE;
            };
            case Bullet ignored -> EntityType.BULLET;
            case LawnMower ignored -> EntityType.LAWNMOWER;
            default -> throw new IllegalArgumentException();
        };
    }

    /**
     * Converts an {@link EntityType} to its corresponding {@link PlantType},
     * only for plant types.
     *
     * @param entityType the type of entity.
     * @return the corresponding {@link PlantType}.
     * @throws IllegalArgumentException if the entity type is not a valid plant.
     */
    private static PlantType getPlantTypeFromEntityType(final EntityType entityType) {
        return switch (entityType) {
            case PEASHOOTER -> PlantType.PEASHOOTER;
            case SUNFLOWER -> PlantType.SUNFLOWER;
            case WALLNUT -> PlantType.WALLNUT;
            default -> throw new IllegalArgumentException();
        };
    }
}
