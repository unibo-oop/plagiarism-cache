package unibo.exiled.model.combat;

import java.util.Optional;
import java.util.Set;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.utilities.Position;

import javax.annotation.concurrent.Immutable;

/**
 * The model wrapping the current combat.
 */
@Immutable
public interface CombatModel {
    /**
     * Sets the player, the enemy and the combat position of the new combat.
     * 
     */
    void newCombat();

    /**
     * Returns the player thas is fighting.
     *
     * @return the player thas is fighting.
     */
    Optional<Player> getPlayer();

    /**
     * Returns the enemy thas is fighiting.
     *
     * @return the enemy that is fighiting.
     */
    Optional<Enemy> getEnemy();

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
     * Returns the enemy name.
     *
     * @return the enemy name.
     */
    String getEnemyName();

    /**
     * Returns the enemy health.
     *
     * @return the enemy health.
     */
    double getEnemyHealth();

    /**
     * Returns the enemy health cap.
     *
     * @return the enemy health cap.
     */
    double getEnemyHealthCap();

    /**
     * Returns the moves of the enemy.
     *
     * @return the moves of the enemy.
     */
    Set<MagicMove> getEnemyMoves();

    /**
     * Returns the class name of the enemy.
     * 
     * @return the class name of the enemy.
     */
    String getEnemyClassName();

    /**
     * Returns the combat position.
     *
     * @return the combat position.
     */
    Position getCombatPosition();

    /**
     * Returns if the player has a move to learn, that couldn't learn because his move set was
     * at max capacity.
     * 
     * @return if the player has a move to learn.
     */
    boolean needsPlayerToChangeMove();
}
