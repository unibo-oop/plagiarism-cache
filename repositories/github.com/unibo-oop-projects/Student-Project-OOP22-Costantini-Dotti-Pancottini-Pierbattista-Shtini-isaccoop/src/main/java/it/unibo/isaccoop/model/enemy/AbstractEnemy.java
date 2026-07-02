package it.unibo.isaccoop.model.enemy;

import java.util.List;
import java.util.Optional;

import it.unibo.isaccoop.graphics.GraphicsComponent;
import it.unibo.isaccoop.model.action.HitStrategy;
import it.unibo.isaccoop.model.action.MovementStrategy;
import it.unibo.isaccoop.model.action.ShootingHitStrategy;
import it.unibo.isaccoop.model.boundingbox.BoundingBox;
import it.unibo.isaccoop.model.boundingbox.RectBoundingBox;
import it.unibo.isaccoop.model.common.AbstractMapElement;
import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.player.PlayerStat;
import it.unibo.isaccoop.model.weapon.WeaponShot;

/**
 * AbstractEnemy abstract class that extends AbstractMapElement and implements Enemy,
 * it is used as a common container for enemies state and behavior.
 * */
public abstract class AbstractEnemy extends AbstractMapElement implements Enemy {

    /**
     * Attribute used to update enemy position incrementally.
     * */
    private static final Double SPEED = 0.5;

    private MovementStrategy movementStrategy;

    private HitStrategy hitStrategy;

    /**
     * Attribute used to store enemy hearts.
     * */
    private Double hearts;

    /**
     * Constructor for {@link AbstractEnemy}.
     *
     * @param maxHearts max hearts number for the enemy
     * @param hitStrategy the strategy for the hit
     * @param movementStrategy the strategy for the movement
     * @param graphicComponent enemy graphics component
     * */
    public AbstractEnemy(final EnemyHearts maxHearts, final HitStrategy hitStrategy, final MovementStrategy movementStrategy,
            final GraphicsComponent graphicComponent) {
        super(ElementsRadius.ENEMY, graphicComponent);
        this.hitStrategy = hitStrategy;
        this.movementStrategy = movementStrategy;
        this.hearts = maxHearts.getMaxHearts();
    }

    /**
     * Delegates movement to {@link MovementStrategy}.
     *
     * @param playerPosition in order to move towards the player if needed
     * */
    @Override
    public void move(final Point2D playerPosition, final BoundingBox containerBox) {
        final Point2D newPos = this.getMovementStrategy().move(this.getCoords(), playerPosition);

        if (containerBox instanceof RectBoundingBox
                && !this.getBox().isCollidingWithRecPerimeter(newPos, (RectBoundingBox) containerBox)) {
            super.setCoords(newPos);
        }
    }

    /**
     * Delegates hit to {@link HitStrategy}.
     *
     * @param playerPosition in order to hit towards the player if needed
     * */
    @Override
    public void hit(final Point2D playerPosition) {
        this.hitStrategy.hit(Optional.empty(), this);
    }

    /**
     *  Method to be called when a collision between player and enemy is detected, it decreases
     *  the player's hearts by on.
     *
     *  @param player event target
     * */
    @Override
    public void onHit(final PlayerStat player) {
        if (!player.isDead()) {
            player.setHeart(player.getHeart() - 1);
        }
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Optional<List<WeaponShot>> getWeaponShots() {
        return this.getHitStrategy() instanceof ShootingHitStrategy
            ? Optional.of(((ShootingHitStrategy) this.getHitStrategy()).getWeaponShots())
            : Optional.empty();
    }

    /**
     *  Get current enemy hearts.
     *
     *  @return current hearts number
     * */
    @Override
    public Double getHearts() {
        return this.hearts;
    }

    /**
     * Get enemy speed.
     *
     * @return enemy speed
     * */
    public static double getSpeed() {
        return SPEED;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public final boolean isDead() {
        return this.getHearts() <= 0;
    }

    /**
     * Enum containing enemy max hearts configurations.
     * */
    protected enum EnemyHearts {
        /**
         * Enemy max hearts.
         * */
        ENEMY_HEARTS(3.0),

        /**
         * Boss max hearts.
         * */
        BOSS_HEARTS(10.0);

        private final Double maxHearts;

        EnemyHearts(final Double maxHearts) {
            this.maxHearts = maxHearts;
        }

        /**
         * Get enum max hearts number.
         *
         * @return max hearts number
         * */
        public Double getMaxHearts() {
            return this.maxHearts;
        }
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public HitStrategy getHitStrategy() {
        return this.hitStrategy;
    }

    /**
     * {@inheritDoc}
     * */
    public MovementStrategy getMovementStrategy() {
        return this.movementStrategy;
    }

    /**
     * Set enemy movement strategy.
     * @param movementStrategy
     * */
    public void setMovementStrategy(final MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    /**
     * Set enemy hit strategy.
     * @param hitStrategy
     * */
    public void setHitStrategy(final HitStrategy hitStrategy) {
        this.hitStrategy = hitStrategy;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void setHearts(final Double hearts) {
        if (!this.isDead()) {
            this.hearts -= hearts;
        }
    }

}
