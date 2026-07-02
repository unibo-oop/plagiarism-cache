package model.entity.manager;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.geometry.Dimension2D;
import model.entity.DynamicEntity;
import model.entity.SpawnLevel;
import model.entity.factory.EntityFactory;
import model.entity.factory.EntityFactoryImpl;
import model.entity.EntityType;

/**
 * 
 * Class used to generate a list of {@link DynamicEntity}.
 *
 */
public final class EntityManagerImpl implements EntityManager {

    private static final double INITIAL_SPEEDX = 2.0;
    private static final int POWERUP_RARITY = 25;
    private static final int MAX_CASE = 3;

    private final List<DynamicEntity> entities;
    private final EntityFactory factory;
    private final Counter counter;
    private final SecureRandom random; 
    private double speedX;

    /**
     * 
     * @param worldDimensions the game's world dimensions.
     */
    public EntityManagerImpl(final Dimension2D worldDimensions) {

        this.factory = new EntityFactoryImpl(worldDimensions);
        this.entities = new ArrayList<>();
        this.counter = new Counter();
        this.speedX = INITIAL_SPEEDX;
        this.random = new SecureRandom();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DynamicEntity> getEntities() {
        return this.entities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeedX(final double speedX) {
        this.speedX = speedX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateList() {

        this.removeEntity(e -> e.wasHit() && (e.getType() == EntityType.POWERUP || e.getType() == EntityType.COIN));

        this.entities.forEach(e -> e.updatePosition(speedX));
        this.removeEntity(e -> e.isOutofScreen()); 

        if (this.entities.isEmpty()) {
            this.entities.addAll(factory.combineAll(SpawnLevel.ONE, SpawnLevel.ZERO, SpawnLevel.TWO));
            counter.increment(3);
        } else if (this.checkPosition()) {
            this.addEntity();
        }

    }

    /**
     * Add a new entities' configuration to the entities list.
     */
    private void addEntity() {

        final DynamicEntity last = this.entities.get(entities.size() - 1);

        if (counter.get() < POWERUP_RARITY) {
            switch (last.getLevel()) {
                case ZERO:
                    this.levelZeroConfig();
                    break;
                case ONE:
                    this.levelOneConfig();
                    break;
                case TWO:
                    this.levelTwoConfig();
                    break;
                default:
                    break;
            }
        } else {
            this.entities.add(factory.createPowerup(SpawnLevel.values()[random.nextInt(MAX_CASE)]));
            this.counter.reset();
        }
    }

    /**
     * Add a new entities' configuration to the entities' list, starting from level zero. 
     */
    private void levelZeroConfig() {

        final Stream.Builder<List<DynamicEntity>> builder = Stream.builder();
        final List<DynamicEntity> newConfig = builder.add(factory.combineObstacleCoin(SpawnLevel.ZERO, SpawnLevel.ONE))
                                                     .add(factory.combinePlatformObstacle(SpawnLevel.ONE, SpawnLevel.ZERO))
                                                     .add(List.of(factory.createPlatform(SpawnLevel.ONE)))
                                                     .build()
                                                     .skip(random.nextInt(MAX_CASE))
                                                     .findFirst()
                                                     .get();
        this.entities.addAll(newConfig);
        this.counter.increment(newConfig.size());

    }

    /**
     * Add a new entities' configuration to the entities' list, starting from level one. 
     */
    private void levelOneConfig() {

        final Stream.Builder<List<DynamicEntity>> builder = Stream.builder();
        final List<DynamicEntity> newConfig = builder.add(factory.combinePlatformObstacle(SpawnLevel.TWO, SpawnLevel.ZERO))
                                                     .add(factory.combinePlatformCoin(SpawnLevel.TWO, SpawnLevel.ONE))
                                                     .add(List.of(factory.createObstacle(SpawnLevel.ZERO)))
                                                     .build()
                                                     .skip(random.nextInt(MAX_CASE))
                                                     .findFirst()
                                                     .get();
        this.entities.addAll(newConfig);
        this.counter.increment(newConfig.size());

    }

    /**
     * Add a new entities' configuration to the entities' list, starting from level two. 
     */
    private void levelTwoConfig() {

        final Stream.Builder<List<DynamicEntity>> builder = Stream.builder();
        final List<DynamicEntity> newConfig = builder.add(factory.combinePlatformCoin(SpawnLevel.ONE, SpawnLevel.TWO))
                                                     .add(factory.combinePlatformObstacle(SpawnLevel.ONE, SpawnLevel.ZERO))
                                                     .add(List.of(factory.createCoin(SpawnLevel.ONE)))
                                                     .build()
                                                     .skip(random.nextInt(MAX_CASE))
                                                     .findFirst()
                                                     .get();
        this.entities.addAll(newConfig);
        this.counter.increment(newConfig.size());
    }

    /**
     * Remove the filtered entities from the entities list. 
     * @param filterCondition the condition used to remove the entities from the list. 
     */
    private void removeEntity(final Predicate<DynamicEntity> filterCondition) {

        this.entities.removeAll(entities.stream()
                                        .filter(filterCondition)
                                        .collect(Collectors.toList()));

    }

    /**
     * Check if a new entity could spawn, according to the last entity's distance.
     * @return true if the entity could spawn, false otherwise.
     */
    private boolean checkPosition() {
        final DynamicEntity last = entities.get(entities.size() - 1);
        return last.getBounds().getMinX() <= last.getDistance();
    }

    /**
     * 
     * Simple counter used to count the entities added to the list.
     *
     */
    private class Counter {

        private int count;

        Counter() {
            this.count = 0;
        }

        public void increment(final int increment) {
            this.count += increment;
        }

        public void reset() {
            this.count = 0;
        }

        public int get() {
            return this.count;
        }
    }

}
