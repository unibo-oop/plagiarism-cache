package unibo.exiled.model.combat;

import java.util.Optional;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.utilities.Position;

/**
 * The model rapresenting the current combat.
 */
public interface Combat {

    /**
     * Gets the combat position.
     * 
     * @return the combat position.
     */
    Position getCombatPosition();

    /**
     * Sets the combat position.
     * 
     * @param combatPosition the position.
     */
    void setCombatPosition(Position combatPosition);

    /**
     * Gets the combat status.
     * 
     * @return the combat status.
     */
    CombatStatus getCombatStatus();

    /**
     * Sets the combat status.
     * 
     * @param status the new status.
     */
    void setCombatStatus(CombatStatus status);

    /**
     * Returns the player thas is fighting.
     * 
     * @return the player thas is fighting.
     */
    Optional<Player> getPlayer();

    /**
     * Sets the combat player.
     * 
     * @param player the player fighting.
     */
    void setPlayer(Optional<Player> player);

    /**
     * Returns if the player has a move to learn, that couldn't learn because his move set was
     * at max capacity.
     * 
     * @return if the player has a move to learn.
     */
    boolean needsPlayerToChangeMove();

    /**
     * Returns the enemy fighiting.
     * 
     * @return the enemy fighiting.
     */
    Optional<Enemy> getEnemy();

    /**
     * Sets the enemy fighting.
     * 
     * @param enemy the enemy fighting.
     */
    void setEnemy(Enemy enemy);
}
