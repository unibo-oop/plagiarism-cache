package it.unibo.arkanoid.engine;

import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import it.unibo.arkanoid.controller.Command;
import it.unibo.arkanoid.controller.GameStats;
import it.unibo.arkanoid.model.Model;
import it.unibo.arkanoid.view.View;

/**
 * The game engine where periodically updates the game's world, process input
 * and render the {@link View}.
 *
 */
public final class GameLoopImpl extends Thread implements GameLoop {

    private static final int MAXINPUT = 500;
    private static final long PERIOD = 4;
    private static final int UPDATES_PER_RENDER = 5;
    private static final double SECOND_TO_MILLISECOND = 0.001;

    private final Model model;
    private final View view;
    private volatile boolean running;
    private volatile boolean pause;
    private int updatesDone;
    private final BlockingQueue<Command> inputQueue;

    /**
     * Creates a new game loop, that initializes the main elements.
     * 
     * @param view
     *            The {@link View} component.
     * 
     * @param model
     *            The {@link Model} component.
     * 
     * 
     */
    public GameLoopImpl(final View view, final Model model) {
        this.view = view;
        this.model = model;
        this.inputQueue = new ArrayBlockingQueue<Command>(MAXINPUT);
        this.setDaemon(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (this.running) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            lastTime = current;
            if (!this.pause) {
                processInput();
                updateGame(elapsed * SECOND_TO_MILLISECOND);
                this.updatesDone++;
                if (this.updatesDone == UPDATES_PER_RENDER) {
                    render();
                    this.updatesDone = 0;
                }
                waitNextFrame(current);
            }
        }
    }

    private void waitNextFrame(final long current) {
        final long delta = System.currentTimeMillis() - current;
        if (delta < PERIOD) {
            try {
                Thread.sleep(PERIOD - delta);
            } catch (Exception ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void processInput() {
        while (!this.inputQueue.isEmpty()) {
            this.inputQueue.poll().execute(this.model);
        }
    }

    private void updateGame(final double elapsed) {
        this.model.updateAll(elapsed);
    }

    /**
     * Draw the model representation in explicit graphic representation and show
     * {@link GameStats}.
     */
    private void render() {
        this.view.render(model.getCurrentLevel().getAllSubjects().collect(Collectors.toList()));
        this.view.displayGameStats(new GameStats(model.getGameWorldWidth(), model.getGameWorldHeight(),
                () -> model.getScore(), () -> model.getPlayerLife()));
    }

    @Override
    public synchronized void setPause() {
        this.pause = true;
    }

    @Override
    public synchronized void resumeGame() {
        this.pause = false;
        this.notifyAll();
    }

    @Override
    public synchronized void stopGame() {
        this.running = false;
        this.interrupt();
    }

    @Override
    public void addInput(final Command input) {
        this.inputQueue.add(input);
    }

    @Override
    public void startGameLoop() {
        this.running = true;
        this.start();
    }

}
