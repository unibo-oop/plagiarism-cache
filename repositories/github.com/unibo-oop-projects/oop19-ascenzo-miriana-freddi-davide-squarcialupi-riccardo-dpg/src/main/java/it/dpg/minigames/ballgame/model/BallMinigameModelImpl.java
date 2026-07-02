package it.dpg.minigames.ballgame.model;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class BallMinigameModelImpl implements BallMinigameModel {
    private final BallEnvironmentFactory factory;
    private BallEnvironment env;
    private boolean levelIsReady = false;
    private volatile boolean isGoingLeft = false;
    private volatile boolean isGoingRight = false;
    private volatile boolean isGoingUp = false;
    private volatile boolean isGoingDown = false;
    private final int maxScore;
    private double timePassed = 0;
    private final double deltaT;

    public BallMinigameModelImpl(int expectedFPS, int maxScore) {
        this.maxScore = maxScore;
        this.deltaT = 1d / expectedFPS;
        this.factory = new BallEnvironmentFactoryImpl(expectedFPS);
    }

    private void checkLevelSetup() {
        if (!levelIsReady) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void setupLevel(BallMinigameLevel level) {
        env = factory.createEnvironment(level);
        levelIsReady = true;
    }

    @Override
    public void setGoingLeft(boolean isGoingLeft) {
        this.isGoingLeft = isGoingLeft;
    }

    @Override
    public void setGoingRight(boolean isGoingRight) {
        this.isGoingRight = isGoingRight;
    }

    @Override
    public void setGoingUp(boolean isGoingUp) {
        this.isGoingUp = isGoingUp;
    }

    @Override
    public void setGoingDown(boolean isGoingDown) {
        this.isGoingDown = isGoingDown;
    }


    @Override
    public Pair<Double, Double> calculateNextFrame() {
        checkLevelSetup();
        timePassed += deltaT;
        env.nextFrame(isGoingUp, isGoingDown, isGoingLeft, isGoingRight);
        return new ImmutablePair<>(env.getX(), env.getY());
    }

    @Override
    public int getScore() {
        int score = maxScore - ((int) (timePassed * 20));
        return Math.max(score, 0);
    }

    @Override
    public boolean isOver() {
        checkLevelSetup();
        return env.goalReached();
    }
}
