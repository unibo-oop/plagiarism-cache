package model.utils;

import java.util.Collection;
import java.util.List;

import model.entities.Enemy;
import model.hitbox.HitboxCircle;

/**
 * 
 * An utility class to handle all the variables needed by the classes of the
 * model.
 *
 */
public final class ModelVariables {

    private static HitboxCircle p;
    private static Collection<Direction> moveInput;
    private static Collection<Direction> shootInput;
    private static Collection<Enemy> enemies;

    private ModelVariables() {

    }

    /**
     * Set all the internal variable to refer to the parameters.
     * 
     * @param player
     *            The HitboxCircle of the entity player.
     * @param playerMove
     *            The possible directions for the player.
     * @param playerShoot
     *            The directions where the player can shoot.
     * @param enemies
     *            The collection of enemies.
     */
    public static void setAll(final HitboxCircle player, final List<Direction> playerMove,
            final List<Direction> playerShoot, final Collection<Enemy> enemies) {
        setInputs(playerMove, playerShoot);
        setPlayerHitbox(player);
        setEnemies(enemies);
    }

    /**
     * Set the internal variables to refer to the parameters provided.
     * 
     * @param playerMove
     *            The possible directions for the player.
     * @param playerShoot
     *            The directions where the player can shoot.
     */
    public static void setInputs(final Collection<Direction> playerMove, final Collection<Direction> playerShoot) {
        moveInput = playerMove;
        shootInput = playerShoot;
    }

    /**
     * Set the HitboxCircle of the player.
     * 
     * @param player
     *            The position of the entity player.
     */
    public static void setPlayerHitbox(final HitboxCircle player) {
        p = player;
    }

    /**
     * Get the collection of possible move direction of the player.
     * 
     * @return A collection of Direction.
     */
    public static Collection<Direction> getMoveDirection() {
        return moveInput;
    }

    /**
     * Get the collection of possible shoot direction of the player.
     * 
     * @return A collection Direction.
     */
    public static Collection<Direction> getShootDirection() {
        return shootInput;
    }

    /**
     * Get the HitboxCircle of the player.
     * 
     * @return The HitboxCircle of the player.
     */
    public static HitboxCircle getPlayerHitbox() {
        return p;
    }

    /**
     * Get the collection of the enemies.
     * 
     * @return A collection of Enemy.
     */
    public static Collection<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Set the collection of enemies.
     * 
     * @param enemies
     *            The collection of Enemy.
     */
    public static void setEnemies(final Collection<Enemy> enemies) {
        ModelVariables.enemies = enemies;
    }
}
