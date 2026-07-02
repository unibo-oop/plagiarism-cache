package model.player;

import java.util.List;

import controller.matchmanager.GameModeType;
import javafx.util.Pair;
import model.collidable.tank.Tank;
import model.match.ScoreManager;
import model.projectile.ProjectileType;

/**
 *
 * @author Oleg
 *
 */
public interface Player {

    /**
     *
     * @return tank
     */
    Tank getTank();

    /**
     *
     * @return name
     */
    String getName();

    /**
     *
     * @return last shot projectyle by this player
     */
    ProjectileType getLastShotProjectile();

    /**
     *
     * @return player's score manager
     */
    ScoreManager getScoreManager();

    /**
     *
     * @return list of allowed angles
     */
    List<Integer> getAllowedAngles();

    /**
     *
     * @return inventory of projectiles
     */
    List<Pair<ProjectileType, Integer>> getInventory();

    /**
     *
     * @return true if a player is alive, false if not
     */
    boolean isAlive();

    /**
     *
     * @param alive
     *            alive
     */
    void setAlive(boolean alive);

    /**
     *
     * @param allowedAngles
     *            allowed angles
     */
    void setAllowedAngles(List<Integer> allowedAngles);

    /**
     *
     * @param specialProjectyle
     *            special projectyle
     * @param gameMode
     *            gameMode
     */
    void addProjectile(boolean specialProjectyle, GameModeType gameMode);

    /**
     *
     * @param projectileType
     *            projectile type
     * @param gameMode
     *            game mode
     */
    void removeProjectile(ProjectileType projectileType, GameModeType gameMode);

}
