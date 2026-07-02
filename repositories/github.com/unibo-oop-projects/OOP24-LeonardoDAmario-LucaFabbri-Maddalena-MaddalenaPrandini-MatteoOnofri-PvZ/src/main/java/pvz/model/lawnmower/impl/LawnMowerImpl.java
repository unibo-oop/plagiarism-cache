package pvz.model.lawnmower.impl;

import pvz.model.collisions.api.CollisionManager;
import pvz.model.collisions.impl.CollisionManagerImpl;
import pvz.model.collisions.impl.HitBoxFactory;
import pvz.model.game.api.EntitiesManager;
import pvz.model.entities.api.Entity;
import pvz.model.entities.impl.AbstractEntity;
import pvz.model.lawnmower.api.LawnMower;
import pvz.model.zombies.api.Zombie;
import pvz.utilities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the implementation of a Lawn Mower entity.
 * It moves horizontally and kills any zombie it collides with.
 */
public class LawnMowerImpl extends AbstractEntity implements LawnMower {

    /**
     * The movement speed of the lawn mower.
     */
    private static final int SPEED = 4;

    private final CollisionManager collisionManager;

    /**
     * Constructs a new LawnMowerImpl with the given position and hitbox type.
     *
     * @param position   the initial position of the mower; must not be {@code null}.
     * @param hitBoxType the type of hitbox to assign to this mower.
     */
    public LawnMowerImpl(final Position position, final HitBoxFactory.HitBoxType hitBoxType) {
        super(position, hitBoxType);
        this.collisionManager = new CollisionManagerImpl();
    }

    /**
     * Updates the mower's state: moves it and handles collisions with zombies.
     *
     * @param deltaTime       the time elapsed since the last update (in ms).
     * @param entitiesManager the manager that contains all game entities.
     */
    @Override
    public void update(final long deltaTime, final EntitiesManager entitiesManager) {
        final Optional<Zombie> zombie = this.collisionManager.handleCollision(this, entitiesManager)
                .map(entity -> (Zombie) entity);
        if (zombie.isPresent()) {
            zombie.get().forceKill();
            if (!zombie.get().isAlive()) {
                entitiesManager.addKill();
                entitiesManager.removeEntity(zombie.get());
            }
        }
        this.move();
        final List<Entity> snapshot = new ArrayList<>(entitiesManager.getEntities());
        final double epsilon = 1e-6;
        for (final Entity e : snapshot) {
            if (e instanceof Zombie z
                    && Math.abs(z.getPosition().y() - this.getPosition().y()) < epsilon
                    && this.getHitBox().isColliding(z.getHitBox())) {

                z.forceKill();
                if (!z.isAlive()) {
                    entitiesManager.addKill();
                    entitiesManager.removeEntity(z);
                }
            }
        }
    }

    /**
     * Moves the lawn mower forward based on its speed and updates its hitbox.
     */
    private void move() {
        final double move = SPEED * (1 / 100.0);
        final double newX = this.getPosition().x() + move;
        this.setPosition(new Position(newX, this.getPosition().y()));
        this.getHitBox().update(this.getPosition());
    }
}
