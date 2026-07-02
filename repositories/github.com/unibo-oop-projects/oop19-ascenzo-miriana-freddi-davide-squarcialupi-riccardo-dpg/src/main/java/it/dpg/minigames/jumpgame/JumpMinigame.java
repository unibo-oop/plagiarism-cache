package it.dpg.minigames.jumpgame;

import it.dpg.maingame.view.View;
import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.jumpgame.controller.JumpMinigameCycle;
import it.dpg.minigames.jumpgame.view.JumpMinigameView;
import it.dpg.minigames.jumpgame.view.JumpMinigameViewImpl;

/**
 * Minigame in which the players use the directional arrow left and right
 * to move a square that jumps on platforms and try to go the highest possible.
 * Touching window's borders with the square is game over
 * @author Davide Picchiotti
 * */

public class JumpMinigame extends AbstractMinigame {

    private JumpMinigameView view = new JumpMinigameViewImpl();

    @Override
    public int getMaxScore() {
        return 100;
    }

    @Override
    protected View createView() {
        return view;
    }

    @Override
    protected MinigameCycle createCycle() {
        return new JumpMinigameCycle(view);
    }
}
