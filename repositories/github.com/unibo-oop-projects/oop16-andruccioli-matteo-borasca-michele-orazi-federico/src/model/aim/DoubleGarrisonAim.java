package model.aim;

import model.player.PlayerInfo;

/**
 * 
 * This Class models the concept of a DoubleGarrisonAim. This Aim consists in
 * conquering a certain amount of states and defending them with a specified
 * number of tanks.
 *
 */
public class DoubleGarrisonAim extends AbstractStateAim {

    private static final long serialVersionUID = 3577342721370780279L;
    private static final int TANKS_ON_EACH_STATE = 2;

    /**
     * @param numStates
     *            Number of states the player must own to win the game.
     * 
     * @param player
     *            player this aim has been assigned to.
     * 
     */
    public DoubleGarrisonAim(final int numStates, final PlayerInfo player) {
        super(numStates, player, AimLabel.DGA);
    }

    private boolean checkNumTanksOnStates() {

        if (!checkNumStates()) {
            return false;
        }

        int suitableStates = 0;

        suitableStates += super.getPlayer().getStates().stream()
                .filter(state -> state.getTanks() >= TANKS_ON_EACH_STATE).count();

        return suitableStates >= super.getNumStates() ? true : false;
    }

    @Override
    public boolean aimAchieved() {
        return checkNumTanksOnStates();
    }

    @Override
    public String toString() {
        return "Conquer " + super.getNumStates() + "states and defend those with " + TANKS_ON_EACH_STATE + " tanks";
    }

}
