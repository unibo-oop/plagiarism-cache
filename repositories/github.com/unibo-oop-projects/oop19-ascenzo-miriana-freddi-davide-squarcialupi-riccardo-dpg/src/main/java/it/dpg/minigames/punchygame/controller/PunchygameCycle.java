package it.dpg.minigames.punchygame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.punchygame.controller.input.Input;
import it.dpg.minigames.punchygame.controller.input.InputObserver;
import it.dpg.minigames.punchygame.model.WorldImpl;
import it.dpg.minigames.punchygame.view.PunchygameView;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This class serves as InputObserver and MinigameCycle for PunchyMinigame
 * @author Davide Picchiotti
 * @see it.dpg.minigames.punchygame.PunchyMinigame
 * @see MinigameCycle
 * @see InputObserver
 * */

public class PunchygameCycle implements MinigameCycle, InputObserver {

    private static final int TIMER_TICK_MILLIS = 20;

    private PunchygameView view;
    private WorldImpl world;
    private BlockingQueue<Input> inputBuffer;

    public PunchygameCycle(final PunchygameView view) {
        this.view = Objects.requireNonNull(view);
        world = new WorldImpl();
        inputBuffer = new ArrayBlockingQueue<>(10);
    }

    @Override
    public int startCycle() {
        setup();
        long lastTime = System.currentTimeMillis();
        long currentTime;
        long total = 0;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        while(!world.isGameOver()) {
            currentTime = System.currentTimeMillis();

            total += currentTime - lastTime;
            total = updateTimer(total);
            processInput();

            lastTime = currentTime;
        }

        view.closeView();
        return world.getScore();
    }

    @Override
    public void notifyInput(final Input input) {
        inputBuffer.add(Objects.requireNonNull(input));
    }

    private long updateTimer(final long elapsed) {
        if(elapsed >= TIMER_TICK_MILLIS) {
            world.updateTimer((float) elapsed / 1000);
            updateView();
            return 0;
        }
        return elapsed;
    }

    private void setup() {
        view.setInputObserver(this);
        updateView();
    }

    private void processInput() {
        Input i = inputBuffer.poll();
        if(i != null && i.execute(world)) {
            view.playPunchSound();
        } else if(i != null) {
            view.playMissSound();
        }
    }

    private void updateView() {
        view.updateTimer(world.getTimer());
        view.updateSacks(world.getSacks());
        view.updateBoxer(world.getBoxerDirection());
        view.updateScore(world.getScore(), world.getScoreMultiplier());
    }
}
