package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.Direction;

/**
 * Extension of AbstractPunch that models a punch to the right
 * @author Davide Picchiotti
 * @see AbstractPunch
 * */

public class PunchRight extends AbstractPunch {

    @Override
    protected Direction getPunchDirection() {
        return Direction.RIGHT;
    }
}
