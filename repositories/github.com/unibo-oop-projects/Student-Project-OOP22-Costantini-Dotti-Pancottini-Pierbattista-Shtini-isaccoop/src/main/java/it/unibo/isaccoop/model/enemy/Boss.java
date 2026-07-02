package it.unibo.isaccoop.model.enemy;

import java.util.Map;
import java.util.Optional;

import it.unibo.isaccoop.graphics.factory.ConcreteEnemyGraphicsComponentFactory;
import it.unibo.isaccoop.model.action.MovementStrategy;
import it.unibo.isaccoop.model.action.NonShootingMovementStrategy;
import it.unibo.isaccoop.model.action.ShootingHitStrategy;
import it.unibo.isaccoop.model.action.ShootingMovementStrategy;
import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.weapon.BaseWeaponShot;
import it.unibo.isaccoop.model.weapon.TimeIntervalWeapon;

/**
 * The class for the boss.
 * */
public class Boss extends AbstractEnemy {

    /**
     * The time to wait to change the boss's attack type.
     * */
    private static final int CHANGE_TIME = 3_000;

    /**
     * Weapon Time interval between shots.
     * */
    private static final double WEAPON_INTERVAL = 1_000;

    /**
     * The time since the last change.
     * */
    private long lastChangeTime;

    private final Map<String, MovementStrategy> movementStrategies;

    /**
     * Boss constructor.
     * */
    public Boss() {
        super(EnemyHearts.BOSS_HEARTS, new ShootingHitStrategy(new TimeIntervalWeapon(Boss.WEAPON_INTERVAL,
                (start, direction) -> new BaseWeaponShot(start, direction,
                new ConcreteEnemyGraphicsComponentFactory().getBossBaseWeaponShotGraphicsComponent()))),
                new NonShootingMovementStrategy(),
                new ConcreteEnemyGraphicsComponentFactory().getBossGraphicsComponent());
        this.lastChangeTime = System.currentTimeMillis();
        this.movementStrategies = Map.of(
                "shooting", new ShootingMovementStrategy(),
                "nonShooting", new NonShootingMovementStrategy());
    }

    /**
     * Change the type of attack of the boss based on time, with shooting or not.
     */
    public void changeMode() {
        if (System.currentTimeMillis() - this.lastChangeTime >= Boss.CHANGE_TIME) {
            this.lastChangeTime = System.currentTimeMillis();
            this.updateMovementStrategy();
        }
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void hit(final Point2D playerPosition) {
        this.changeMode();
        super.getHitStrategy().hit(Optional.of(playerPosition.sub(this.getCoords())), this);
    }

    /**
     * Update movement strategy between shooting and non shooting movement strategies.
     * */
    private void updateMovementStrategy() {
        if (super.getMovementStrategy() instanceof ShootingMovementStrategy) {
            super.setMovementStrategy(this.movementStrategies.get("nonShooting"));
        } else {
            super.setMovementStrategy(this.movementStrategies.get("shooting"));
        }
    }

}
