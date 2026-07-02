package it.dpg.minigames.ballgame.controller;

import it.dpg.maingame.view.View;
import it.dpg.minigames.ballgame.view.BallView;
import it.dpg.minigames.ballgame.view.BallViewImpl;
import it.dpg.minigames.base.controller.AbstractMinigame;
import it.dpg.minigames.base.controller.Minigame;
import it.dpg.minigames.base.controller.MinigameCycle;

import java.awt.*;

public class BallMinigame extends AbstractMinigame implements Minigame {
    private final int maxScore = 999;
    private BallView view;
    private BallGamecycle cycle;

    public BallMinigame() {
    }

    private void createComponents() {
        if (view == null || cycle == null) {
            BallGamecycleImpl c = new BallGamecycleImpl(maxScore);
            this.view = new BallViewImpl(Toolkit.getDefaultToolkit().getScreenSize().height * 0.7, c);
            c.setView(view);
            this.cycle = c;
        }
    }

    @Override
    public int getMaxScore() {
        return maxScore;
    }

    @Override
    public View createView() {
        createComponents();
        return view;
    }

    @Override
    public MinigameCycle createCycle() {
        createComponents();
        return cycle;
    }
}
