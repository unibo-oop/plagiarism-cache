package model.aim;

import model.player.PlayerInfo;
import utils.enumerations.Color;

/**
 * 
 * This Class models the concept of a DestroyAim. This aim consists in dealing
 * the fatal blow to a designed player or conquering a certain amount of states
 * if this first aim is not available.
 *
 */
public class DestroyAim extends AbstractStateAim {

    private static final long serialVersionUID = -8075668761399722088L;
    private boolean secondAimEnabled;
    private final Color enemy;
    private PlayerInfo lastDefeated;

    /**
     * @param numStates
     *            Number of states the player must own to win the game.
     * 
     * @param player
     *            player this aim has been assigned to.
     *
     * @param enemy
     *            color that identifies the enemy player.
     * 
     */
    public DestroyAim(final int numStates, final PlayerInfo player, final Color enemy) {
        super(numStates, player, AimLabel.DA);
        this.enemy = enemy;
        this.lastDefeated = null;
        this.secondAimEnabled = player.getColor().equals(enemy) ? true : false; 
    }

    /**
     * 
     * Enables the second aim.
     *
     */
    public void switchAim() {
        this.secondAimEnabled = true;
    }

    /**
     * 
     * @return color that identifies the player to defeat.
     *
     */
    public Color getEnemy() {
        return enemy;
    }

    /**
     * @param lastDefeated
     *            lastDefeated must be updated before any call of method
     *            "aimAchieved" .
     *
     */
    public void setLastDefeated(final PlayerInfo lastDefeated) {
        this.lastDefeated = lastDefeated;
    }

    @Override
    public boolean aimAchieved() {
        if (this.secondAimEnabled) {
            return checkNumStates();
        } else if (this.lastDefeated != null) {
            if (this.enemy.equals(this.lastDefeated.getColor())) {
                return true;
            }
            this.lastDefeated = null;
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.secondAimEnabled) {
            return "Your aim is no more available! New Aim: conquer " + super.getNumStates() + " states";
        }
        return "Destroy " + this.enemy.toString() + " army";
    }

    /**
     * 
     * @return true if switchAim has been called
     */
    public boolean isSecondAimEnabled() {
        return secondAimEnabled;
    }

}
