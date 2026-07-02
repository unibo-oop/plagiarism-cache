package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.Direction;
import it.dpg.minigames.punchygame.model.World;

/**
 * Abstract class to implement Input generalization
 * @author Davide Picchiotti
 * @see Input
 * */

public abstract class AbstractPunch implements Input {

    @Override
    public boolean execute(World world) {
        return world.checkSackHit(getPunchDirection());
    }

    /**
     * @return the direction in which the punch is thrown
     * @see Direction
     * */
    protected abstract Direction getPunchDirection();
}
