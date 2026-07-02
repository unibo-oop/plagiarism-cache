package pvz.model.collisions.impl;

import pvz.model.bullets.api.Bullet;
import pvz.model.collisions.api.CollisionManager;
import pvz.model.game.api.EntitiesManager;
import pvz.model.entities.api.Entity;
import pvz.model.plants.api.Plant;
import pvz.utilities.PlantType;
import pvz.model.zombies.api.Zombie;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of {@link CollisionManager} that handles collision detection between
 * bullets, zombies, and plants in the game.
 * <p>
 * This class is not designed for extension.
 * </p>
 */
public final class CollisionManagerImpl implements CollisionManager {

    /**
     * {@inheritDoc}
     * <p>
     * This method is not intended to be overridden. If you need custom collision logic,
     * create a new implementation of {@link CollisionManager}.
     * </p>
     */
    @Override
    public Optional<Entity> handleCollision(final Entity entity, final EntitiesManager entitiesManager) {
        if (entity instanceof Bullet) {
            return this.handleBulletZombieCollision((Bullet) entity, entitiesManager).map(zombie -> zombie);
        } else if (entity instanceof Zombie) {
            return this.handleZombiePlantCollision((Zombie) entity, entitiesManager).map(plant -> plant);
        } else if (entity instanceof Plant) {
            final Plant plant = (Plant) entity;
            if (plant.mapToEntityType() == PlantType.WALLNUT) {
                return this.handleWallNutZombieCollision(plant, entitiesManager).map(zombie -> zombie);
            }
        }
        return Optional.empty();
    }

    private Optional<Zombie> handleWallNutZombieCollision(final Plant wallNut, final EntitiesManager entitiesManager) {
        final Set<Zombie> zombieSet = entitiesManager.getEntities().stream()
            .filter(entity -> entity instanceof Zombie)
            .filter(zombie -> BigDecimal.valueOf(zombie.getPosition().y())
                   .compareTo(BigDecimal.valueOf(wallNut.getPosition().y())) == 0)
            .map(entity -> (Zombie) entity)
            .collect(Collectors.toSet());
        for (final Zombie zombie : zombieSet) {
            if (wallNut.getHitBox().isColliding(zombie.getHitBox())) {
                return Optional.of(zombie);
            }
        }
        return Optional.empty();
    }

    private Optional<Zombie> handleBulletZombieCollision(final Bullet bullet, final EntitiesManager entitiesManager) {
        final Set<Zombie> zombieSet = entitiesManager.getEntities().stream()
            .filter(entity -> entity instanceof Zombie)
            .filter(zombie -> BigDecimal.valueOf(zombie.getPosition().y())
                   .compareTo(BigDecimal.valueOf(bullet.getPosition().y())) == 0)
            .map(entity -> (Zombie) entity)
            .collect(Collectors.toSet());
        for (final Zombie zombie : zombieSet) {
            if (bullet.getHitBox().isColliding(zombie.getHitBox())) {
                return Optional.of(zombie);
            }
        }
        return Optional.empty();
    }

    private Optional<Plant> handleZombiePlantCollision(final Zombie zombie, final EntitiesManager entitiesManager) {
        final Set<Plant> plantSet = entitiesManager.getEntities().stream()
            .filter(entity -> entity instanceof Plant)
            .map(entity -> (Plant) entity)
            .filter(plant -> BigDecimal.valueOf(plant.getPosition().y())
                    .compareTo(BigDecimal.valueOf(zombie.getPosition().y())) == 0)
            .collect(Collectors.toSet());
        for (final Plant plant : plantSet) {
            if (zombie.getHitBox().isColliding(plant.getHitBox())) {
                return Optional.of(plant);
            }
        }
        return Optional.empty();
    }
}
