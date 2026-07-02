package it.dpg.minigames.ballgame.controller;

import it.dpg.minigames.ballgame.model.BallMinigameModel;
import it.dpg.minigames.ballgame.model.BallMinigameModelImpl;
import it.dpg.minigames.ballgame.view.BallView;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.TimeUnit;

public class BallGamecycleImpl implements BallGamecycle {
    private final BallMinigameModel model;
    private BallView view;

    BallGamecycleImpl(int maxScore) {
        this.model = new BallMinigameModelImpl(30, maxScore);
    }

    public void setView(BallView view) {
        this.view = view;
    }

    @Override
    public void signalGoingUp(boolean isGoing) {
        model.setGoingUp(isGoing);
    }

    @Override
    public void signalGoingDown(boolean isGoing) {
        model.setGoingDown(isGoing);
    }

    @Override
    public void signalGoingleft(boolean isGoing) {
        model.setGoingLeft(isGoing);
    }

    @Override
    public void signalGoingRight(boolean isGoing) {
        model.setGoingRight(isGoing);
    }

    private void sleepMillis(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int startCycle() {
        boolean exitCycle = false;
        BallMinigameLevel level = BallMinigameLevel.LEVEL1;
        setup(level);
        startSequence();
        while (!exitCycle) {
            Pair<Double, Double> coords = model.calculateNextFrame();
            this.view.positionBall(coords.getLeft(), 100 - coords.getRight());
            this.view.setScore(model.getScore());
            if (model.isOver()) {
                this.view.setVictory();
                sleepMillis(1500);
                exitCycle = true;
            }
            if (model.getScore() == 0) {
                exitCycle = true;
            }
            sleepMillis(30);
        }
        this.view.closeView();
        return model.getScore();
    }

    private void setup(BallMinigameLevel level) {
        this.view.setupLevel(level);
        this.model.setupLevel(level);
        this.view.setScore(model.getScore());
    }

    private void startSequence() {
        view.setReady();
        sleepMillis(2000);
        view.removeReady();
        view.setGo();
        sleepMillis(1000);
        view.removeGo();
    }
}
