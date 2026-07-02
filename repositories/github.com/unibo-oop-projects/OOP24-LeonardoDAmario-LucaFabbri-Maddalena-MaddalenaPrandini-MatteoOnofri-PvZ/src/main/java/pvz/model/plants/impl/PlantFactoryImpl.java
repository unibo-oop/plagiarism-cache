package pvz.model.plants.impl;

import pvz.model.bullets.impl.BulletImpl;
import pvz.model.collisions.api.CollisionManager;
import pvz.model.collisions.impl.CollisionManagerImpl;
import pvz.model.game.api.EntitiesManager;
import pvz.model.plants.api.Plant;
import pvz.model.plants.api.PlantFactory;
import pvz.utilities.PlantType;
import pvz.model.zombies.api.Zombie;
import pvz.utilities.Position;

import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the {@link PlantFactory} interface.
 * Responsible for creating different types of {@link Plant} instances,
 * each with its own behavior and logic.
 */
public final class PlantFactoryImpl implements PlantFactory {

    /**
     * {@inheritDoc}
     *
     * <p>Creates a new {@link PlantType#PEASHOOTER} instance at the specified position.
     * The Peashooter periodically shoots bullets if it is alive.</p>
     *
     * @param position the position where the Peashooter should be placed; must not be {@code null}
     * @return a new {@link Plant} instance representing a Peashooter
     * @throws NullPointerException if {@code position} is {@code null}
     */
    @Override
    public Plant createPeashooter(final Position position) {
        Objects.requireNonNull(position);
        return new AbstractPlant(position) {

            private static final double FIRE_RATE = 1750;
            private double elapsedTime;

            @Override
            public PlantType mapToEntityType() {
                return PlantType.PEASHOOTER;
            }

            @Override
            public void update(final long deltaTime, final EntitiesManager entitiesManager) {
                elapsedTime += deltaTime;
                if (elapsedTime >= FIRE_RATE && getLife() > 0) {
                    entitiesManager.addEntity(
                            new BulletImpl(
                                    new Position(
                                            this.getPosition().x() + this.getHitBox().getWidth(),
                                            this.getPosition().y()
                                    )
                            )
                    );
                    elapsedTime = 0;
                }
            }

            @Override
            protected int getMaxLife() {
                return PlantType.PEASHOOTER.getLife();
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * <p>Creates a new {@link PlantType#SUNFLOWER} instance at the specified position.
     * The Sunflower periodically generates sun points if it is alive.</p>
     *
     * @param position the position where the Sunflower should be placed; must not be {@code null}
     * @return a new {@link Plant} instance representing a Sunflower
     * @throws NullPointerException if {@code position} is {@code null}
     */
    @Override
    public Plant createSunflower(final Position position) {
        Objects.requireNonNull(position);
        return new AbstractPlant(position) {

            private static final int SUN_VALUE = 25;
            private static final long SUN_GENERATION_INTERVAL = 6000;
            private long lastSunTime;

            @Override
            public PlantType mapToEntityType() {
                return PlantType.SUNFLOWER;
            }

            @Override
            public void update(final long deltaTime, final EntitiesManager entitiesManager) {
                if (getLife() <= 0) {
                    return;
                }
                lastSunTime += deltaTime;
                if (lastSunTime >= SUN_GENERATION_INTERVAL) {
                    entitiesManager.addSun(SUN_VALUE);
                    lastSunTime = 0;
                }
            }

            @Override
            protected int getMaxLife() {
                return PlantType.SUNFLOWER.getLife();
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * <p>Creates a new {@link PlantType#WALLNUT} instance at the specified position.
     * The Wall-nut checks for collisions with zombies and removes both itself
     * and the collided zombie if a collision occurs.</p>
     *
     * @param position the position where the Wall-nut should be placed; must not be {@code null}
     * @return a new {@link Plant} instance representing a Wall-nut
     * @throws NullPointerException if {@code position} is {@code null}
     */
    @Override
    public Plant createWallnut(final Position position) {
        Objects.requireNonNull(position);
        return new AbstractPlant(position) {

            private final CollisionManager collisionManager = new CollisionManagerImpl();

            @Override
            public PlantType mapToEntityType() {
                return PlantType.WALLNUT;
            }

            @Override
            public void update(final long deltaTime, final EntitiesManager entitiesManager) {
                final Optional<Zombie> zombie = collisionManager
                        .handleCollision(this, entitiesManager)
                        .map(entity -> (Zombie) entity);

                if (zombie.isPresent()) {
                    entitiesManager.removeEntity(zombie.get());
                    entitiesManager.removeEntity(this);
                    entitiesManager.addKill();
                }
            }

            @Override
            protected int getMaxLife() {
                return PlantType.WALLNUT.getLife();
            }
        };
    }
}
