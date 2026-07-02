package unibo.exiled.controller;

import java.util.Optional;

import javax.annotation.concurrent.Immutable;

import unibo.exiled.model.combat.CombatStatus;
import unibo.exiled.utilities.Position;
import unibo.exiled.view.CombatView;

/**
 * Controller for managing the combat.
 */
@Immutable
public interface CombatController {
    /**
     * Initialize the combat controller.
     * 
     * @param combatPosition the combat position.
     */
    void initializeCombat(Position combatPosition);

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
     * Returns the enemy class name.
     * 
     * @return the enemy class name.
     */
    String getEnemyClassName();

    /**
     * Returns a string describing the last move the enemy used.
     * 
     * @return a string describing the last move the enemy used.
     */
    String getLastMoveLabel();

    /**
     * Returns a string describing the attack modifier.
     * 
     * @return a string describing the attack modifier.
     */
    String getAttackerModifierLabel();

    /**
     * Returns a string describing the defender modifier.
     * 
     * @return a string describing the defender modifier.
     */
    String getDefenderModifierLabel();

    /**
     * Returns the description of the move performed.
     * 
     * @return the description of the move performed.
     */
    String getMoveDescription();

    /**
     * Returns the current status of the combat.
     * 
     * @return the current status of the combat.
     */
    CombatStatus getCombatStatus();

    /**
     * Returns if the player has a move to learn, that couldn't learn because his move set was
     * at max capacity.
     * 
     * @return if the player has a move to learn.
     */
    boolean needsPlayerToChangeMove();

    /**
     * Permforms an attack routine.
     * 
     * @param isPlayerAttacking if the player is attacking.
     * @param playerMoveName    the name of the move done by the player.
     * @param gameController    the game controller.
     * @param combatView        the combat view.
     */
    void attack(boolean isPlayerAttacking, Optional<String> playerMoveName, GameController gameController,
            CombatView combatView);
}
