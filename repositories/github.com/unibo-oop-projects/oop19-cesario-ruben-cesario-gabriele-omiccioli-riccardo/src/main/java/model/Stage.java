package model;

import java.util.Collection;

import model.entity.Entity;
import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.weapon.Weapon.Projectile;

/**
 *  Models the environment where the game will be resolving.
 *  This environment is defined by its dimensions, some enemies, the player and all shot projectiles. 
 *  Also, the harshness of the environment increases as the player progresses in the game.
 */
public interface Stage {

    /**
     * Returns the enemies contained by this stage.
     * @return the enemies contained by this stage
     */
    Collection<EnemyShip> enemies();

    /**
     * Adds an enemy to the enemies contained by this stage.
     * @param enemy Enemy to be added
     */
    void spawnEnemy(EnemyShip enemy);

    /**
     * Adds some enemies to the enemies contained by this stage.
     * @param enemies Enemies to be added
     */
    default void spawnEnemies(Collection<EnemyShip> enemies) {
        for (EnemyShip enemy : enemies) {
            spawnEnemy(enemy);
        }
    }

    /**
     * Remove an enemy from the enemies contained by this stage.
     * @param enemy Enemy to be added
     */
    void despawnEnemy(EnemyShip enemy);

    /**
     * Remove some enemies from the enemies contained by this stage.
     * @param enemies Enemies to be added
     */
    default void despawnEnemies(Collection<EnemyShip> enemies) {
        for (EnemyShip enemy : enemies) {
            despawnEnemy(enemy);
        }
    }

    /**
     * Returns the projectiles contained by this stage.
     * @return The projectiles contained by this stage
     */
    Collection<Projectile> projectiles();

    /**
     * Adds a projectile to the projectiles contained by this stage.
     * @param projectile Projectile to be added
     */
    void spawnProjectile(Projectile projectile);

    /**
     * Adds some projectiles to the projectiles contained by this stage.
     * @param projectiles Projectiles to be added
     */
    default void spawnProjectiles(Collection<Projectile> projectiles) {
        for (Projectile projectile : projectiles) {
            spawnProjectile(projectile);
        }
    }

    /**
     * Remove a projectile from the projectiles contained by this stage.
     * @param projectile Projectile to be added
     */
    void despawnProjectile(Projectile projectile);

    /**
     * Remove some projectiles from the projectiles contained by this stage.
     * @param projectiles Projectiles to be added
     */
    default void despawnProjectiles(Collection<Projectile> projectiles) {
        for (Projectile projectile : projectiles) {
            despawnProjectile(projectile);
        }
    }

    /**
     * Returns the player currently playing this stage.
     * @return the player currently playing this stage
     */
    PlayerShip player();

    /**
     * Sets the current player of this stage to a new player.
     * @param player Player who will take control of the player ship
     */
    void spawnPlayer(PlayerShip player);

    /**
     * Checks if the entity belongs to the player faction.
     * @param entity Entity to be checked
     * @return True if the entity belongs to the player's faction, false otherwise
     */
    boolean isInPlayerFaction(Entity entity);

    /**
     * Checks if the entity belongs to the enemies faction.
     * @param entity Entity to be checked
     * @return True if the entity belongs to the enemies faction, false otherwise
     */
    boolean isInEnemyFaction(Entity entity);

}
