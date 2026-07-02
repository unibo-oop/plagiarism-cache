package it.dpg.minigames.punchygame.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Basic implementation of World
 * @author Davide Picchiotti
 * @see World
 * */

public class WorldImpl implements World {
    private Random r = new Random();

    private Deque<Direction> sacks;
    private Score score;
    private Timer timer;
    private Boxer boxer;
    private boolean gameOver;

    private static final int MAX_SACKS = 10;

    public WorldImpl() {
        score = new ScoreImpl();
        timer = new TimerImpl();
        boxer = new BoxerImpl();
        gameOver = false;
        generateSacks();
    }

    @Override
    public boolean checkSackHit(final Direction direction) {
        boxer.setDirection(direction);

        if(sacks.getFirst() == direction) {
            score.incrementScore();
            timer.timerIncrease();

            sacks.removeFirst();
            sacks.addLast(randomDirection());

            return true;
        } else {
            score.resetCombo();

            return false;
        }
    }


    @Override
    public List<Direction> getSacks() {
        return new ArrayList<>(sacks);
    }

    @Override
    public int getScore() {
        return score.getPoints();
    }

    @Override
    public int getScoreMultiplier() {
        return score.getMultiplier();
    }

    @Override
    public float getTimer() {
        return timer.getTimeLeft();
    }

    @Override
    public void updateTimer(final float elapsed) {
        timer.timerDecrease(elapsed);
        if(timer.getTimeLeft() <= 0) {
            gameOver = true;
        }
    }

    @Override
    public Direction getBoxerDirection() {
        return boxer.getDirection();
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    private void generateSacks() {
        sacks = Stream
                .generate(this::randomDirection)
                .limit(MAX_SACKS)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private Direction randomDirection() {
        Direction[] directions = Direction.values();
        return directions[r.nextInt(directions.length)];
    }
}
