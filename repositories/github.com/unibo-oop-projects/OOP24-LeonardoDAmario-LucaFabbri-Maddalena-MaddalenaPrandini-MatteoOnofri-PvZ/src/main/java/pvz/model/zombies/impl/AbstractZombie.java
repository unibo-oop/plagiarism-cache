package pvz.model.zombies.impl;
import pvz.model.collisions.api.CollisionManager;
import pvz.model.collisions.impl.CollisionManagerImpl;
import pvz.model.collisions.impl.HitBoxFactory.HitBoxType;
import pvz.model.game.api.EntitiesManager;
import pvz.model.entities.impl.AbstractEntity;
import pvz.model.plants.api.Plant;
import pvz.model.zombies.api.Zombie;
import pvz.model.zombies.api.ZombieType;
import pvz.utilities.Position;

import java.util.Optional;

/**
 * Abstract base class for all zombie types in the game.
 * Provides common behavior such as health tracking, movement, and collision handling.
 */
abstract class AbstractZombie extends AbstractEntity implements Zombie {

    /** The current health points of the zombie. */
    private int health;
    /** The movement speed of the zombie. */
    private final int speed;
    /** Whether the zombie is alive. */
    private boolean alive;
    /** The collision manager for handling collisions. */
    private final CollisionManager collisionManager;
    /** The rate at which the zombie can attack (in milliseconds). */
    protected static final long ATTACK_RATE = 2000;
    /** The time since the last attack. */
    private long lastAttackTime;

    /**
     * Constructs an AbstractZombie with the specified position, health, and speed.
     *
     * @param position the initial position of the zombie.
     * @param health   the initial health of the zombie.
     * @param speed    the movement speed of the zombie.
     */
    AbstractZombie(final Position position, final int health, final int speed) {
        super(position, HitBoxType.ZOMBIE);
        this.health = health;
        this.speed = speed;
        this.alive = true;
        this.collisionManager = new CollisionManagerImpl();
        this.lastAttackTime = ATTACK_RATE;
    }

    /**
     * {@inheritDoc}
     * Updates the zombie's state, handling movement and attacking plants.
     *
     * @param deltaTime       the time elapsed since the last update (in ms).
     * @param entitiesManager the manager containing all game entities.
     */
    @Override
    public void update(final long deltaTime, final EntitiesManager entitiesManager) {
        final Optional<Plant> plant = collisionManager.handleCollision(this, entitiesManager).map(entity -> (Plant) entity);
        if (plant.isPresent()) {
            lastAttackTime += deltaTime;
            if (lastAttackTime >= ATTACK_RATE) {
                plant.get().decreaseLife(this.getDamage());
                lastAttackTime = 0;
                if (plant.get().getLife() <= 0) {
                    lastAttackTime = ATTACK_RATE;
                    entitiesManager.removeEntity(plant.get());
                }
            }
        } else {
            this.move();
        }
    }

    /**
     * Moves the zombie based on its speed.
     */
    private void move() {
        final double move = this.speed * (1 / 100.0);
        final double newX = this.getPosition().x() - move;
        this.setPosition(new Position(newX, this.getPosition().y()));
        this.getHitBox().update(this.getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void takeDamage(final int damage) {
        health -= damage;
        if (health < 0) {
            alive = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return alive && health > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forceKill() {
        alive = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseLife(final int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.alive = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract int getDamage();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract ZombieType getType();
}
