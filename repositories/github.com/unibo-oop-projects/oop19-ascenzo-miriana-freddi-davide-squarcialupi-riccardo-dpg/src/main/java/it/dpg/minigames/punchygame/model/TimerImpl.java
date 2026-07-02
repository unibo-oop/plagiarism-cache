package it.dpg.minigames.punchygame.model;

/**
 * Basic implementation of Timer
 * @author Davide Picchiotti
 * @see Timer
 * */

public class TimerImpl implements Timer {

    private float timeLeft;
    private static final float MAX_TIME = 10f;

    public TimerImpl() {
        timeLeft = MAX_TIME;
    }

    @Override
    public void timerDecrease(final float elapsed) {
        timeLeft -= elapsed;
    }

    @Override
    public void timerIncrease() {
        timeLeft += 0.10;
        if(timeLeft > MAX_TIME) {
            timeLeft = MAX_TIME;
        }
    }

    @Override
    public float getTimeLeft() {
        return timeLeft;
    }

    @Override
    public float getMaxTime() {
        return MAX_TIME;
    }
}
