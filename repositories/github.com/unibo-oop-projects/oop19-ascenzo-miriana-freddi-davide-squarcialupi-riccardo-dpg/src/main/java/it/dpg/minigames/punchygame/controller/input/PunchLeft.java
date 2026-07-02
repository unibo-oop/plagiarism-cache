package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.Direction;

/**
 * Extension of AbstractPunch that models a punch to the left
 * @author Davide Picchiotti
 * @see AbstractPunch
 * */

public class PunchLeft extends AbstractPunch {

    @Override
    protected Direction getPunchDirection() {
        return Direction.LEFT;
    }
}
