package model;

import java.util.List;

import model.collidable.terrain.Terrain;
import model.explosion.Explosion;
import model.physics.particle.environment.Environment;
import model.player.Player;
import model.projectile.Projectile;

/**
 *
 * @author Oleg
 *
 */
public interface Model {

    /**
     *
     * @return enviroment
     */
    Environment getEnviroment();

    /**
     *
     * @return terrain
     */
    Terrain getTerrain();

    /**
     *
     * @return a list of players
     */
    List<Player> getPlayers();

    /**
     *
     * @return a list of flying projectiles
     */
    List<Projectile> getFlyingProjectiles();

    /**
     *
     * @return landing explosion.
     */
    List<Explosion> getLandingExplosion();

    /**
     *
     * @return landing explosion.
     */
    List<Explosion> getTankExplosion();

    /**
     *
     * @param projectile
     *            projectile
     */
    void addProjectile(Projectile projectile);

    /**
     *
     * @param explosion
     *            explosion
     */
    void addLandingExplosion(Explosion explosion);

    /**
     *
     * @param explosion
     *            explosion
     */
    void addTankExplosion(Explosion explosion);

    /**
     * removes all the projectiles from the battelfield.
     */
    void removeProjectilesFromBattelfield();

    /**
     * removes all the explosions from the battelfield.
     */
    void removeTankExplosions();

    /**
     * removes all the explosions from the battelfield.
     */
    void removeLandingExplosions();
}
