package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import model.collidable.terrain.Terrain;
import model.explosion.Explosion;
import model.physics.particle.environment.Environment;
import model.player.Player;
import model.player.PlayerImpl;
import model.projectile.Projectile;

/**
 *
 * @author Oleg
 *
 */
public final class ModelImpl implements Model {
    private final Environment enviroment;
    private final Terrain terrain;
    private final List<Player> players;
    private final List<Projectile> projectiles;
    private final List<Explosion> landingExplosion;
    private final List<Explosion> tankExplosion;

    /**
     *
     * @param enviroment
     *            enviroment
     * @param terrain
     *            terrain
     * @param players
     *            players
     */
    public ModelImpl(final Environment enviroment, final Terrain terrain, final List<Player> players) {
        super();
        this.enviroment = enviroment;
        this.terrain = terrain;
        this.players = players.stream().peek(p -> new PlayerImpl(p)).collect(Collectors.toList());
        projectiles = new ArrayList<>();
        landingExplosion = new ArrayList<>();
        tankExplosion = new ArrayList<>();
    }

    @Override
    public Environment getEnviroment() {
        return enviroment;
    }

    @Override
    public Terrain getTerrain() {
        return terrain;
    }

    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    @Override
    public List<Projectile> getFlyingProjectiles() {
        return Collections.unmodifiableList(projectiles);
    }

    @Override
    public void addProjectile(final Projectile projectile) {
        projectiles.add(projectile);

    }

    @Override
    public void addLandingExplosion(final Explosion explosion) {
        landingExplosion.add(explosion);
    }

    @Override
    public void addTankExplosion(final Explosion explosion) {
        tankExplosion.add(explosion);

    }

    @Override
    public List<Explosion> getLandingExplosion() {
        return Collections.unmodifiableList(landingExplosion);
    }

    @Override
    public List<Explosion> getTankExplosion() {
        return Collections.unmodifiableList(tankExplosion);
    }

    @Override
    public void removeProjectilesFromBattelfield() {
        projectiles.clear();
    }

    @Override
    public void removeLandingExplosions() {
        landingExplosion.clear();
    }

    @Override
    public void removeTankExplosions() {
        tankExplosion.clear();

    }

}
