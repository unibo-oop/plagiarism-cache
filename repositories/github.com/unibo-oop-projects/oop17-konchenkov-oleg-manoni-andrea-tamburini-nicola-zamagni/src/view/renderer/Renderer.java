package view.renderer;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.collidable.tank.Tank;
import model.collidable.terrain.Terrain;
import model.explosion.Explosion;
import model.projectile.Projectile;

/**
 * Represents renderer.
 *
 * @author Oleg
 *
 */
public interface Renderer {

    /**
    *
    */
    void clearScreen();

    /**
     *
     * @param projectile
     *            projectile
     */
    void renderProjectile(Projectile projectile);

    /**
     *
     * @param tank
     *            tank
     */
    void renderTank(Tank tank);

    /**
     *
     * @param tankList
     *            list of tanks
     */
    void renderTankList(List<Tank> tankList);

    /**
     *
     * @param points
     *            points
     */
    @Deprecated
    void renderPolyline(List<Vector2D> points);

    /**
     *
     * @param terrain
     *            terrain
     */
    void renderTerrain(Terrain terrain);

    /**
     *
     * @param explosion
     *            explosion
     */
    void renderExplosion(Explosion explosion);

    /**
     *
     * @param explosionList
     *            explosion list
     */
    void renderExplosionList(List<Explosion> explosionList);

    /**
     *
     * @param terrainWidth
     *            terrain width
     */
    void setTerrainWidth(double terrainWidth);

}
