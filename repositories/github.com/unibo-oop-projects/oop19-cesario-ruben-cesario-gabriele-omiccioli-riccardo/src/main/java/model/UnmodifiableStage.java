package model;

import java.util.Collection;
import java.util.Collections;

import model.entity.Entity;
import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.weapon.Weapon.Projectile;

/**
 * A Stage implementation of an unmodifiable stage.
 */
public class UnmodifiableStage implements Stage {

    private final Stage stage;

    public UnmodifiableStage(final Stage stage) {
        this.stage = stage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<EnemyShip> enemies() {
        return Collections.unmodifiableCollection(this.stage.enemies());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spawnEnemy(final EnemyShip enemy) {
        throw new UnsupportedOperationException("Can't call Stage.spawnEnemy on an UnmodifiableStage.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void despawnEnemy(final EnemyShip enemy) {
        throw new UnsupportedOperationException("Can't call Stage.despawnEnemy on an UnmodifiableStage.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Projectile> projectiles() {
        return Collections.unmodifiableCollection(this.stage.projectiles());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spawnProjectile(final Projectile projectile) {
        throw new UnsupportedOperationException("Can't call Stage.spawnProjectile on an UnmodifiableStage.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void despawnProjectile(final Projectile projectile) {
        throw new UnsupportedOperationException("Can't call Stage.despawnProjectile on an UnmodifiableStage.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerShip player() {
        return this.stage.player();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spawnPlayer(final PlayerShip player) {
        throw new UnsupportedOperationException("Can't call Stage.spawnPlayer on an UnmodifiableStage.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInPlayerFaction(final Entity entity) {
        return this.stage.isInPlayerFaction(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInEnemyFaction(final Entity entity) {
        return this.stage.isInPlayerFaction(entity);
    }

}
