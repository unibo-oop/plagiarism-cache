package model.aim;

import model.player.PlayerInfo;

/**
 * 
 * Class that implements a behavior common to those aim which need to check
 *      number of states owned by the player.
 *
 */
public abstract class AbstractStateAim implements Aim {

    private static final long serialVersionUID = 2716708729276439165L;
    private final int numStates;
    private final PlayerInfo player;
    private final AimLabel type;

    /**
     * @param numStates
     *          Number of states the player must own to win the game.
     * 
     * @param player
     *          player this aim has been assigned to.
     * 
     * @param type
     *            this aim type.
     */
    public AbstractStateAim(final int numStates, final PlayerInfo player, final AimLabel type) {
        super();
        this.numStates = numStates;
        this.player = player;
        this.type = type;
    }

    @Override
    public abstract boolean aimAchieved();



    /**
     * 
     *@return whether or not the number of states controlled by the player is enough.
     *
     */
    public boolean checkNumStates() {
        return this.player.getStates().size() >= this.numStates ? true : false;
    }

    /**
     * 
     *@return  Number of states the player must own to win the game.
     *
     */
    public int getNumStates() {
        return numStates;
    }

    @Override
    public PlayerInfo getPlayer() {
        return player;
    }

    @Override
    public AimLabel getType() {
        return this.type;
    }

}
