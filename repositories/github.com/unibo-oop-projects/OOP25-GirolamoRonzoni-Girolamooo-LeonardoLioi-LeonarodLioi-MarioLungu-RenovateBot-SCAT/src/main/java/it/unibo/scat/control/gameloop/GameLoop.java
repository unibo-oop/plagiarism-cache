package it.unibo.scat.control.gameloop;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Constants;
import it.unibo.scat.common.GameState;
import it.unibo.scat.model.api.ModelInterface;

/**
 * Main game loop: updates the model at a fixed tick rate and schedules the view
 * update.
 * The loop runs only when the game status is {@link GameState#RUNNING}.
 */
public final class GameLoop implements Runnable {
    private final ModelInterface model;
    private final Object pauseLock = new Object();
    private volatile boolean running;

    /**
     * Creates a new game loop.
     *
     * @param model the game model
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Intentional exposure is intended")
    public GameLoop(final ModelInterface model) {
        this.model = model;
    }

    /**
     * Starts the loop (idempotent).
     */
    public void start() {
        this.running = true;
    }

    /**
     * Stops the loop permanently.
     */
    public void stop() {
        this.running = false;
    }

    /**
     * Resumes the loop if it was paused.
     */
    public void resumeGame() {
        synchronized (pauseLock) {
            model.setGameState(GameState.RUNNING);
            pauseLock.notifyAll();
        }
    }

    @Override
    public void run() {
        while (running) {
            waitIfNotPlaying();

            if (!running) {
                break;
            }

            final long start = System.currentTimeMillis();

            model.update();

            final long elapsed = System.currentTimeMillis() - start;
            sleepUninterruptibly(Math.max(0L, Constants.GAME_STEP_MS - elapsed));
        }
    }

    /**
     * Suspends the thread execution if the game is not running.
     */
    private void waitIfNotPlaying() {
        if (model.getGameState() == GameState.RUNNING) {
            return;
        }

        synchronized (pauseLock) {
            while (running && model.getGameState() != GameState.RUNNING) {

                try {
                    pauseLock.wait();
                } catch (final InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

        }
    }

    /**
     * Suspends the current thread for the specified duration.
     * 
     * @param millis duration to sleep.
     */
    private static void sleepUninterruptibly(final long millis) {
        if (millis <= 0L) {
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
