package it.unibo.isaccoop.model.collision;

import java.util.List;

import it.unibo.isaccoop.model.boundingbox.CircleBoundingBox;
import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.enemy.Enemy;
import it.unibo.isaccoop.model.item.Item;
import it.unibo.isaccoop.model.player.Player;
import it.unibo.isaccoop.model.weapon.WeaponShot;

/**
 * Factory for collision check.
 */
public interface CollisionCheckFactory {
    /**
     * Method for checking the collision between the player and the item.
     *
     * @param p reference to Player.
     * @param i list of item to check collision.
     *
     * @return interface implementation of CollisionCheck.
     */
    CollisionCheck getCollisionWithItemChecker(Player p, List<? extends Item> i);
    /**
     * Get collision with player shot.
     * @param p reference to player.
     * @param i list of mapElements to check collision, in this case Enemy.
     *
     * @return CollisionCheck refer to player shot.
     */
    CollisionCheck getCollisionPlayerShotChecker(Player p, List<Enemy> i);
    /**
     * Get collision with enemy.
     * @param p reference to player.
     * @param i list of mapElements to check collision, in this case Enemy.
     *
     * @return CollisionCheck refer to enemy.
     */
    CollisionCheck getCollisionWithEnemyChecker(Player p, List<Enemy> i);
    /**
     * Get collision with enemy shot.
     * @param p reference to player.
     * @param i list of mapElements to check collision, in this case Enemy.
     *
     * @return CollisionCheck refer to enemy shot.
     */
    CollisionCheck getCollisionWithEnemyShotChecker(Player p, List<Enemy> i);

    /**
     *  Get collision between shots and player or enemies, in order to remove the target shot.
     *
     *  @param shots weapon shots
     *  @param pos enemy position or player position
     *  @param box player or enemy bounding box
     *  @return CollisionCheck refer to target shot
     * */
    CollisionCheck getShotsCollisionToRemoveChecker(List<WeaponShot> shots, Point2D pos, CircleBoundingBox box);
}
