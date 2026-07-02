package model;

import java.util.ArrayList;
import java.util.Collection;

import model.entity.Entity;
import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.weapon.Weapon.Projectile;

/**
 * A Stage implementation.
 * @see Stage
 */
public class StageImpl implements Stage {

    private final Collection<EnemyShip> enemyShips = new ArrayList<>();
    private final Collection<Projectile> projectiles = new ArrayList<>();
    private PlayerShip playerShip;

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<EnemyShip> enemies() {
        return this.enemyShips;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spawnEnemy(final EnemyShip enemy) {
        this.enemyShips.add(enemy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void despawnEnemy(final EnemyShip enemy) {
        this.enemyShips.remove(enemy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Projectile> projectiles() {
        return this.projectiles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spawnProjectile(final Projectile projectile) {
        this.projectiles.add(projectile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void despawnProjectile(final Projectile projectile) {
        this.projectiles.remove(projectile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerShip player() {
        return this.playerShip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spawnPlayer(final PlayerShip player) {
        this.playerShip = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInPlayerFaction(final Entity entity) {
        return this.playerShip.equals(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInEnemyFaction(final Entity entity)  {
        return this.enemyShips.contains(entity);
    }

}
