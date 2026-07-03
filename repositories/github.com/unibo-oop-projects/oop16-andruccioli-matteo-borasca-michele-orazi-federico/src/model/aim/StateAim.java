package model.aim;

import model.player.PlayerInfo;

/**
 * 
 *This Class models the concept of a StateAim. This Aim consists in conquering
 *      a certain amount of states.
 *
 */
public class StateAim extends AbstractStateAim {

    private static final long serialVersionUID = -2813311227742966776L;

    /**
     * @param numStates
     *          Number of states the player must own to win the game.
     * 
     * @param player
     *          player this aim has been assigned to.
     * 
     */
    public StateAim(final int numStates, final PlayerInfo player) {
        super(numStates, player, AimLabel.SA);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean aimAchieved() {
        return checkNumStates();
    }

    @Override
    public String toString() {
        return "Conquer " + super.getNumStates() + " states";
    }
}
