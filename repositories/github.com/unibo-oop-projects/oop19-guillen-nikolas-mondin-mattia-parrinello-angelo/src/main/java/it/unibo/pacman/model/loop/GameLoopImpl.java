package it.unibo.pacman.model.loop;

import it.unibo.pacman.controller.SoundController;
import it.unibo.pacman.controller.game.GameController;

/**
 * 
 * Creates and manages the Game Loop.
 */
public class GameLoopImpl extends Thread implements GameLoop {
    /**
     * Constants for FPS(frames per second).
     */
    public static final int FPS = 60;
    private static final int SECONDS = 500;
    private static final int LAPSE = SECONDS / FPS;
    private final boolean update;
    private boolean isRunning;
    private final GameController game;
    /**
     * Set variables.
     * @param gc {@link GameController}
     */
    public GameLoopImpl(final GameController gc) {
        super();
        this.update = true;
        this.isRunning = false;
        this.game = gc;
    }
    /**
     * Start thread's execution.
     */
    @Override
    public void startLoop() {
        if (!this.isRunning) {
            this.isRunning = true;
            this.setName("gameLoop");
            SoundController.getStartGameSound().ifPresent(s -> s.playInLoop());
            this.start();
        }
    }
    /**
     * Stop thread's execution.
     */
    @Override
    public void stopLoop() {
        if (this.isRunning) {
            this.isRunning = false;
            SoundController.getStartGameSound().ifPresent(s -> s.stop());
            this.game.endGame();
            try {
                this.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } 
    }
    /**
     * {@inheritDoc}.
     */
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        long now;
        long deltaTime;
        while (isRunning && !this.game.isGameOver() && !this.game.hasWon()) {
            now = System.currentTimeMillis();
            deltaTime = now - lastTime;
            lastTime = now;
            if (this.update) {
                this.game.update();
            }
            deltaTime = System.currentTimeMillis() - now;
            sleepToNextFrame(deltaTime);
        }
        this.stopLoop();
    }
    /**
     * Used to pause the run for a certain time.
     * @param deltaTime
     */
    private void sleepToNextFrame(final long deltaTime) {
        long sleepTime;
        final long remainingToSleepTime = LAPSE - deltaTime;
        if (remainingToSleepTime < 0) {
            sleepTime = LAPSE;
        } else {
            sleepTime = remainingToSleepTime;
        }
        try {
            GameLoopImpl.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
